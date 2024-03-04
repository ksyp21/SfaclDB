package com.sfacl.magnus.repository;

import com.sfacl.magnus.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
