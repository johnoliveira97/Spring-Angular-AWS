package br.com.livrariamaua.helper;

import java.util.List;

import br.com.livrariamaua.domain.Books;
import br.com.livrariamaua.enums.LivrariaMauaOperations;
import br.com.livrariamaua.exception.BooksNotFoundException;
import br.com.livrariamaua.exception.BooksNotValidException;

public class LivrariaMauaValidateBook {

	public void validateBook(Books book, LivrariaMauaOperations operation) {

		if (null == book && (operation == LivrariaMauaOperations.SAVE || operation == LivrariaMauaOperations.EDIT)) {
			throw new BooksNotValidException("Parametros necessários não informados.");
		} else if (null == book && operation == LivrariaMauaOperations.LIST) {
			throw new BooksNotFoundException("Livro nao encontrado");
		} else if (null != book && operation != LivrariaMauaOperations.LIST) {
			String author = book.getAuthor();
			String title = book.getTitle();
			String gender = book.getGender();
			Integer quantity = book.getQuantity();
			validateParams(author, title, gender, quantity);
		}
	}

	public void validateBook(List<Books> books, LivrariaMauaOperations operation) {
		if (null == books || books.isEmpty() && operation == LivrariaMauaOperations.SAVE) {
			throw new BooksNotValidException("Parametros necessários não informados.");
		} else if (null != books && !books.isEmpty()) {
			for (Books book : books) {
				String author = book.getAuthor();
				String title = book.getTitle();
				String gender = book.getGender();
				Integer quantity = book.getQuantity();
				validateParams(author, title, gender, quantity);
			}
		}
	}
	
	public void validateId(Integer id) {
		if (null == id || id <= 0) {
			throw new BooksNotValidException("Index informado é inválido.");
		}
	}
	
	public void validateParam(String param) {
		if (null == param || "".equalsIgnoreCase(param.trim())) {
			throw new BooksNotValidException("Parametro informado é inválido.");
		}
	}

	private void validateParams(String author, String title, String gender, Integer quantity) {
		String invalidParam = null;
		if (null == author) {
			invalidParam = "Autor nulo.";
		}

		if (null == title) {
			invalidParam = "Título nulo.";
		}

		if (null == gender) {
			invalidParam = "Gênero nulo.";
		}

		if (quantity <= 0) {
			invalidParam = "Quantidade precisa ser maior que 0.";
		}

		if (null != invalidParam) {
			throw new BooksNotValidException("Pamaretro inválido. " + invalidParam);
		}
	}
}
