package com.will.bookstoreapi.service;

import com.will.bookstoreapi.domain.Category;
import com.will.bookstoreapi.dtos.CategoryDTO;
import com.will.bookstoreapi.repository.CategoryRepository;
import com.will.bookstoreapi.resources.exceptions.DataIntegrityViolationException;
import com.will.bookstoreapi.resources.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public Category findById(Long id) {
        Optional<Category> objOptional = categoryRepository.findById(id);

        return objOptional.orElseThrow(
                () -> new ObjectNotFoundException("Object not found "
                        + id + ", type: " + Category.class.getName()));
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category create(Category cat) {
        cat.setId(null);
        return categoryRepository.save(cat);
    }

    public Category update(Long id, CategoryDTO catDto) {
        Category cat = findById(id);
        cat.setName(cat.getName());
        cat.setDescription(catDto.getDescription());
        return categoryRepository.save(cat);
    }

    public void delete(Long id) {
        findById(id);
        try {
            categoryRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(
                    "This Object can't be deleted because it's linked to a existing Book");
        }

    }

}
