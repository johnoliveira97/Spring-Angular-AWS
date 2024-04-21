package br.com.livrariamaua.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.livrariamaua.domain.Books;
import br.com.livrariamaua.exception.BooksNotFoundException;
import br.com.livrariamaua.repository.BooksRepository;

@Service
public class LivrariaMauaGetService {

	@Autowired
	BooksRepository booksRepository;

	public Iterable<Books> findAll() {
		return booksRepository.findAll();
	}

	public Books findBook(Integer id) throws Exception {
		Optional<Books> books = booksRepository.findById(id);
		if (books.isPresent()) {
			validateReturn(books.get());
		} else {
			throw new BooksNotFoundException("Index informado nao encontrado.");
		}
		return books.get();
	}

	public Books findBookByAuthor(String author) throws Exception {
		Books book = booksRepository.findByAuthor(author.toUpperCase());
		validateReturn(book);
		return book;
	}

	public Books findBookByTitle(String title) throws Exception {
		Books book = booksRepository.findByTitle(title);
		validateReturn(book);
		return book;
	}
	
	
	private void validateReturn(Books book) throws BooksNotFoundException {
		if(null == book) {
			throw new BooksNotFoundException("Livro nao encontrado");
		}
	}

	public Books addNewBook(Books book) {
		return booksRepository.save(book);	
	}
}
