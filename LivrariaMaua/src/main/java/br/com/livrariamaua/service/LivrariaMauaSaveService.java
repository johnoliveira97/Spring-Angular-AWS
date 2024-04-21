package br.com.livrariamaua.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.livrariamaua.domain.Books;
import br.com.livrariamaua.repository.BooksRepository;

@Service
public class LivrariaMauaSaveService {

	@Autowired
	BooksRepository booksRepository;
	
	public Books saveBook(Books book) {
		return booksRepository.save(book);
	}

	public Object saveBooks(List<Books> books) {
		return booksRepository.saveAll(books);
	}
}
