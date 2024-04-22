package br.com.livrariamaua.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.livrariamaua.domain.Books;
import br.com.livrariamaua.enums.LivrariaMauaOperations;
import br.com.livrariamaua.exception.BooksNotFoundException;
import br.com.livrariamaua.repository.BooksRepository;

@Service
public class LivrariaMauaGetService extends LivrariaMauaService {

	@Autowired
	BooksRepository booksRepository;

	public Iterable<Books> findAll() {
		return booksRepository.findAll();
	}

	public Books findBook(Integer id) throws Exception {
		validateId(id);
		Optional<Books> books = booksRepository.findById(id);
		if (books.isPresent()) {
			validate(books.get(), LivrariaMauaOperations.LIST);
		} else {
			throw new BooksNotFoundException("Index informado nao encontrado.");
		}
		return books.get();
	}

	public Books findBookByAuthor(String author) throws Exception {
		validateParam(author);
		Books book = booksRepository.findByAuthor(author.toUpperCase());
		validate(book, LivrariaMauaOperations.LIST);
		return book;
	}

	public Books findBookByTitle(String title) throws Exception {
		validateParam(title);
		Books book = booksRepository.findByTitle(title.toUpperCase());
		validate(book, LivrariaMauaOperations.LIST);
		return book;
	}
	
	public Books findBookByGender(String gender) throws Exception {
		validateParam(gender);
		Books book = booksRepository.findByGender(gender.toUpperCase());
		validate(book, LivrariaMauaOperations.LIST);
		return book;
	}
}
