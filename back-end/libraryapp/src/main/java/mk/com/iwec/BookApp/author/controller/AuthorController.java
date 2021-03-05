package mk.com.iwec.BookApp.author.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mk.com.iwec.BookApp.author.domain.Author;
import mk.com.iwec.BookApp.author.dto.AuthorDto1;
import mk.com.iwec.BookApp.author.dto.AuthorDto2;
import mk.com.iwec.BookApp.author.service.AuthorService;
import mk.com.iwec.BookApp.infrastructure.Endpoints;

@RestController
@RequestMapping(Endpoints.AUTHORS)
@CrossOrigin
public class AuthorController implements AuthorControllerLayer<Author, AuthorDto1, AuthorDto2> {
	@Autowired
	AuthorService service;

	@Override
	@GetMapping
	@ResponseBody
	public List<AuthorDto1> findAll() {
		return service.findAll();
	}

	@Override
	@GetMapping("/{id}")
	@ResponseBody
	public AuthorDto2 findId(@PathVariable String id) {
		return service.findId(id);
	}

	@Override
	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteId(@PathVariable String id) {
		service.deleteId(id);

	}

	@Override
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Author save(@RequestBody Author t) {
		return service.save(t);

	}

	@Override
	@PutMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public Author update(@PathVariable String id, @RequestBody Author t) {
		return service.update(id, t);
	}

}
