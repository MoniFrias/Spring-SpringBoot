package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.requestDto.BookRequestDto;
import com.example.entity.Response;
import com.example.service.BookService;

@RestController
@RequestMapping(path = "/book")
public class BookController {
	
	@Autowired
	private BookService service;
	
	@PostMapping(path = "/addBook")
	public ResponseEntity<Response> addBook(@RequestBody BookRequestDto bookDto){
		Response response = service.addBook(bookDto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(path = "/getAll")
	public ResponseEntity<Response> getAll(){
		Response response = service.getAll();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(path = "/get/{id}")
	public ResponseEntity<Response> getBookById(@PathVariable Long id){
		Response response = service.getBookById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);		
	}
	
	@PutMapping(path = "/edit")
	public ResponseEntity<Response> editBook(@RequestBody BookRequestDto bookDto, @RequestParam(name = "id") Long id){
		Response response = service.editBook(bookDto, id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<Response> deleteBook(@PathVariable Long id){
		Response response = service.deleteBook(id);
		return new ResponseEntity<>(response, HttpStatus.OK);		
	}
	
	@PostMapping(path = "/addCategory/{categoryId}/toBook/{bookId}")
	public ResponseEntity<Response> addCategory(@PathVariable Long categoryId, @PathVariable Long bookId){
		Response response = service.addCategoryToBook(categoryId, bookId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/removeCategory/{categoryId}/from/{bookId}")
	public ResponseEntity<Response> removeZipcode(@PathVariable Long categoryId, @PathVariable Long bookId){
		Response response = service.removeCategoryFromBook(categoryId, bookId);
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}
	
	@PostMapping(path = "/addAuthor/{authorId}/toBook/{bookId}")
	public ResponseEntity<Response> addAuthor(@PathVariable Long authorId, @PathVariable Long bookId){
		Response response = service.addAuthorToBook(authorId, bookId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/removeAuthor/{authorId}/from/{bookId}")
	public ResponseEntity<Response> removeAuthor(@PathVariable Long authorId, @PathVariable Long bookId){
		Response response = service.removeAuthorFromBook(authorId, bookId);
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}

}
