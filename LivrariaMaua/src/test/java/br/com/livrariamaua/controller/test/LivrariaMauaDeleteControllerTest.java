package br.com.livrariamaua.controller.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.livrariamaua.controller.LivrariaMauaDeleteController;
import br.com.livrariamaua.domain.Books;
import br.com.livrariamaua.factory.BooksFactory;
import br.com.livrariamaua.service.LivrariaMauaDeleteService;

public class LivrariaMauaDeleteControllerTest {

	private Books book;

	private BooksFactory booksFactory;

	@InjectMocks
	private LivrariaMauaDeleteController lMDeleteController;
	
	@Mock
	private LivrariaMauaDeleteService lmDeleteService;
	
	@BeforeEach
	void setUp() {
		
		MockitoAnnotations.openMocks(this);
		
		booksFactory = new BooksFactory();
		book = booksFactory.retrieveMockedBook();
	}
	
	@Test
	void deleteBookWithSucess() {
		Assertions.assertDoesNotThrow(() -> {
			lMDeleteController.deleteBooks(book.getId().toString());
		});
	}
}
