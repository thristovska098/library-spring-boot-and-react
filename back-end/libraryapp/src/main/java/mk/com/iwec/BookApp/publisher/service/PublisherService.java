package mk.com.iwec.BookApp.publisher.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mk.com.iwec.BookApp.book.domain.Book;
import mk.com.iwec.BookApp.book.repository.BookRepository;
import mk.com.iwec.BookApp.infrastructure.exception.NotFoundException;
import mk.com.iwec.BookApp.infrastructure.service.ServiceLayer;
import mk.com.iwec.BookApp.publisher.mapper.PublisherMapper;
import mk.com.iwec.BookApp.publisher.repository.PublisherRepository;
import mk.com.iwec.BookApp.publisher.domain.*;
import mk.com.iwec.BookApp.publisher.dto.*;

@Service
public class PublisherService implements ServiceLayer<Publisher, PublisherDto2> {

	@Autowired
	PublisherRepository repo;
	@Autowired
	PublisherMapper mapper;
	@Autowired
	BookRepository bookRepo;

	@Override
	public List<PublisherDto2> findAll() {
		return repo.findAll().stream().map(p -> {
			return mapper.entityToDto2(p);
		}).collect(Collectors.toList());
	}

	@Override
	public PublisherDto2 findId(Long id) {
		return mapper.entityToDto2(repo.findById(id).orElseThrow(() -> {
			return new NotFoundException("The publisher is not found");
		}));
	}

	@Override
	public void deleteId(Long id) {
		repo.deleteById(id);

	}

	@Override
	public Publisher save(Publisher t) {
		List<Book> newBooks = t.getBooks();
		if (Objects.nonNull(newBooks) && !newBooks.isEmpty()) {
			newBooks = newBooks.stream().map(b -> {
				Book newBook = b;
				if (Objects.nonNull(newBook.getBookId())) {
					newBook = bookRepo.findById(b.getBookId()).orElse(b);
				}
				return newBook;
			}).collect(Collectors.toList());
			t.setBooks(newBooks);
		}
		return repo.save(t);

	}

	@Override
	public Publisher update(Long id, Publisher t) {
		Publisher original = repo.findById(id).orElseThrow(() -> {
			return new NotFoundException("The publisher is not found.");
		});
		return repo.save(mapper.entityUpdate(t, original));

	}

}