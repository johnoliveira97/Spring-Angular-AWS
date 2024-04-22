package br.com.livrariamaua.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public Iterable<Books> getBooks() {
	    return lmService.findAll();
	  }
	
	@GetMapping(value = "/books/index/{id}")
	  public Books getBookById(@PathVariable Integer id) throws Exception {
	    return lmService.findBook(id);
	  }
	
	@GetMapping("/books/author/{author}")
	  public List<Books> getBooksByAuthor(@PathVariable String author) throws Exception {
	    return lmService.findBooksByAuthor(author);
	  }
	
	@GetMapping("/books/title/{title}")
	  public List<Books> getBooksByTitle(@PathVariable String title) throws Exception {
	    return lmService.findBooksByTitle(title);
	  }
	
	@GetMapping("/books/gender/{gender}")
	  public List<Books> getBooksByGender(@PathVariable String gender) throws Exception {
	    return lmService.findBooksByGender(gender);
	  }
}
