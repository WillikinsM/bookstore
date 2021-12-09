package com.will.bookstoreapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.will.bookstoreapi.domain.Book;
import com.will.bookstoreapi.domain.Category;
import com.will.bookstoreapi.repository.BookRepository;
import com.will.bookstoreapi.resources.exceptions.ObjectNotFoundException;

@Service
public class BookService {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private BookRepository bookRepository;

	public Book findById(Integer id) {
		Optional<Book> book = bookRepository.findById(id);

		return book.orElseThrow(
				() -> new ObjectNotFoundException("Object not found " + id + ", type: " + Book.class.getName()));

	}

	public List<Book> findAll(Integer id_cat) {
		categoryService.findById(id_cat);
		return bookRepository.findByCategory(id_cat);
	}

	public Book create(Book book) {
		book.setId(null);
		return bookRepository.save(book);
	}

	public Book update(Integer id, Book book) {
		Book book1 = findById(id);
		book1.setAuthorName(book.getAuthorName());
		book1.setTitle(book.getTitle());
		book1.setText(book.getText());
		return bookRepository.save(book1);
	}

	public void delete(Integer id) {
		findById(id);
		bookRepository.deleteById(id);
	}

	public Book create(Integer id_cat, Book book) {
		book.setId(null);
		Category cat = categoryService.findById(id_cat);
		book.setCategory(cat);
		return bookRepository.save(book);
	}
}
