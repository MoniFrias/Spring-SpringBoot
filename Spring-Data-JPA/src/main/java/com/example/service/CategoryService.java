package com.example.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.Mapper;
import com.example.dto.requestDto.CategoryRequestDto;
import com.example.entity.Category;
import com.example.entity.Response;
import com.example.entity.ValidationException;
import com.example.repository.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository repository;
	
	
	public Response addCategory(CategoryRequestDto categoryDto) {
		Response response = new Response();
		Category category = new Category();
		category.setName(categoryDto.getName());
		repository.save(category);
		response.setData(Mapper.categoryToCategoryResponseDto(category));
		return response;
	}

	public Response getAll() {
		Response response = new Response();
		List<Category> categories = StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
		response.setData(Mapper.categoriesToCategoryResponseDtos(categories));
		return response;
	}

	public Response getCategoryById(Long id) {
		Response response = new Response();
		Category category = getCategory(id);
		response.setData(Mapper.categoryToCategoryResponseDto(category));
		return response;
	}

	public Category getCategory(Long categoryId) {
		return repository.findById(categoryId).orElseThrow(() ->
		new ValidationException("Could not find category with Id: " + categoryId));
	}

	@Transactional
	public Response editCategory(CategoryRequestDto categoryDto, Long id) {
		Response response = new Response();
		Category categoryToEdit = getCategory(id);
		categoryToEdit.setName(categoryDto.getName());
		response.setData(Mapper.categoryToCategoryResponseDto(categoryToEdit));
		return response;
	}

	public Response deleteCategory(Long id) {
		Response response = new Response();
		Category category = getCategory(id);
		repository.delete(category);
		return response;
	}

}
