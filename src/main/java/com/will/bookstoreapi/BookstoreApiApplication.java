package com.will.bookstoreapi;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.will.bookstoreapi.domain.Book;
import com.will.bookstoreapi.domain.Category;
import com.will.bookstoreapi.repository.BookRepository;
import com.will.bookstoreapi.repository.CategoryRepository;

@SpringBootApplication
public class BookstoreApiApplication implements CommandLineRunner {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Category cat1 = new Category(null, "Informática", "Livros de TI");
		Book book1 = new Book(null, "Clean Code", "Robert Martin", "Lorem ipsum", cat1);

		cat1.getBook().addAll(Arrays.asList(book1));

		this.categoryRepository.saveAll(Arrays.asList(cat1));
		this.bookRepository.saveAll(Arrays.asList(book1));
	}

}
