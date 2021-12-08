package com.will.bookstoreapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.will.bookstoreapi.domain.Category;
import com.will.bookstoreapi.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public Category findById(Integer id) {
		Optional<Category> objOptional = categoryRepository.findById(id);

		return objOptional.orElseThrow();
	}

}
