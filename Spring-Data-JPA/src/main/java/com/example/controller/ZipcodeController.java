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

import com.example.dto.requestDto.ZipcodeRequestDto;
import com.example.entity.Response;
import com.example.service.ZipcodeService;

@RestController
@RequestMapping(path = "/zipcode")
public class ZipcodeController {
	
	@Autowired
	ZipcodeService service;
	
	
	@PostMapping(path = "/addZipcode")
	public ResponseEntity<Response> addZipcode(@RequestBody ZipcodeRequestDto zipcodeDto){
		Response response = service.addZipcode(zipcodeDto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(path = "/getAll")
	public ResponseEntity<Response> getAllZipcodes(){
		Response response = service.getAllZipcodes();
		return new ResponseEntity<>(response, HttpStatus.OK);		
	}

	@GetMapping(path = "/get/{id}")
	public ResponseEntity<Response> getZipcodeById(@PathVariable Long id){
		Response response = service.getZipcodeById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);		
	}
	
	@PutMapping(path = "/edit")
	public ResponseEntity<Response> editZipcode(@RequestBody ZipcodeRequestDto zipcodeDto, @RequestParam(name = "id") Long id){
		Response response = service.editZipcode(zipcodeDto, id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<Response> deleteZipcode(@PathVariable Long id){
		Response response = service.deleteZipcode(id);
		return new ResponseEntity<>(response, HttpStatus.OK);		
	}
	
	@PostMapping("/addCity/{cityId}/toZipCode/{zipcodeId}")
	public ResponseEntity<Response> addCity(@PathVariable Long cityId, @PathVariable Long zipcodeId){
		Response response = service.addCityToZipcode(cityId, zipcodeId);
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}
	
	@PostMapping("/removeCityFromZipcode/{zipcodeId}")
	public ResponseEntity<Response> removeCity(@PathVariable Long zipcodeId){
		Response response = service.removeCityFromZipcode(zipcodeId);
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}

}
