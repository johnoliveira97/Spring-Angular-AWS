package br.com.livrariamaua.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.livrariamaua.domain.Books;
import br.com.livrariamaua.enums.LivrariaMauaOperations;
import br.com.livrariamaua.repository.BooksRepository;

@Service
public class LivrariaMauaEditService extends LivrariaMauaService {

	@Autowired
	BooksRepository booksRepository;
	
	@Transactional("livrariaMauaTransactionManager")
	public void editBook(Books book, Integer id) {
		validateId(id);
		validate(book, LivrariaMauaOperations.EDIT);
		booksRepository.updateBooks(book.getTitle(), book.getAuthor(), book.getQuantity(), book.getGender(), id);
	}
}
