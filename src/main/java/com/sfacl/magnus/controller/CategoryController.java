package com.sfacl.magnus.controller;

import com.sfacl.magnus.entity.Category;
import com.sfacl.magnus.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/category")
    public ResponseEntity<String> createCategory(@RequestBody Category category) {
        categoryService.createNewCategory(category);
        return new ResponseEntity<>("Category created", HttpStatus.CREATED);
    }

    @GetMapping("/category")
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> categoryList = categoryService.getAllCategory();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable long id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        if (category.isPresent()) {
            return new ResponseEntity<>(HttpStatus.FOUND);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<String> updateById(@PathVariable long id, @RequestBody Category category) {
        categoryService.updateCategorylById(id, category);
        return new ResponseEntity<>("Value updated for id" + id, HttpStatus.OK);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id) {
        categoryService.deleteCategoryById(id);
        return new ResponseEntity<>("Values deleted for id" + id, HttpStatus.OK);
    }

    @DeleteMapping("/category")
    public ResponseEntity<String> deleteAllStaff() {
        categoryService.deleteAllCategory();
        return new ResponseEntity<>("Category deleted", HttpStatus.OK);
    }

}
