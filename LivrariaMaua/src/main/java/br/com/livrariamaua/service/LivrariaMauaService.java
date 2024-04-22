package br.com.livrariamaua.service;

import java.util.List;

import br.com.livrariamaua.domain.Books;
import br.com.livrariamaua.enums.LivrariaMauaOperations;
import br.com.livrariamaua.exception.BooksNotValidException;
import br.com.livrariamaua.helper.LivrariaMauaValidateBook;

public abstract class LivrariaMauaService {
	
	protected void validate(Books book, LivrariaMauaOperations operation) throws BooksNotValidException {
		var validate = new LivrariaMauaValidateBook();
		validate.validateBook(book, operation);
	}
	
	protected void validate(List<Books> book, LivrariaMauaOperations operation) throws BooksNotValidException {
		var validate = new LivrariaMauaValidateBook();
		validate.validateBook(book, operation);
	}
	
	protected void validateId(Integer id) throws BooksNotValidException {
		var validate = new LivrariaMauaValidateBook();
		validate.validateId(id);
	}
	
	protected void validateParam(String param) throws BooksNotValidException {
		var validate = new LivrariaMauaValidateBook();
		validate.validateParam(param);
	}
}
