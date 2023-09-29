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

import com.example.dto.requestDto.AuthorRequestDto;
import com.example.entity.Response;
import com.example.service.AuthorService;

@RestController
@RequestMapping(path = "/author")
public class AuthorController {
	
	@Autowired
	private AuthorService service;
	
	@PostMapping(path = "/addAuthor")
	public ResponseEntity<Response> addAuthor(@RequestBody AuthorRequestDto authorDto){
		Response response = service.addAuthor(authorDto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(path = "/getAll")
	public ResponseEntity<Response> getAll(){
		Response response = service.getAll();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(path = "/get/{id}")
	public ResponseEntity<Response> getAuthorById(@PathVariable Long id){
		Response response = service.getAuthorById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);		
	}
	
	@PutMapping(path = "/edit")
	public ResponseEntity<Response> editAuthor(@RequestBody AuthorRequestDto authorDto, @RequestParam(name = "id") Long id){
		Response response = service.editAuthor(authorDto, id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<Response> deleteAuthor(@PathVariable Long id){
		Response response = service.deleteAuthor(id);
		return new ResponseEntity<>(response, HttpStatus.OK);		
	}
	
	@PostMapping(path = "/addZipcode/{zipcodeId}/toAuthor/{authorId}")
	public ResponseEntity<Response> addZipcode(@PathVariable Long zipcodeId, @PathVariable Long authorId){
		Response response = service.addZipcodeToAuthor(zipcodeId, authorId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/removeZipcode/{authorId}")
	public ResponseEntity<Response> removeZipcode(@PathVariable Long authorId){
		Response response = service.removeZipcodeFromAuthor(authorId);
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}

}
