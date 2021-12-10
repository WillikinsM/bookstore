package com.will.bookstoreapi.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.will.bookstoreapi.domain.Category;
import com.will.bookstoreapi.dtos.CategoryDTO;
import com.will.bookstoreapi.service.CategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/category")
@Api(value = "Category")
@CrossOrigin(origins = "*")
public class CategoryResources {

	@Autowired
	private CategoryService catService;

	@ApiOperation(value = "Find Category by given ID")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Category> findById(@PathVariable Integer id) {
		Category obj = catService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@ApiOperation(value = "Find all categories")
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll() {
		List<Category> catList = catService.findall();
		List<CategoryDTO> dtoList = catList.stream().map(obj -> new CategoryDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(dtoList);
	}

	@ApiOperation(value = "Create a new category")
	@PostMapping
	public ResponseEntity<Category> create(@Valid @RequestBody Category cat) {
		cat = catService.create(cat);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cat.getId()).toUri();

		return ResponseEntity.created(uri).body(cat);
	}

	@ApiOperation(value = "Update a existing category")
	@PutMapping(value = "/{id}")
	public ResponseEntity<CategoryDTO> update(@PathVariable Integer id, @Valid @RequestBody CategoryDTO catDto) {
		Category newCat = catService.update(id, catDto);
		return ResponseEntity.ok().body(new CategoryDTO(newCat));

	}

	@ApiOperation(value = "Delete a exixting category")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		catService.delete(id);

		return ResponseEntity.noContent().build();
	}
}
