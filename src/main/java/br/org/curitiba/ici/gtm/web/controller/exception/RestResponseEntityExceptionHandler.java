package br.org.curitiba.ici.gtm.web.controller.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import br.org.curitiba.ici.gtm.exception.ConstraintViolationException;
import br.org.curitiba.ici.gtm.exception.NotFoundException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
	
	
	@ExceptionHandler({ ConstraintViolationException.class })
    protected ResponseEntity<ErrorConstraint> handleConstraintViolationException(
    		ConstraintViolationException exception, WebRequest request) {
		ErrorConstraint error = new ErrorConstraint("Constraint Violation", 400, 
				exception.getField(), exception.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler({ MethodArgumentNotValidException.class })
    protected ResponseEntity<ErrorConstraint> handleMethodArgumentNotValidException(
    		MethodArgumentNotValidException exception, WebRequest request) {
		
		List<Violation> violations = exception.getAllErrors()
		.stream()
		.map(fieldViolation -> new Violation(fieldViolation.getDefaultMessage(), fieldViolation.getCodes()[0]))
		.collect(Collectors.toList());
		ErrorConstraint error = new ErrorConstraint("Constraint Violation", HttpStatus.BAD_REQUEST.value(), violations);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler({ NotFoundException.class })
    protected ResponseEntity<ErrorConstraint> handleNotFoundException(
    		NotFoundException exception, WebRequest request) {
		ErrorConstraint error = new ErrorConstraint("Not found", HttpStatus.NOT_FOUND.value(), 
				exception.getField(), exception.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
	

}
