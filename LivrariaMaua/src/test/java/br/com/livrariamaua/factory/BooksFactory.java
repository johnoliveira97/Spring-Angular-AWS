package br.com.livrariamaua.factory;

import java.util.ArrayList;
import java.util.List;

import br.com.livrariamaua.domain.Books;

public class BooksFactory {

	public Books retrieveMockedBook() {
		var books = new Books();
		books.setId(1);
		books.setAuthor("Joao Pedro");
		books.setGender("biography");
		books.setTitle("Life is Good.");
		books.setQuantity(10);
		return books;
	}
	
	public List<Books> retrieveMockedListBooks() {
		Books books = new Books();
		books.setId(2);
		books.setAuthor("Joao Pedro");
		books.setGender("biography");
		books.setTitle("Life is Good.");
		books.setQuantity(10);
		
		Books books2 = new Books();
		books2.setId(3);
		books2.setAuthor("Eiichiro Oda");
		books2.setGender("pirates");
		books2.setTitle("One Piece");
		books2.setQuantity(10);
		
		List<Books> listBooks = new ArrayList<Books>();
		listBooks.add(books);
		listBooks.add(books2);
		
		return listBooks;
	}
}
