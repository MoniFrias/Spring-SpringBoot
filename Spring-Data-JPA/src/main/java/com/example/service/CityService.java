package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.requestDto.CityRequestDto;
import com.example.entity.City;
import com.example.entity.Response;
import com.example.entity.ValidationException;
import com.example.repository.CityRepository;

import jakarta.transaction.Transactional;

@Service
public class CityService {

	@Autowired
	CityRepository repository;

	public Response addCity(CityRequestDto cityDto) {
		Response response = new Response();
		City city = new City();
		city.setName(cityDto.getName());
		response.setData(repository.save(city));
		return response;
	}

	public Response getAll() {
		Response response = new Response();
		List<City> cities = new ArrayList<>();
		repository.findAll().forEach(cities::add);
		response.setData(cities);
		return response;
	}

	public Response getCityById(Long id) {
		Response response = new Response();
		if (id != null && id > 0) {
			Optional<City> city = repository.findById(id);
			if (!city.isPresent()) {
				throw new ValidationException("No city with that ID");
			}
			response.setData(city);
			return response;
		}
		throw new ValidationException("Id can't be null or zero");
	}
	
	 public City getCity(Long cityId) {
	        return repository.findById(cityId).orElseThrow(() ->
	                new IllegalArgumentException("City with cityId: " + cityId + " could not be found"));
	    }

	@Transactional
	public Response editCity(CityRequestDto cityDto, Long id) {
		Response response = new Response();
		if(id != null && id > 0) {
			City cityToEdit = getCity(id);
			if(cityToEdit != null) {
				cityToEdit.setName(cityDto.getName());
				response.setData(cityToEdit);
				return response;
			}
			throw new ValidationException("No city with that Id");
		}
		throw new ValidationException("Id can't be null or zero");
	}

	public Response deleteCity(Long id) {
		Response response = new Response();
		if(id != null && id > 0) {
			City cityToDelete = getCity(id);
			if(cityToDelete != null) {
				repository.deleteById(id);
				return response;
			}
			throw new ValidationException("No city with that Id");
		}
		throw new ValidationException("Id can't be null or zero");
	}

}
