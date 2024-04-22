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
import br.com.livrariamaua.service.LivrariaMauaEditService;

public class LivrariaMauaEditServiceTest {

	private Books book;

	private BooksFactory booksFactory;
	
	@InjectMocks
	private LivrariaMauaEditService lmEditService;
	
	@Mock
	private BooksRepository booksRepository;
	
	@BeforeEach
	void setUp() {
		
		MockitoAnnotations.openMocks(this);
		
		booksFactory = new BooksFactory();
		book = booksFactory.retrieveMockedBook();
	}
	
	@Test
	void editBooksWithSuccess() {
		doNothing().when(booksRepository).updateBooks(book.getTitle(), book.getAuthor(), book.getQuantity(), book.getGender(), book.getId());
		
		Assertions.assertDoesNotThrow(() -> {
			lmEditService.editBook(book, book.getId());
		});
	}
	
	@Test
	void editBooksWithErrorInvalidIdNull() {
		doNothing().when(booksRepository).updateBooks(book.getTitle(), book.getAuthor(), book.getQuantity(), book.getGender(), null);
		
		Assertions.assertThrows(BooksNotValidException.class, () -> lmEditService.editBook(book, null));
	}
	
	@Test
	void editBooksWithErrorInvalidId() {
		doNothing().when(booksRepository).updateBooks(book.getTitle(), book.getAuthor(), book.getQuantity(), book.getGender(), null);
		
		Assertions.assertThrows(BooksNotValidException.class, () -> lmEditService.editBook(book, -1));
	}
	
	@Test
	void editBooksWithErrorInvalidTitle() {
		book.setTitle(null);
		doNothing().when(booksRepository).updateBooks(book.getTitle(), book.getAuthor(), book.getQuantity(), book.getGender(), null);
		
		Assertions.assertThrows(BooksNotValidException.class, () -> lmEditService.editBook(book, book.getId()));
	}
	
	@Test
	void editBooksWithErrorInvalidTitleEmpty() {
		book.setTitle("");
		doNothing().when(booksRepository).updateBooks(book.getTitle(), book.getAuthor(), book.getQuantity(), book.getGender(), null);
		
		Assertions.assertThrows(BooksNotValidException.class, () -> lmEditService.editBook(book, book.getId()));
	}
	
	@Test
	void editBooksWithErrorInvalidAuthor() {
		book.setAuthor(null);
		doNothing().when(booksRepository).updateBooks(book.getTitle(), book.getAuthor(), book.getQuantity(), book.getGender(), null);
		
		Assertions.assertThrows(BooksNotValidException.class, () -> lmEditService.editBook(book, book.getId()));
	}
	
	@Test
	void editBooksWithErrorInvalidAuthorEmpty() {
		book.setAuthor("");
		doNothing().when(booksRepository).updateBooks(book.getTitle(), book.getAuthor(), book.getQuantity(), book.getGender(), null);
		
		Assertions.assertThrows(BooksNotValidException.class, () -> lmEditService.editBook(book, book.getId()));
	}
	
	@Test
	void editBooksWithErrorInvalidGender() {
		book.setGender(null);
		doNothing().when(booksRepository).updateBooks(book.getTitle(), book.getAuthor(), book.getQuantity(), book.getGender(), null);
		
		Assertions.assertThrows(BooksNotValidException.class, () -> lmEditService.editBook(book, book.getId()));
	}
	
	@Test
	void editBooksWithErrorInvalidGenderEmpty() {
		book.setGender("");
		doNothing().when(booksRepository).updateBooks(book.getTitle(), book.getAuthor(), book.getQuantity(), book.getGender(), null);
		
		Assertions.assertThrows(BooksNotValidException.class, () -> lmEditService.editBook(book, book.getId()));
	}
	
	@Test
	void editBooksWithErrorInvalidQuantity() {
		book.setQuantity(-1);
		doNothing().when(booksRepository).updateBooks(book.getTitle(), book.getAuthor(), book.getQuantity(), book.getGender(), null);
		
		Assertions.assertThrows(BooksNotValidException.class, () -> lmEditService.editBook(book, book.getId()));
	}
}
