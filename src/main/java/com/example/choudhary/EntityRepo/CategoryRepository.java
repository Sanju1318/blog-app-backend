package com.example.choudhary.EntityRepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.choudhary.EntityApis.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
