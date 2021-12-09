package com.will.bookstoreapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.will.bookstoreapi.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

	@Query("SELECT obj FROM Book obj WHERE obj.category.id = :id_cat ORDER BY title")
	List<Book> findByCategory(@Param(value = "id_cat") Integer id_cat);

}
