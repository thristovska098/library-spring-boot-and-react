package mk.com.iwec.BookApp.infrastructure.exception.controllerAdvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import mk.com.iwec.BookApp.infrastructure.exception.NotFoundException;

@ControllerAdvice
public class EntityNotFoundAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public final ResponseEntity<String> handleAllException(Exception ex, WebRequest request) {
		return new ResponseEntity<>("Bad request.", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = NotFoundException.class)
	public final ResponseEntity<String> handleNotFoundException(NotFoundException nfe, WebRequest request) {

		return new ResponseEntity<>("The entity was not found.", HttpStatus.NOT_FOUND);
	}
}
