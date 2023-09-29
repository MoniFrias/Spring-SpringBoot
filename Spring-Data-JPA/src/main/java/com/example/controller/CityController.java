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

import com.example.dto.requestDto.CityRequestDto;
import com.example.entity.Response;
import com.example.service.CityService;

@RestController
@RequestMapping(path = "/city")
public class CityController {
	
	@Autowired
	CityService service;
	
	
	@PostMapping(path = "/addCity")
	public ResponseEntity<Response> addCity(@RequestBody CityRequestDto cityDto){
		Response response = service.addCity(cityDto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(path = "/getAll")
	public ResponseEntity<Response> getAll(){
		Response response = service.getAll();
		return new ResponseEntity<>(response, HttpStatus.OK);		
	}

	@GetMapping(path = "/get/{id}")
	public ResponseEntity<Response> getCityById(@PathVariable Long id){
		Response response = service.getCityById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);		
	}
	
	@PutMapping(path = "/edit")
	public ResponseEntity<Response> editCity(@RequestBody CityRequestDto cityDto, @RequestParam(name = "id") Long id){
		Response response = service.editCity(cityDto, id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<Response> deleteCity(@PathVariable Long id){
		Response response = service.deleteCity(id);
		return new ResponseEntity<>(response, HttpStatus.OK);		
	}
}
