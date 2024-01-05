package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
