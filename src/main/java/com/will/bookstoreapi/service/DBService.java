package com.will.bookstoreapi.service;

import com.will.bookstoreapi.domain.Book;
import com.will.bookstoreapi.domain.Category;
import com.will.bookstoreapi.repository.BookRepository;
import com.will.bookstoreapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;

    @Autowired
    public DBService(CategoryRepository categoryRepository, BookRepository bookRepository) {
        this.categoryRepository = categoryRepository;
        this.bookRepository = bookRepository;
    }

    public void initDataBase() {

        Category cat1 = new Category(null, "Computing", "IT Books",
                "https://www.valentir.com.br/wp-content/uploads/2018/11/binary-code-475664_640.jpg");

        Category cat2 = new Category(null, "Science Fiction", "Science Fiction Books",
                "https://1.bp.blogspot.com/-fOGg6tS7LmU/XTIIyJcvdHI/AAAAAAAAfsM/syj8BZN6q4sYe_T6HJ5DpmUiYXUfx2i4wCLcBGAs/s1600/post%2Blegende%2Bnew%2Bcopy.jpg");

        Category cat3 = new Category(null, "Biographies", "Biographies books",
                "https://cropper.watch.aetnd.com/cdn.watch.aetnd.com/sites/4/2020/01/biography_on_black_background_1920x1080.jpg");

        Book book1 = new Book(null, "Clean code", "Robert Martin",
                "Even bad code can function. But if code isn’t clean, it can bring a development organization to its knees. Every year, countless hours and significant resources are lost because of poorly written code. But it doesn’t have to be that way."
                        + " Noted software expert Robert C. Martin presents a revolutionary paradigm with Clean Code: A Handbook of Agile Software Craftsmanship. Martin has teamed up with his colleagues from Object Mentor to distill their best agile practice of cleaning code “on the fly” into a book that will instill within you the values of a software craftsman and make you a better programmer–but only if you work at it.",
                "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1436202607i/3735293.jpg",
                cat1);

        Book book2 = new Book(null, "Designing Distributed Systems", "Brendan Burns",
                "Without established design patterns to guide them, developers have had to build distributed systems from scratch, and most of these systems are very unique indeed. Today, the increasing use of containers has paved the way for core distributed system patterns and reusable containerized components.",
                "https://images-na.ssl-images-amazon.com/images/I/51dU7JA2hbL._SX379_BO1,204,203,200_.jpg", cat1);

        Book book3 = new Book(null, "The Time Machine", "H.G. Wells",
                "In this unabridged classic, the time-traveling protagonist is propelled by his machine to the distant year of 802,701 AD. To his horror, he finds only a decaying Earth that is being gradually swallowed by the Sun, and where two strange species--the delicate Eloi and the fierce, subterranean Morlocks--inhabit an eerie dystopia. The Time Machine is a must-read for any science-fiction fan.",
                "https://images-na.ssl-images-amazon.com/images/I/51Zfgu-TTLL._SX413_BO1,204,203,200_.jpg", cat2);

        Book book4 = new Book(null, "The War of the Worlds", "H.G. Wells",
                "This is the granddaddy of all alien invasion stories, first published by H.G. Wells in 1898. The novel begins ominously, as the lone voice of a narrator tells readers that No one would have believed in the last years of the nineteenth century that this world was being watched keenly and closely by intelligences greater than man's...",
                "https://m.media-amazon.com/images/I/51Uvh63U4vL.jpg", cat2);

        Book book5 = new Book(null, "I, Robot", "Isaac Asimov",
                "I, Robot, the first and most widely read book in Asimov's Robot series, forever changed the world's perception of artificial intelligence. Here are stories of robots gone mad, of mind-reading robots, and robots with a sense of humor. Of robot politicians, and robots who secretly run the world--all told with the dramatic blend of science fact and science fiction that has become Asimov's trademark.",
                "https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/ad352c45728713.583b609560cfb.jpg", cat2);

        Book book6 = new Book(null, "Steve Jobs", "Walter Isaacson",
                "Many people consider the name of Steve Jobs to be synonymous to Apple Computers, but the actual fact is that he was far beyond than being the basic driving force behind Apple. In the book Steve Jobs, the author Walter Isaacson actually brings out the true person standing behind the persona of Steve Jobs. Isaacson successfully documents the personal as well as professional hardships faced by the man who actually brought the revolution in the world of technology and also helped in bringing the computers to individuals.",
                "https://images-na.ssl-images-amazon.com/images/I/41OnkWAt1SL._SX345_BO1,204,203,200_.jpg", cat3);

        cat1.getBook().addAll(Arrays.asList(book1, book2));
        cat2.getBook().addAll(Arrays.asList(book3, book4, book5));
        cat3.getBook().add(book6);

        this.categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
        this.bookRepository.saveAll(Arrays.asList(book1, book2, book3, book4, book5, book6));

    }
}
