package com.boot.swagger.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.boot.swagger.error.Error;
import com.boot.swagger.error.ResourceNotFoundException;
import com.boot.swagger.model.Book;
import com.boot.swagger.service.BookStoreService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import springfox.documentation.service.ApiKey;

@Api(description = "Crud Operations on BookStore resource")
@RestController
@ControllerAdvice
@RequestMapping(value = "/book")
public class BookStore extends ResponseEntityExceptionHandler {

	@Autowired
	private BookStoreService bookStoreService;

	@ApiOperation(value = "Endpoint for getting a book details by passing id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved Book details", response = Book.class),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
	@GetMapping(value = "/id/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public Book getBook(
			@ApiParam(value = "Book id required for getting book details", required = true) @PathVariable int id) {

		return bookStoreService.getBook(id);
	}

	@ApiOperation(value = "Endpint for getting books list", response = Book.class, responseContainer = "List")

	@ApiImplicitParams({

			@ApiImplicitParam(name = "authentication", value = "bearer token", required = true, dataType = "string", paramType = "header") })

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved All Books details"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping(value = "/all", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<Book> getAllBooks() {
		return bookStoreService.getAllBooks();
	}

	@ApiOperation(value = "Endpoint for Save a book", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully save a Book details"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource", response = ApiKey.class),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden") })
	@PostMapping(value = "/save", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> save(@ApiParam(value = "Book required for storing a book") @RequestBody Book book) {
		bookStoreService.save(book);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "Endpoint for updating a book", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully update a Book details"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource", response = ApiKey.class),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden") })
	@PutMapping(value = "/update", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> update(
			@ApiParam(value = "Book required for updating a book", required = true) @RequestBody Book book) {
		bookStoreService.update(book);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "Endpoint for deleting a book", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully delete a Book details"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource", response = ApiKey.class),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden") })
	@DeleteMapping(value = "/delete/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> delete(
			@ApiParam(value = "Book id required for deleting a book", required = true) @PathVariable int id) {
		bookStoreService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Error> restExceptions(ResourceNotFoundException ex) {
		Error error = new Error("e1002", "siva rest exception");
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
