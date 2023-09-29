package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.Mapper;
import com.example.dto.requestDto.BookRequestDto;
import com.example.entity.Author;
import com.example.entity.Book;
import com.example.entity.Category;
import com.example.entity.Response;
import com.example.entity.ValidationException;
import com.example.repository.BookRepository;

import jakarta.transaction.Transactional;

@Service
public class BookService {

	private final BookRepository repository;
	private final AuthorService authorService;
	private final CategoryService categoryService;

	@Autowired
	public BookService(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
		this.repository = bookRepository;
		this.authorService = authorService;
		this.categoryService = categoryService;
	}

	@Transactional
	public Response addBook(BookRequestDto bookDto) {
		Response response = new Response();
		Book book = new Book();
		book.setName(bookDto.getName());
		if (bookDto.getAuthorIds().isEmpty()) {
			throw new ValidationException("You need at least one author");
		} else {
			List<Author> authors = new ArrayList<>();
			for (Long authorId : bookDto.getAuthorIds()) {
				Author author = authorService.getAuthor(authorId);
				authors.add(author);
			}
			book.setAuthors(authors);
		}
		if (bookDto.getCategoryId() == null) {
			throw new ValidationException("Book needs at least one category");
		}
		Category category = categoryService.getCategory(bookDto.getCategoryId());
		book.setCategory(category);
		repository.save(book);

		response.setData(Mapper.bookToBookResponseDto(book));
		return response;
	}

	public Response getAll() {
		Response response = new Response();
		List<Book> books = StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
		response.setData(Mapper.booksToBookResponseDto(books));
		return response;
	}

	public Response getBookById(Long id) {
		Response response = new Response();
		Book book = getBook(id);
		response.setData(Mapper.bookToBookResponseDto(book));
		return response;
	}

	public Book getBook(Long id) {
		if (id != null && id > 0) {
			Book book = repository.findById(id)
					.orElseThrow(() -> new ValidationException("cannot find book with id: " + id));
			return book;
		}
		throw new ValidationException("Id cant be null or zero");
	}

	@Transactional
	public Response editBook(BookRequestDto bookDto, Long id) {
		Response response = new Response();
		if (id != null && id > 0) {
			Book bookToEdit = getBook(id);
			bookToEdit.setName(bookDto.getName());
			if (!bookDto.getAuthorIds().isEmpty()) {
				List<Author> authors = new ArrayList<>();
				for (Long authorId : bookDto.getAuthorIds()) {
					Author author = authorService.getAuthor(authorId);
					authors.add(author);
				}
				bookToEdit.setAuthors(authors);
			}
			if (bookDto.getCategoryId() != null) {
				Category category = categoryService.getCategory(bookDto.getCategoryId());
				bookToEdit.setCategory(category);

			}
			response.setData(Mapper.bookToBookResponseDto(bookToEdit));
			return response;
		}
		throw new ValidationException("Id cant be null or zero");
	}

	public Response deleteBook(Long id) {
		Response response = new Response();
		if (id != null && id > 0) {
			Book book = getBook(id);
			repository.delete(book);
			return response;
		}
		throw new ValidationException("Id cant be null or zero");
	}

	@Transactional
	public Response addCategoryToBook(Long categoryId, Long bookId) {
		Response response = new Response();
		Book book = getBook(bookId);
		Category category = categoryService.getCategory(categoryId);
		if (Objects.nonNull(book.getCategory())) {
			throw new ValidationException("Book already has a category");
		}
		book.setCategory(category);
		category.addBook(book);
		response.setData(Mapper.bookToBookResponseDto(book));
		return response;
	}

	@Transactional
	public Response removeCategoryFromBook(Long categoryId, Long bookId) {
		Response response = new Response();
		Book book = getBook(bookId);
		Category category = categoryService.getCategory(categoryId);
		if (!(Objects.nonNull(book.getCategory()))) {
			throw new ValidationException("Book does not have a category to delete");
		}
		book.setCategory(null);
		category.addBook(book);
		return response;
	}

	@Transactional
	public Response addAuthorToBook(Long authorId, Long bookId) {
		Response response = new Response();
		Book book = getBook(bookId);
		Author author = authorService.getAuthor(authorId);
		if (author.getBooks().contains(book)) {
			throw new ValidationException("This author is already assigned to this book");
		}

		book.addAuthor(author);
		author.addBook(book);
		response.setData(Mapper.bookToBookResponseDto(book));
		return response;

	}

	@Transactional
	public Response removeAuthorFromBook(Long authorId, Long bookId) {
		Response response = new Response();
		Book book = getBook(bookId);
		Author author = authorService.getAuthor(authorId);
		if (!(author.getBooks().contains(book))) {
			throw new ValidationException("Book does not have this author");
		}
		author.removeBook(book);
		book.deleteAuthor(author);
		response.setData(Mapper.bookToBookResponseDto(book));
		return response;
	}

}
