package com.will.bookstoreapi.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll() {
		List<Category> catList = catService.findall();
		List<CategoryDTO> dtoList = catList.stream().map(obj -> new CategoryDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(dtoList);
	}

	@PostMapping
	public ResponseEntity<Category> create(@RequestBody Category cat) {
		cat = catService.create(cat);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cat.getId()).toUri();

		return ResponseEntity.created(uri).body(cat);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<CategoryDTO> update(@PathVariable Integer id, @RequestBody CategoryDTO catDto) {
		Category newCat = catService.update(id, catDto);
		return ResponseEntity.ok().body(new CategoryDTO(newCat));

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		catService.delete(id);

		return ResponseEntity.noContent().build();
	}
}
