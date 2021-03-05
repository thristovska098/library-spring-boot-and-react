package mk.com.iwec.BookApp.category.controller;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mk.com.iwec.BookApp.category.domain.Category;
import mk.com.iwec.BookApp.category.dto.CategoryDto2;
import mk.com.iwec.BookApp.category.service.CategoryService;
import mk.com.iwec.BookApp.infrastructure.controller.*;
import mk.com.iwec.BookApp.infrastructure.Endpoints;

@RestController
@RequestMapping(Endpoints.CATEGORIES)
@CrossOrigin
public class CategoryController implements ControllerLayer<Category, CategoryDto2> {

	@Autowired
	CategoryService service;

	@Override
	@GetMapping
	public List<CategoryDto2> findAll() {
		return service.findAll();
	}

	@Override
	@GetMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public CategoryDto2 findId(@PathVariable Long id) {
		return service.findId(id);
	}

	@Override
	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteId(@PathVariable Long id) {
		service.deleteId(id);

	}

	@Override
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Category save(@RequestBody Category t) {
		return service.save(t);

	}

	@Override
	@PutMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public Category update(@PathVariable Long id, @RequestBody Category t) {
		return service.update(id, t);

	}

}
