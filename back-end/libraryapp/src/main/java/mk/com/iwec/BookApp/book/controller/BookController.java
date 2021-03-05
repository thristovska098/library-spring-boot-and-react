package mk.com.iwec.BookApp.book.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mk.com.iwec.BookApp.book.domain.Book;
import mk.com.iwec.BookApp.book.dto.BookDto2;
import mk.com.iwec.BookApp.book.service.BookService;
import mk.com.iwec.BookApp.infrastructure.controller.ControllerLayer;
import mk.com.iwec.BookApp.infrastructure.Endpoints;

@RestController
@RequestMapping(Endpoints.BOOKS)
@CrossOrigin
public class BookController implements ControllerLayer<Book,  BookDto2> {
	@Autowired
	private BookService service;

	@GetMapping("/find")
	@ResponseStatus(value = HttpStatus.OK)
	public List<BookDto2> findByName(@RequestParam String name) {
		return service.findByName(name);
	}

	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public List<BookDto2> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public BookDto2 findId(@PathVariable Long id) {
		return service.findId(id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteId(@PathVariable Long id) {
		service.deleteId(id);

	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Book save(@RequestBody Book t) {
		return service.save(t);

	}

	@PutMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public Book update(@PathVariable Long id, @RequestBody Book t) {
		return service.update(id, t);
	}

}