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
		} else if ((null == book || null == book.getId()) && operation == LivrariaMauaOperations.LIST) {
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
		} else if (null == books || books.isEmpty() && operation == LivrariaMauaOperations.LIST) {
			throw new BooksNotFoundException("Livros não encontrados para os parâmetros informados.");
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
		String invalidQuantity = "";
		if (null == author || "".equalsIgnoreCase(author.trim())) {
			invalidParam = "author";
		}

		if (null == title || "".equalsIgnoreCase(title.trim())) {
			invalidParam = "title";
		}

		if (null == gender || "".equalsIgnoreCase(gender.trim())) {
			invalidParam = "gender";
		}

		if (quantity <= 0) {
			invalidParam = "quantity";
			invalidQuantity = " Quantidade precisa ser maior que zero.";
		}

		if (null != invalidParam) {
			throw new BooksNotValidException("Pamaretro " + invalidParam + " inválido." + invalidQuantity);
		}
	}
}
