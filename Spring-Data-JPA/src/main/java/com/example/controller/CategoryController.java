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

import com.example.dto.requestDto.CategoryRequestDto;
import com.example.entity.Response;
import com.example.service.CategoryService;

@RestController
@RequestMapping(path = "/category")
public class CategoryController {
	
	@Autowired
	CategoryService service;

	@PostMapping(path = "/addCategory")
	public ResponseEntity<Response> addCategory(@RequestBody CategoryRequestDto categoryDto){
		Response response = service.addCategory(categoryDto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(path = "/getAll")
	public ResponseEntity<Response> getAll(){
		Response response = service.getAll();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(path = "/get/{id}")
	public ResponseEntity<Response> getCategoryById(@PathVariable Long id){
		Response response = service.getCategoryById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);		
	}
	
	@PutMapping(path = "/edit")
	public ResponseEntity<Response> editCategory(@RequestBody CategoryRequestDto categoryDto, @RequestParam(name = "id") Long id){
		Response response = service.editCategory(categoryDto, id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<Response> deleteCategory(@PathVariable Long id){
		Response response = service.deleteCategory(id);
		return new ResponseEntity<>(response, HttpStatus.OK);		
	}
	
}
