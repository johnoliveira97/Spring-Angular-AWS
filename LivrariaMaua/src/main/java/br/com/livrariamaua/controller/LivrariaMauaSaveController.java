package br.com.livrariamaua.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.livrariamaua.domain.Books;
import br.com.livrariamaua.service.LivrariaMauaSaveService;

@RestController
public class LivrariaMauaSaveController {

	@Autowired
	LivrariaMauaSaveService lmService;

	@PostMapping(value = "/books")
	public ResponseEntity<Object> saveBooks(@RequestBody Books book) {
		return ResponseEntity.status(HttpStatus.CREATED).body(lmService.saveBook(book));
	}

	@PostMapping(value = "/books/list")
	public ResponseEntity<Object> saveBooks(@RequestBody List<Books> book) {
		return ResponseEntity.status(HttpStatus.CREATED).body(lmService.saveBooks(book));
	}
}
