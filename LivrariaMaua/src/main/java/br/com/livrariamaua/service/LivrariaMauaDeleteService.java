package br.com.livrariamaua.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.livrariamaua.repository.BooksRepository;

@Service
public class LivrariaMauaDeleteService {

	@Autowired
	BooksRepository booksRepository;

	@Transactional("livrariaMauaTransactionManager")
	public void deleteBook(Integer id) {
		booksRepository.deleteById(id);
	}
}
