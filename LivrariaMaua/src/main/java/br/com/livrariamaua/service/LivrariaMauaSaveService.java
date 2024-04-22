package br.com.livrariamaua.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.livrariamaua.domain.Books;
import br.com.livrariamaua.enums.LivrariaMauaOperations;
import br.com.livrariamaua.repository.BooksRepository;

@Service
public class LivrariaMauaSaveService extends LivrariaMauaService {

	@Autowired
	BooksRepository booksRepository;
	
	public Books saveBook(Books book) {
		validate(book, LivrariaMauaOperations.SAVE);
		return booksRepository.save(book);
	}

	public Object saveBooks(List<Books> books) {
		validate(books, LivrariaMauaOperations.SAVE);
		return booksRepository.saveAll(books);
	}
}
