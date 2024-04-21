package br.com.livrariamaua.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.livrariamaua.domain.Books;
import br.com.livrariamaua.service.LivrariaMauaGetService;

@RestController
public class LivrariaMauaGetController {

	@Autowired
	LivrariaMauaGetService lmService;
	
	@GetMapping(value = "/books")
	public ResponseEntity<Object> getBooks() {
	    return ResponseEntity.of(Optional.ofNullable((lmService.findAll())));
	  }
	
	@GetMapping(value = "/books/index/{id}")
	  public Books getBookById(@PathVariable Integer id) throws Exception {
	    return lmService.findBook(id);
	  }
	
	@GetMapping("/books/author/{author}")
	  public Books getBookByAuthor(@PathVariable String author) throws Exception {
	    return lmService.findBookByAuthor(author);
	  }
	
	@GetMapping("/books/title/{title}")
	  public Books getBookByTitle(@PathVariable String title) throws Exception {
	    return lmService.findBookByTitle(title);
	  }
}
