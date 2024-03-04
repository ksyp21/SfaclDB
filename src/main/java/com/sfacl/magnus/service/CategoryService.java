package com.sfacl.magnus.service;

import com.sfacl.magnus.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    void createNewCategory(Category category);

    List<Category> getAllCategory();

    Optional<Category> getCategoryById(long id);

    void updateCategorylById(Long id,Category category);
    void deleteCategoryById(Long id);
    void deleteAllCategory();

}
