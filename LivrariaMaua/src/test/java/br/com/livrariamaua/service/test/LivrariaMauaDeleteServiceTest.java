package br.com.livrariamaua.service.test;

import static org.mockito.Mockito.doNothing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.livrariamaua.domain.Books;
import br.com.livrariamaua.exception.BooksNotValidException;
import br.com.livrariamaua.factory.BooksFactory;
import br.com.livrariamaua.repository.BooksRepository;
import br.com.livrariamaua.service.LivrariaMauaDeleteService;

public class LivrariaMauaDeleteServiceTest {

	private Books book;

	private BooksFactory booksFactory;
	
	@InjectMocks
	private LivrariaMauaDeleteService lmDeleteService;
	
	@Mock
	private BooksRepository booksRepository;
	
	@BeforeEach
	void setUp() {
		
		MockitoAnnotations.openMocks(this);
		
		booksFactory = new BooksFactory();
		book = booksFactory.retrieveMockedBook();
	}
	
	@Test
	void deleteBooksWithSuccess() {
		doNothing().when(booksRepository).deleteById(book.getId());
		
		Assertions.assertDoesNotThrow(() -> {
			lmDeleteService.deleteBook(book.getId());
		});
	}
	
	@Test
	void deleteBooksWithFailure() {
		doNothing().when(booksRepository).deleteById(null);
		
		Assertions.assertThrows(BooksNotValidException.class, () -> lmDeleteService.deleteBook(null));
	}
}
