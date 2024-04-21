package br.com.livrariamaua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.livrariamaua.domain.Books;
import br.com.livrariamaua.service.LivrariaMauaEditService;

@RestController
public class LivrariaMauaEditController {

	@Autowired
	LivrariaMauaEditService lmService;
	
	@PatchMapping(value = "/books/{id}")
	public ResponseEntity<Object> saveBooks(@RequestBody Books book, @PathVariable String id) {
		lmService.editBook(book, Integer.parseInt(id));
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
