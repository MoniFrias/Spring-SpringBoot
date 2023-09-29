package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.requestDto.ZipcodeRequestDto;
import com.example.entity.City;
import com.example.entity.Response;
import com.example.entity.ValidationException;
import com.example.entity.Zipcode;
import com.example.repository.ZipcodeRepository;

import jakarta.transaction.Transactional;

@Service
public class ZipcodeService {
	
	@Autowired
	ZipcodeRepository repository;
	
	@Autowired
	CityService cityService;
	
	@Autowired
	CityService ciryService;

	@Transactional
	public Response addZipcode(ZipcodeRequestDto zipcodeDto) {
		Response response =  new Response();
		Zipcode zipcode = new Zipcode();
		zipcode.setName(zipcodeDto.getName());
		if(zipcodeDto.getCityId() == null) {
			response.setData(repository.save(zipcode));
			return response;
		}
		City city = ciryService.getCity(zipcodeDto.getCityId());
		zipcode.setCity(city);
		response.setData(repository.save(zipcode));
		return response;
	}

	public Response getAllZipcodes() {
		Response response =  new Response();
		List<Zipcode> zipcodes = new ArrayList<>();
		repository.findAll().forEach(zipcodes::add);
		response.setData(zipcodes);
		return response;
	}

	public Response getZipcodeById(Long id) {
		Response response =  new Response();
		if (id != null && id > 0) {
			Optional<Zipcode> zipcode = repository.findById(id);
			if (!zipcode.isPresent()) {
				throw new ValidationException("No zipcode with that ID");
			}
			response.setData(zipcode);
			return response;
		}
		throw new ValidationException("Id can't be null or zero");
	}
	
	public Zipcode getZipcode(Long id) {
		if (id != null && id > 0) {
			return repository.findById(id)
					.orElseThrow(() -> new ValidationException("Zipcode with Id: " + id + " could not be found"));
		}
		throw new ValidationException("Id can't be null or zero");
	}

	public Response deleteZipcode(Long id) {
		Response response = new Response();
		if(id != null && id > 0) {
			Zipcode zipcodeToDelete = getZipcode(id);
			if(zipcodeToDelete != null) {
				repository.delete(zipcodeToDelete);
				return response;
			}
			throw new ValidationException("No zipcode with that Id");
		}
		throw new ValidationException("Id can't be null or zero");
	}
	
	@Transactional
	public Response editZipcode(ZipcodeRequestDto zipcodeDto, Long id) {
		Response response = new Response();
		if(id != null && id > 0) {
			Zipcode zipcodeToEdit = getZipcode(id);
			if(zipcodeToEdit != null) {
				zipcodeToEdit.setName(zipcodeDto.getName());
				if(zipcodeDto.getCityId() != null) {
					response.setData(zipcodeToEdit);
					return response;
				}
				City city = ciryService.getCity(zipcodeDto.getCityId());
				zipcodeToEdit.setCity(city);
				response.setData(zipcodeToEdit);
				return response;
			}
			throw new ValidationException("No city with that Id");
		}
		throw new ValidationException("Id can't be null or zero");
	}

	@Transactional
	public Response addCityToZipcode(Long cityId, Long zipcodeId) {
		Response response = new Response();
		Zipcode zipcode = getZipcode(zipcodeId);
		City city = ciryService.getCity(cityId);
		if(Objects.nonNull(zipcode.getCity())) {
			throw new ValidationException("Zipcode already has a city");
		}
		zipcode.setCity(city);
		response.setData(zipcode);
		return response;
	}

	@Transactional
	public Response removeCityFromZipcode(Long zipcodeId) {
		Response response = new Response();
		Zipcode zipcode = getZipcode(zipcodeId);
		if(!Objects.nonNull(zipcode.getCity())) {
			throw new ValidationException("Zipcode doesn not have a city");
		}
		zipcode.setCity(null);
		return response;
	}

}
