package com.will.bookstoreapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.will.bookstoreapi.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
