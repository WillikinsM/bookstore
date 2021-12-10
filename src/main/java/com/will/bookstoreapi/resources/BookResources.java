package com.will.bookstoreapi.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/books")
@Api(value = "Books")
@CrossOrigin(origins = "*")
public class BookResources {

	@Autowired
	private BookService bookService;

	@ApiOperation(value = "Find book by given ID")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Book> findById(@PathVariable Integer id) {
		Book book = bookService.findById(id);
		return ResponseEntity.ok().body(book);
	}

	//// books?category={id}
	@ApiOperation(value = "Find books by category ID")
	@GetMapping
	public ResponseEntity<List<BookDTO>> findAllbyCategory(
			@RequestParam(value = "category", defaultValue = "0") Integer id_cat) {

		List<Book> bookList = bookService.findAll(id_cat);
		List<BookDTO> dtoList = bookList.stream().map(obj -> new BookDTO(obj)).collect(Collectors.toList());

		return ResponseEntity.ok().body(dtoList);
	}

	@ApiOperation(value = "Find all Books")
	@GetMapping(value = "/")
	public ResponseEntity<List<BookDTO>> findAll() {
		List<Book> bookList = bookService.findall();
		List<BookDTO> dtoList = bookList.stream().map(obj -> new BookDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(dtoList);
	}

	@ApiOperation(value = "Create a new Book")
	@PostMapping(value = "/{id}")
	public ResponseEntity<Book> create(@Valid @RequestBody Book book,
			@RequestParam(value = "category", defaultValue = "0") Integer id_cat) {

		Book newBook = bookService.create(id_cat, book);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newBook.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Update a existing Book")
	@PutMapping(value = "/{id}")
	public ResponseEntity<Book> update(@PathVariable Integer id, @Valid @RequestBody Book book) {

		Book newBook = bookService.update(id, book);

		return ResponseEntity.ok().body(newBook);

	}

	@ApiOperation(value = "Delete a existing Book")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		bookService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
