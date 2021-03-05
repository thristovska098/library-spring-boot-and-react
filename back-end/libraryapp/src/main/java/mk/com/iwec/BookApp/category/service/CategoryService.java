package mk.com.iwec.BookApp.category.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mk.com.iwec.BookApp.book.domain.Book;
import mk.com.iwec.BookApp.book.repository.BookRepository;
import mk.com.iwec.BookApp.category.domain.Category;
import mk.com.iwec.BookApp.category.dto.CategoryDto2;
import mk.com.iwec.BookApp.category.mapper.CategoryMapper;
import mk.com.iwec.BookApp.category.repository.CategoryRepository;
import mk.com.iwec.BookApp.infrastructure.exception.NotFoundException;
import mk.com.iwec.BookApp.infrastructure.service.ServiceLayer;

@Service
public class CategoryService implements ServiceLayer<Category,  CategoryDto2> {

	@Autowired
	CategoryRepository repo;
	@Autowired
	BookRepository bookRepo;
	@Autowired
	CategoryMapper mapper;

	@Override
	public List<CategoryDto2> findAll() {
		return repo.findAll().stream().map(c -> {
			return mapper.entityToDto2(c);
		}).collect(Collectors.toList());
	}

	@Override
	public CategoryDto2 findId(Long id) {
		return mapper.entityToDto2(repo.findById(id).orElseThrow(() -> {
			return new NotFoundException("The category isn't found");
		}));
	}

	@Override
	public void deleteId(Long id) {
		repo.deleteById(id);

	}

	@Override
	public Category save(Category t) {
		List<Book> newBooks = t.getBooks();
		if (Objects.nonNull(newBooks) && !newBooks.isEmpty()) {
			newBooks = newBooks.stream().map(b -> {
				Book newBook = b;
				if (Objects.nonNull(b.getBookId())) {
					newBook = bookRepo.findById(b.getBookId()).orElse(b);
				}
				return newBook;
			}).collect(Collectors.toList());
			t.setBooks(newBooks);
		}
		return repo.save(t);

	}

	@Override
	public Category update(Long id, Category t) {
		Category original = repo.findById(id).orElseThrow(() -> {
			return new NotFoundException("The category is not found.");
		});
		return repo.save(mapper.entityUpdate(t, original));
	}

}