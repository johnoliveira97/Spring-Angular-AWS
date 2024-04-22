package br.com.livrariamaua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.livrariamaua.service.LivrariaMauaDeleteService;

@RestController
public class LivrariaMauaDeleteController {

	@Autowired
	LivrariaMauaDeleteService lmService;
	
	@DeleteMapping(value = "/books/{id}")
	public ResponseEntity<Object> deleteBooks(@PathVariable String id) {
		lmService.deleteBook(Integer.parseInt(id));
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
