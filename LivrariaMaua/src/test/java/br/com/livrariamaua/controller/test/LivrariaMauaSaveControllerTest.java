package br.com.livrariamaua.controller.test;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.livrariamaua.controller.LivrariaMauaSaveController;
import br.com.livrariamaua.domain.Books;
import br.com.livrariamaua.factory.BooksFactory;
import br.com.livrariamaua.service.LivrariaMauaSaveService;

public class LivrariaMauaSaveControllerTest {

	private Books book;
	
	private List<Books> books;

	private BooksFactory booksFactory;

	@InjectMocks
	private LivrariaMauaSaveController lMSaveController;
	
	@Mock
	private LivrariaMauaSaveService lmSaveService;
	
	@BeforeEach
	void setUp() {
		
		MockitoAnnotations.openMocks(this);
		
		booksFactory = new BooksFactory();
		book = booksFactory.retrieveMockedBook();
		books = booksFactory.retrieveMockedListBooks();
	}
	
	@Test
	void saveBookWithSuccess() {
		when(lmSaveService.saveBook(book)).thenReturn(book);
		
		Assertions.assertDoesNotThrow(() -> {
			lMSaveController.saveBooks(book);
		});
	}
	
	@Test
	void saveBooksWithSuccess() {
		when(lmSaveService.saveBooks(books)).thenReturn(books);
		
		Assertions.assertDoesNotThrow(() -> {
			lMSaveController.saveBooks(books);
		});
	}
}
