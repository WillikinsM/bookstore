package com.will.bookstoreapi.service;

import com.will.bookstoreapi.domain.Book;
import com.will.bookstoreapi.domain.Category;
import com.will.bookstoreapi.repository.BookRepository;
import com.will.bookstoreapi.resources.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryService categoryService;

    @Autowired
    public BookService(BookRepository bookRepository,CategoryService categoryService){
        this.bookRepository = bookRepository;
        this.categoryService = categoryService;
    }


    public Book findById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElseThrow(
                () -> new ObjectNotFoundException("Object not found " +
                        id + ", type: " + Book.class.getName()));

    }

    public List<Book> findAllByCat(Long id_cat) {
        categoryService.findById(id_cat);
        return bookRepository.findByCategory(id_cat);
    }

    public Book create(Book book) {
        book.setId(null);
        return bookRepository.save(book);
    }

    public Book update(Long id, Book book) {
        Book book1 = findById(id);
        book1.setAuthorName(book.getAuthorName());
        book1.setTitle(book.getTitle());
        book1.setText(book.getText());
        return bookRepository.save(book1);
    }

    public void delete(Long id) {
        findById(id);
        bookRepository.deleteById(id);
    }

    public Book create(Long id_cat, Book book) {
        book.setId(null);
        Category cat = categoryService.findById(id_cat);
        book.setCategory(cat);
        return bookRepository.save(book);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

}