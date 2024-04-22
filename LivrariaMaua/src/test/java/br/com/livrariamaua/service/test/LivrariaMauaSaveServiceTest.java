package br.com.livrariamaua.service.test;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
import br.com.livrariamaua.service.LivrariaMauaSaveService;

public class LivrariaMauaSaveServiceTest {

	private Books book;
	
	private List<Books> books;

	private BooksFactory booksFactory;
	
	@InjectMocks
	private LivrariaMauaSaveService lmSaveService;
	
	@Mock
	private BooksRepository booksRepository;
	
	@BeforeEach
	void setUp() {
		
		MockitoAnnotations.openMocks(this);
		
		booksFactory = new BooksFactory();
		book = booksFactory.retrieveMockedBook();
		books = booksFactory.retrieveMockedListBooks();
	}
	
	@Test
	void saveBooksWithSuccess() {
		when(booksRepository.save(book)).thenReturn(book);
		
		Assertions.assertDoesNotThrow(() -> {
			lmSaveService.saveBook(book);
		});
	}
	
	@Test
	void saveBooksListWithSuccess() {
		when(booksRepository.saveAll(books)).thenReturn(books);
		
		Assertions.assertDoesNotThrow(() -> {
			lmSaveService.saveBooks(books);
		});
	}
	
	@Test
	void saveBooksWithErrorNoBookInformed() {
		when(booksRepository.save(null)).thenReturn(null);
		
		Assertions.assertThrows(BooksNotValidException.class, () -> {
			lmSaveService.saveBooks(null);
		});
	}
	
	@Test
	void saveBooksListWithErrorNoBooksInformed() {
		when(booksRepository.saveAll(null)).thenReturn(null);
		
		Assertions.assertThrows(BooksNotValidException.class, () -> {
			lmSaveService.saveBooks(null);
		});
	}
	
	@Test
	void saveBooksListWithErrorBooksWithNoInformation() {
		List<Books> listBooks = new ArrayList<Books>();
		when(booksRepository.saveAll(listBooks)).thenReturn(null);
		
		Assertions.assertThrows(BooksNotValidException.class, () -> {
			lmSaveService.saveBooks(listBooks);
		});
	}
	

}
