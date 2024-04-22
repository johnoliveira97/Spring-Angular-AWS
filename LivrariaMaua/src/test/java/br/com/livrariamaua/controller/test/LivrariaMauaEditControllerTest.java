package br.com.livrariamaua.controller.test;

import static org.mockito.Mockito.doNothing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.livrariamaua.controller.LivrariaMauaEditController;
import br.com.livrariamaua.domain.Books;
import br.com.livrariamaua.factory.BooksFactory;
import br.com.livrariamaua.service.LivrariaMauaEditService;

public class LivrariaMauaEditControllerTest {

	private Books book;

	private BooksFactory booksFactory;

	@InjectMocks
	private LivrariaMauaEditController lMEditController;
	
	@Mock
	private LivrariaMauaEditService lmEditService;
	
	@BeforeEach
	void setUp() {
		
		MockitoAnnotations.openMocks(this);
		
		booksFactory = new BooksFactory();
		book = booksFactory.retrieveMockedBook();
	}
	
	@Test
	void editBookWithSuccess() {
		doNothing().when(lmEditService).editBook(book, book.getId());
		
		Assertions.assertDoesNotThrow(() -> {
			lMEditController.editBooks(book, book.getId().toString());
		});
	}
}
