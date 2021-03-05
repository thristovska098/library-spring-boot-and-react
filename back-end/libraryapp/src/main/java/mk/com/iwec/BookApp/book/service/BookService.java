package mk.com.iwec.BookApp.book.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mk.com.iwec.BookApp.author.domain.Author;
import mk.com.iwec.BookApp.author.repository.AuthorRepository;
import mk.com.iwec.BookApp.book.domain.Book;
import mk.com.iwec.BookApp.book.dto.BookDto2;
import mk.com.iwec.BookApp.book.mapper.BookMapper;
import mk.com.iwec.BookApp.book.repository.BookRepository;
import mk.com.iwec.BookApp.category.domain.Category;
import mk.com.iwec.BookApp.category.repository.CategoryRepository;
import mk.com.iwec.BookApp.infrastructure.exception.NotFoundException;
import mk.com.iwec.BookApp.infrastructure.service.ServiceLayer;
import mk.com.iwec.BookApp.publisher.domain.Publisher;
import mk.com.iwec.BookApp.publisher.repository.PublisherRepository;

@Service
public class BookService implements ServiceLayer<Book, BookDto2> {

	@Autowired
	BookRepository repo;
	@Autowired
	AuthorRepository authorRepo;
	@Autowired
	PublisherRepository publisherRepo;
	@Autowired
	CategoryRepository categoryRepo;
	@Autowired
	BookMapper mapper;

	public List<BookDto2> findByName(String name) {
		return repo.findByName(name).stream().map(b -> mapper.entityToDto2(b)).collect(Collectors.toList());
	}

	@Override
	public List<BookDto2> findAll() {
		return repo.findAll().stream().map(b -> {
			return mapper.entityToDto2(b);
		}).collect(Collectors.toList());

	}

	@Override
	public BookDto2 findId(Long id) {
		Book book = repo.findById(id).orElseThrow(() -> {
			return new NotFoundException("The Book is not found");
		});
		return mapper.entityToDto2(book);
	}

	@Override
	public void deleteId(Long id) {
		repo.deleteById(id);
	}

	@Override
	public Book save(Book t) {
		List<Author> newAuthors = t.getAuthors();
		if (!newAuthors.isEmpty()) {
			newAuthors = t.getAuthors().stream().map((a) -> {
				Author author = a;
				if (Objects.nonNull(author.getSsn())) {
					author = authorRepo.findById(a.getSsn()).orElse(a);
					
				}
				return author;
			}).collect(Collectors.toList());
			t.setAuthors(newAuthors);
		}

		Publisher newPublisher = t.getPublisher();
		if (Objects.nonNull(newPublisher) && Objects.nonNull(newPublisher.getPublisherId())) {
			newPublisher = publisherRepo.findById(newPublisher.getPublisherId()).orElse(t.getPublisher());
			t.setPublisher(newPublisher);
		}
		List<Category> newCategories = t.getCategories();
		if (!newCategories.isEmpty()) {
			newCategories = newCategories.stream().map(c -> {
				Category category = c;
				if (Objects.nonNull(c.getCategoryId())) {
					category = categoryRepo.findById(c.getCategoryId()).orElse(c);
				}
				return category;
			}).collect(Collectors.toList());
			t.setCategories(newCategories);
		}
		return repo.saveAndFlush(t);
	}

	@Override
	public Book update(Long id, Book t) {
		Book pom = repo.findById(t.getBookId()).orElseThrow(() -> {
			return new NotFoundException("The book is not found.");
		});
		return repo.save(mapper.entityUpdate(t, pom));

	}

}