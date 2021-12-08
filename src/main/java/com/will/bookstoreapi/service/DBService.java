package com.will.bookstoreapi.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.will.bookstoreapi.domain.Book;
import com.will.bookstoreapi.domain.Category;
import com.will.bookstoreapi.repository.BookRepository;
import com.will.bookstoreapi.repository.CategoryRepository;

@Service
public class DBService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private BookRepository bookRepository;

	public void initDataBase() {

		Category cat1 = new Category(null, "Informática", "Livros de TI");
		Category cat2 = new Category(null, "Ficcção Científica", "Ficcção científica");
		Category cat3 = new Category(null, "Biografias", "Livros de Biografias");

		Book book1 = new Book(null, "Clean code", "Robert Martin", "Lorem ipsum", cat1);
		Book book2 = new Book(null, "Engenharia de Software", "Louis V. Gerstner", "Lorem ipsum", cat1);
		Book book3 = new Book(null, "The Time Machine", "H.G. Wells", "Lorem ipsum", cat2);
		Book book4 = new Book(null, "The War of the Worlds", "H.G. Wells", "Lorem ipsum", cat2);
		Book book5 = new Book(null, "I, Robot", "Isaac Asimov", "Lorem ipsum", cat2);
		Book book6 = new Book(null, "Steve Jobs", "Walter Isaacson", "Lorem ipsum", cat3);

		cat1.getBook().addAll(Arrays.asList(book1, book2));
		cat2.getBook().addAll(Arrays.asList(book3, book4, book5));
		cat3.getBook().addAll(Arrays.asList(book6));

		this.categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		this.bookRepository.saveAll(Arrays.asList(book1, book2, book3, book4, book5, book6));

	}

}
