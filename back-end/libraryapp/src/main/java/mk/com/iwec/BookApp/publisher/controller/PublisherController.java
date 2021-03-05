package mk.com.iwec.BookApp.publisher.controller;

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

import mk.com.iwec.BookApp.infrastructure.controller.ControllerLayer;
import mk.com.iwec.BookApp.infrastructure.Endpoints;
import mk.com.iwec.BookApp.publisher.domain.Publisher;
import mk.com.iwec.BookApp.publisher.dto.PublisherDto2;
import mk.com.iwec.BookApp.publisher.service.PublisherService;

@RestController
@RequestMapping(Endpoints.PUBLISHERS)
@CrossOrigin
public class PublisherController implements ControllerLayer<Publisher, PublisherDto2> {
	@Autowired
	PublisherService service;

	@Override
	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public List<PublisherDto2> findAll() {
		return service.findAll();
	}

	@Override
	@GetMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public PublisherDto2 findId(@PathVariable Long id) {
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
	public Publisher save(@RequestBody Publisher t) {
		return service.save(t);
	}

	@Override
	@PutMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public Publisher update(@PathVariable Long id, @RequestBody Publisher t) {
		return service.update(id, t);
	}

}