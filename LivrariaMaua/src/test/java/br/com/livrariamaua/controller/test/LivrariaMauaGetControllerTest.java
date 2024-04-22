package br.com.livrariamaua.controller.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.livrariamaua.controller.LivrariaMauaGetController;
import br.com.livrariamaua.domain.Books;
import br.com.livrariamaua.factory.BooksFactory;
import br.com.livrariamaua.service.LivrariaMauaGetService;

public class LivrariaMauaGetControllerTest {

	private Books book;

	private BooksFactory booksFactory;

	@InjectMocks
	private LivrariaMauaGetController lMGetController;
	
	@Mock
	private LivrariaMauaGetService lmGetService;
	
	@BeforeEach
	void setUp() {
		
		MockitoAnnotations.openMocks(this);
		
		booksFactory = new BooksFactory();
		book = booksFactory.retrieveMockedBook();
	}
	
	@Test
	void retrieveBooksWithSuccess() throws Exception {
		Assertions.assertDoesNotThrow(() -> {
			lMGetController.getBooks();
		});
	}
	
	@Test
	void retrieveBooksByIdWithSuccess() throws Exception {
		Assertions.assertDoesNotThrow(() -> {
			lMGetController.getBookById(book.getId());
		});
	}

	@Test
	void retrieveBookByAuthorWithSuccess() throws Exception {
		Assertions.assertDoesNotThrow(() -> {
			lMGetController.getBooksByAuthor(book.getAuthor());
		});
	}
	
	@Test
	void retrieveBookByTitleWithSuccess() throws Exception {
		Assertions.assertDoesNotThrow(() -> {
			lMGetController.getBooksByTitle(book.getTitle());
		});
	}
	
	@Test
	void retrieveBookByGenderWithSuccess() throws Exception {
		Assertions.assertDoesNotThrow(() -> {
			lMGetController.getBooksByGender(book.getGender());
		});
	}
}
