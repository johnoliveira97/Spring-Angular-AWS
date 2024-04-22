package br.com.livrariamaua.service.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.livrariamaua.domain.Books;
import br.com.livrariamaua.exception.BooksNotFoundException;
import br.com.livrariamaua.exception.BooksNotValidException;
import br.com.livrariamaua.factory.BooksFactory;
import br.com.livrariamaua.repository.BooksRepository;
import br.com.livrariamaua.service.LivrariaMauaGetService;

public class LivrariaMauaGetServiceTest {
	
	private Books book;

	private BooksFactory booksFactory;
	
	@InjectMocks
	private LivrariaMauaGetService lmGetService;
	
	@Mock
	private BooksRepository booksRepository;
	
	@BeforeEach
	void setUp() {
		
		MockitoAnnotations.openMocks(this);
		
		booksFactory = new BooksFactory();
		book = booksFactory.retrieveMockedBook();
	}
	
	@Test
	void retrieveBooksWithSuccess() {
		var books = new ArrayList<Books>();
		Iterable<Books> books2 = new ArrayList<Books>();
		books.add(book);
		Mockito.when(booksRepository.findAll()).thenReturn(books);
		
		books2 = lmGetService.findAll();
		
		Assertions.assertNotNull(books2);
	}
	
	@Test
	void retrieveBookByIdWithSuccess() throws Exception {
		Optional<Books> books = Optional.of(book);
		var books2 = book; 
		
		Mockito.when(booksRepository.findById(book.getId())).thenReturn(books);
		
		books2 = lmGetService.findBook(book.getId());
		
		Assertions.assertNotNull(books2);
	}
	
	@Test
	void retrieveBooksByAuthorWithSuccess() throws Exception {
		var books = new ArrayList<Books>();
		List<Books> books2 = new ArrayList<Books>();
		books.add(book);
		Mockito.when(booksRepository.findByAuthor(book.getAuthor().toUpperCase())).thenReturn(books);
		
		books2 = lmGetService.findBooksByAuthor(book.getAuthor());
		
		Assertions.assertEquals(books2, books);
	}
	
	@Test
	void retrieveBooksByTitleWithSuccess() throws Exception {
		var books = new ArrayList<Books>();
		List<Books> books2 = new ArrayList<Books>();
		books.add(book);
		Mockito.when(booksRepository.findByTitle(book.getTitle().toUpperCase())).thenReturn(books);
		
		books2 = lmGetService.findBooksByTitle(book.getTitle());
		
		Assertions.assertEquals(books2, books);
	}
	
	@Test
	void retrieveBooksByGenderWithSuccess() throws Exception {
		var books = new ArrayList<Books>();
		List<Books> books2 = new ArrayList<Books>();
		books.add(book);
		Mockito.when(booksRepository.findByGender(book.getGender().toUpperCase())).thenReturn(books);
		
		books2 = lmGetService.findBooksByGender(book.getGender());
		
		Assertions.assertEquals(books2, books);
	}
	
	@Test
	void retrieveBookByIdWithFailure() throws Exception {
		Optional<Books> books = Optional.empty();
		
		Mockito.when(booksRepository.findById(book.getId())).thenReturn(books);
		
		Assertions.assertThrows(BooksNotFoundException.class, () -> lmGetService.findBook(book.getId()));
	}
	
	@Test
	void retrieveBookWithNoAuthorFailure() throws Exception {
		Mockito.when(booksRepository.findByAuthor(null)).thenThrow(new BooksNotValidException("Pamaretro author inválido."));
		
		Assertions.assertThrows(BooksNotValidException.class, () -> lmGetService.findBooksByAuthor(null));
	}
	
	@Test
	void retrieveBookWithNoTitleFailure() throws Exception {
		Mockito.when(booksRepository.findByTitle(null)).thenThrow(new BooksNotValidException("Pamaretro title inválido."));
		
		Assertions.assertThrows(BooksNotValidException.class, () -> lmGetService.findBooksByTitle(null));
	}
	
	@Test
	void retrieveBookWithNoGenderFailure() throws Exception {
		Mockito.when(booksRepository.findByGender(null)).thenThrow(new BooksNotValidException("Pamaretro gender inválido."));
		
		Assertions.assertThrows(BooksNotValidException.class, () -> lmGetService.findBooksByGender(null));
	}
	
	@Test
	void retrieveBooksNotFound() throws Exception {
		Mockito.when(booksRepository.findAll()).thenReturn(new ArrayList<Books>());
		
		Assertions.assertThrows(BooksNotFoundException.class, () -> lmGetService.findAll());
	}
	
	@Test
	void retrieveBookNotFound() throws Exception {
		Mockito.when(booksRepository.findById(book.getId())).thenReturn(Optional.of(new Books()));
		
		Assertions.assertThrows(BooksNotFoundException.class, () -> lmGetService.findBook(book.getId()));
	}
}
