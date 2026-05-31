package com.example.choudhary.EntityService;

import java.util.List;

import com.example.choudhary.EntityDto.CategoryDto;

public interface CategoryService {
	
	
	//Create 
	
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//Put
	
	CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
	
	//GetAll
	
	List<CategoryDto> getAllData();
	
	//GetByID
	
	CategoryDto getByIdCategory(Integer categoryId);
	
	//DeleteData
	
	void deleteById(Integer categoryId);

}
