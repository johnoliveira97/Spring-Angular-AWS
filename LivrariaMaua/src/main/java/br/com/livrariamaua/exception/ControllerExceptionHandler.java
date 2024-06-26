package br.com.livrariamaua.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler({ BooksNotFoundException.class })
	public ResponseEntity<ExceptionResponse> handleBooksNotFoundException(Exception exception) {
		BooksNotFoundException booksNotFoundException = (BooksNotFoundException) exception;
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.getMessages().add(new ExceptionMessage("406", booksNotFoundException.getInfo(), "Erro ao retornar livro."));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler({ BooksNotValidException.class })
	public ResponseEntity<ExceptionResponse> handleBooksNotValidException(Exception exception) {
		BooksNotValidException booksNotValidException = (BooksNotValidException) exception;
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.getMessages().add(new ExceptionMessage("406", booksNotValidException.getInfo(), "Erro na operação."));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_ACCEPTABLE);
	}
}
