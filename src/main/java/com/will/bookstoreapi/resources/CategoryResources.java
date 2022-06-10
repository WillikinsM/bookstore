package com.will.bookstoreapi.resources;


import com.will.bookstoreapi.domain.Category;
import com.will.bookstoreapi.dtos.CategoryDTO;
import com.will.bookstoreapi.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/category")
@Api(value = "Category")
public class CategoryResources {

    private final CategoryService categoryService;

    @Autowired
    public CategoryResources(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ApiOperation(value = "Find Category by given ID")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Category obj = categoryService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @ApiOperation(value = "Find all categories")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @GetMapping(value ="/")
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<Category> catList = categoryService.findAll();
        List<CategoryDTO> dtoList = catList.stream().map(CategoryDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(dtoList);
    }

    @ApiOperation(value = "Create a new category")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value ="/admin/create")
    public ResponseEntity<Category> create(@Valid @RequestBody Category cat) {
        cat = categoryService.create(cat);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(cat.getId()).toUri();
        return ResponseEntity.created(uri).body(cat);
    }

    @ApiOperation(value = "Update a existing category")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/admin/update/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @Valid @RequestBody CategoryDTO catDto) {
        Category newCat = categoryService.update(id, catDto);
        return ResponseEntity.ok().body(new CategoryDTO(newCat));

    }

    @ApiOperation(value = "Delete a existing category")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/admin/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
