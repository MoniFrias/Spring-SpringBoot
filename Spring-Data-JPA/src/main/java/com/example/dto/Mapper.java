package com.example.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.dto.responseDto.AuthorResponseDto;
import com.example.dto.responseDto.BookResponseDto;
import com.example.dto.responseDto.CategoryResponseDto;
import com.example.entity.Author;
import com.example.entity.Book;
import com.example.entity.Category;
import com.example.entity.Zipcode;

public class Mapper {
	
	public static AuthorResponseDto authorToAuthorResponseDTo(Author author) {
		AuthorResponseDto authorResponseDto = new AuthorResponseDto();
		authorResponseDto.setId(author.getId());
		authorResponseDto.setName(author.getName());
		authorResponseDto.setLastName(author.getLastName());
		List<String> bookNames = new ArrayList<>();
		List<Book> books = author.getBooks();
		Zipcode zipcode = author.getZipCode();
		if(Objects.isNull(books)) {
			authorResponseDto.setBookNames(new ArrayList<>());
			authorResponseDto.setZipcodeName(zipcode.getName());
			return authorResponseDto;
		}
		for(Book book : books) {
			bookNames.add(book.getName());
		}
		authorResponseDto.setBookNames(bookNames);
		authorResponseDto.setZipcodeName(zipcode.getName());
		return authorResponseDto;
	}
	
	public static List<AuthorResponseDto> authorsToAuthorRequestDto(List<Author> authors){
		List<AuthorResponseDto> authorsResponseDto = new ArrayList<>();
		for(Author author : authors) {
			authorsResponseDto.add(authorToAuthorResponseDTo(author));
		}
		return authorsResponseDto;
	}

	public static BookResponseDto bookToBookResponseDto(Book book) {
		BookResponseDto bookResponseDto = new BookResponseDto();
		bookResponseDto.setId(book.getId());
		bookResponseDto.setName(book.getName());
		bookResponseDto.setCategoryName(book.getCategory().getName());
		List<String> names = new ArrayList<>();
        List<Author> authors = book.getAuthors();
        if(Objects.isNull(authors)) {
        	bookResponseDto.setAuthorNames(new ArrayList<>());
            return bookResponseDto;
        }
        for (Author author: authors) {
            names.add(author.getName());
        }
        bookResponseDto.setAuthorNames(names);
        return bookResponseDto;
	}

	public static Object booksToBookResponseDto(List<Book> books) {
		List<BookResponseDto> booksResponseDto = new ArrayList<>();
		for(Book book : books) {
			booksResponseDto.add(bookToBookResponseDto(book));
		}
		return booksResponseDto;		
	}
	
	public static CategoryResponseDto categoryToCategoryResponseDto(Category category) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(category.getId());
        categoryResponseDto.setName(category.getName());
        List<String> names = new ArrayList<>();
        List<Book> books = category.getBooks();
        if(Objects.isNull(books)) {
        	categoryResponseDto.setBookNames(new ArrayList<>());
            return categoryResponseDto;
        }
        for (Book book : books) {
            names.add(book.getName());
        }
        categoryResponseDto.setBookNames(names);
        return categoryResponseDto;
    }

    public static List<CategoryResponseDto> categoriesToCategoryResponseDtos(List<Category> categories) {
        List<CategoryResponseDto> categoryResponseDtos = new ArrayList<>();
        for (Category category: categories) {
            categoryResponseDtos.add(categoryToCategoryResponseDto(category));
        }
        return categoryResponseDtos;
    }
	
	

}
