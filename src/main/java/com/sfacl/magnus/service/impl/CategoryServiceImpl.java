package com.sfacl.magnus.service.impl;

import com.sfacl.magnus.entity.Category;
import com.sfacl.magnus.repository.CategoryRepository;
import com.sfacl.magnus.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {


    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void createNewCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getCategoryById(long id) {

        return categoryRepository.findById(id);
    }

    @Override
    public void updateCategorylById(Long id, Category category) {
        Optional<Category> existingCategory=categoryRepository.findById(id);
        existingCategory.ifPresentOrElse(
                s ->{
                    s.setCategoryName(category.getCategoryName());
                    s.setCategoryDescription(category.getCategoryDescription());
                    categoryRepository.save(s);
                },
                ()->{
                    throw new IllegalArgumentException("Category with "+id+ " not found");
                }
        );


    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void deleteAllCategory() {
        categoryRepository.deleteAll();
    }
}
