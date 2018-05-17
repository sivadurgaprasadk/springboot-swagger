package com.boot.swagger.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.boot.swagger.error.ResourceNotFoundException;
import com.boot.swagger.model.Book;

@Service
public class BookStoreService {

	private List<Book> books = null;

	public BookStoreService() {

		books = new ArrayList<>();
		books.addAll(Arrays.asList(
			new Book(1, "Romeo and Juliet-Pocket edition", "William Shakespeare", "Fingerprint! Publishing","English", 94),
				new Book(2, "Such Is Her Life", "Reecha Agarwal Goyal", "Finger Print", "English", 143),
				new Book(3, "The Great Gatsby-Pocket edition", "F Scott Fitzgerald", "Fingerprint! Publishing","English", 94),
				new Book(4, "The Jungle Book-Pocket edition", "	Rudyard Kipling", "Fingerprint! Publishing", "English",94),
				new Book(5, "Kat Wolfe Investigates", "Lauren St John", "Macmillan Childrens Books", "English", 323))
			);
	}

	public Book getBook(int id) {
		Optional<Book> optional = books.parallelStream().filter(e -> e.getId() == id).findFirst();
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new ResourceNotFoundException("Book id not available in database");
		}
	}

	public List<Book> getAllBooks() {
		return books;
	}

	public void save(Book book) {
		books.add(book);
	}

	public void update(Book book) {
		books.set(books.indexOf(getBook(book.getId())), book);
	}

	public void delete(int id) {
		if (books.remove(getBook(id)) == false) {
			throw new RuntimeException("Book with this id already deleted");
		}
	}
}
