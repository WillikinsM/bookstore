package com.will.bookstoreapi.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.will.bookstoreapi.domain.Category;
import com.will.bookstoreapi.service.CategoryService;

@RestController
@RequestMapping(value = "/category")
public class CategoryResources {

	@Autowired
	private CategoryService catService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Category> findById(@PathVariable Integer id) {
		Category obj = catService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

}
