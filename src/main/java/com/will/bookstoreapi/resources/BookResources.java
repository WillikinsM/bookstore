package com.will.bookstoreapi.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.will.bookstoreapi.domain.Book;
import com.will.bookstoreapi.dtos.BookDTO;
import com.will.bookstoreapi.service.BookService;

@RestController
@RequestMapping(value = "/books")
public class BookResources {

	@Autowired
	private BookService bookService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Book> findById(@PathVariable Integer id) {
		Book book = bookService.findById(id);
		return ResponseEntity.ok().body(book);
	}

	@GetMapping
	public ResponseEntity<List<BookDTO>> findAll(@RequestParam(value = "category", defaultValue = "0") Integer id_cat) {

		List<Book> bookList = bookService.findAll(id_cat);
		List<BookDTO> dtoList = bookList.stream().map(obj -> new BookDTO(obj)).collect(Collectors.toList());

		return ResponseEntity.ok().body(dtoList);
	}

	@PostMapping(value = "/{id}")
	public ResponseEntity<Book> create(@RequestBody Book book,
			@RequestParam(value = "category", defaultValue = "0") Integer id_cat) {

		Book newBook = bookService.create(id_cat, book);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newBook.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Book> update(@PathVariable Integer id, @RequestBody Book book) {

		Book newBook = bookService.update(id, book);

		return ResponseEntity.ok().body(newBook);

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		bookService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
