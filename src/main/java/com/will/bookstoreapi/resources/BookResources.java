package com.will.bookstoreapi.resources;

import com.will.bookstoreapi.domain.Book;
import com.will.bookstoreapi.dtos.BookDTO;
import com.will.bookstoreapi.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/books")
@Api(value = "Books")
@CrossOrigin("*")
public class BookResources {
    private final BookService bookService;

    @Autowired
    public BookResources(BookService bookService) {
        this.bookService = bookService;
    }



    @ApiOperation(value = "Find book by given ID")
    //@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        Book book = bookService.findById(id);
        return ResponseEntity.ok().body(book);
    }

    //// books?category={id}
    @ApiOperation(value = "Find books by category ID")
    //@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<BookDTO>> findAllByCategory(
            @RequestParam(value = "category", defaultValue = "0") Long id_cat) {

        List<Book> bookList = bookService.findAllByCat(id_cat);
        List<BookDTO> dtoList = bookList.stream().map(BookDTO::new).collect(Collectors.toList());

        return ResponseEntity.ok().body(dtoList);
    }

    @ApiOperation(value = "Find all Books")
    //@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(value = "/")
    public ResponseEntity<List<BookDTO>> findAll() {
        List<Book> bookList = bookService.findAll();
        List<BookDTO> dtoList = bookList.stream().map(BookDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(dtoList);
    }

    @ApiOperation(value = "Create a new Book")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value ="/admin/create")
    public ResponseEntity<Book> create(@Valid @RequestBody Book book,
                                       @RequestParam(value = "category", defaultValue = "0") Long id_cat) {

        Book newBook = bookService.create(id_cat, book);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(newBook.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "Update a existing Book")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/admin/update/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @Valid @RequestBody Book book) {
        Book newBook = bookService.update(id, book);
        return ResponseEntity.ok().body(newBook);
    }

    @ApiOperation(value = "Delete a existing Book")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/admin/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

}