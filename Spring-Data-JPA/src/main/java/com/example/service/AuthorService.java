package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.Mapper;
import com.example.dto.requestDto.AuthorRequestDto;
import com.example.entity.Author;
import com.example.entity.Response;
import com.example.entity.ValidationException;
import com.example.entity.Zipcode;
import com.example.repository.AuthorRepository;

import jakarta.transaction.Transactional;

@Service
public class AuthorService {

	@Autowired
	AuthorRepository repository;

	@Autowired
	ZipcodeService zipcodeService;

	@Transactional
	public Response addAuthor(AuthorRequestDto authorDto) {
		Response response = new Response();
		Author author = new Author();
		author.setName(authorDto.getName());
		author.setLastName(authorDto.getLastName());
		if (authorDto.getZipcodeId() == null) {
			throw new ValidationException("Author need a zipcodeID");
		}
		Zipcode zipcode = zipcodeService.getZipcode(authorDto.getZipcodeId());
		author.setZipCode(zipcode);
		repository.save(author);

		response.setData(Mapper.authorToAuthorResponseDTo(author));
		return response;
	}

	public Response getAll() {
		Response response = new Response();
		List<Author> authors = new ArrayList<>();
		repository.findAll().forEach(authors::add);

		response.setData(Mapper.authorsToAuthorRequestDto(authors));
		return response;
	}

	public Response getAuthorById(Long id) {
		Response response = new Response();
		if (id != null && id > 0) {
			response.setData(Mapper.authorToAuthorResponseDTo(getAuthor(id)));
			return response;
		}
		throw new ValidationException("Id can't be null or zero");
	}

	public Author getAuthor(Long id) {
		if (id != null && id > 0) {
			Author author = repository.findById(id)
					.orElseThrow(() -> new ValidationException("Author with Id: " + id + " could not be found"));
			return author;
		}
		throw new ValidationException("Id can't be null or zero");
	}

	@Transactional
	public Response editAuthor(AuthorRequestDto authorDto, Long id) {
		Response response = new Response();
		if (id != null && id > 0) {
			Author authorToEdit = getAuthor(id);
			authorToEdit.setName(authorDto.getName());
			authorToEdit.setLastName(authorDto.getLastName());
			if (authorDto.getZipcodeId() != null) {
				Zipcode zipcode = zipcodeService.getZipcode(authorDto.getZipcodeId());
				authorToEdit.setZipCode(zipcode);
			}
			response.setData(Mapper.authorToAuthorResponseDTo(authorToEdit));
			return response;
		}
		throw new ValidationException("Id cant be null or zero");
	}

	public Response deleteAuthor(Long id) {
		Response response = new Response();
		if (id != null && id > 0) {
			Author author = getAuthor(id);
			repository.deleteById(id);
			return response;
		}
		throw new ValidationException("Id cant be null or zero");
	}

	@Transactional
	public Response addZipcodeToAuthor(Long zipcodeId, Long authorId) {
		Response response = new Response();
		if (zipcodeId != null && zipcodeId > 0 && authorId != null && authorId > 0) {
			Author author = getAuthor(authorId);
			Zipcode zipcode = zipcodeService.getZipcode(zipcodeId);
			if(Objects.nonNull(author.getZipCode())) {
				throw new ValidationException("Author already has a zipcode");
			}
			author.setZipCode(zipcode);
			response.setData(Mapper.authorToAuthorResponseDTo(author));
			return response;
		}
		throw new ValidationException("Id cant be null or zero");
	}

	@Transactional
	public Response removeZipcodeFromAuthor(Long authorId) {
		Response response = new Response();
		if (authorId != null && authorId > 0) {
			Author author = getAuthor(authorId);
			author.setZipCode(null);
			return response;
		}
		throw new ValidationException("Id cant be null or zero");
		
	}

}
