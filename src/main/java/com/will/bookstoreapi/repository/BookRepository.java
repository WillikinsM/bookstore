package com.will.bookstoreapi.repository;

import com.will.bookstoreapi.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    @Query("SELECT obj FROM Book obj WHERE obj.category.id = :id_cat ORDER BY obj.title")
    List<Book> findByCategory(@Param(value = "id_cat") Long id_cat);
}