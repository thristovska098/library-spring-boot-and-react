package mk.com.iwec.BookApp.author.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mk.com.iwec.BookApp.author.domain.Author;
import mk.com.iwec.BookApp.author.dto.AuthorDto1;
import mk.com.iwec.BookApp.author.dto.AuthorDto2;
import mk.com.iwec.BookApp.author.mapper.AuthorMapper;
import mk.com.iwec.BookApp.author.repository.AuthorRepository;
import mk.com.iwec.BookApp.book.domain.Book;
import mk.com.iwec.BookApp.book.repository.BookRepository;
import mk.com.iwec.BookApp.infrastructure.exception.NotFoundException;

@Service
public class AuthorService implements AuthorServiceLayer<Author, AuthorDto1, AuthorDto2> {
	@Autowired
	private AuthorRepository repo;
	@Autowired
	private BookRepository bookRepo;
	@Autowired
	private AuthorMapper mapper;

	public List<AuthorDto1> findAll() {
		return repo.findAll().stream().map(a -> {
			return mapper.entityToDto1(a);
		}).collect(Collectors.toList());
	}

	public AuthorDto2 findId(String id) {
		return mapper.entityToDto2(repo.findById(id).orElseThrow(() -> {
			return new NotFoundException("The author is not found.");
		}));
	}

	public void deleteId(String id) {
		repo.deleteById(id);
	}

	public Author save(Author author) {
		List<Book> newBooks = author.getBooks();
		if (Objects.nonNull(newBooks) && newBooks.isEmpty()) {
			newBooks = newBooks.stream().map(b -> {
				Book newBook = b;
				if (Objects.nonNull(b.getBookId())) {
					newBook = bookRepo.findById(b.getBookId()).orElse(b);
				}
				return newBook;
			}).collect(Collectors.toList());
			author.setBooks(newBooks);
		}
		return repo.save(author);
	}

	public Author update(String id, Author author) {
		Author original = repo.findById(id).orElseThrow(() -> {
			return new NotFoundException("Author not found");
		});
		return repo.save(mapper.entityUpdate(author, original));

	}

}
