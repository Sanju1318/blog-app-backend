package com.example.choudhary.EntityService.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.choudhary.EntityApis.Category;
import com.example.choudhary.EntityDto.CategoryDto;
import com.example.choudhary.EntityException.ResourceNotFoundException;
import com.example.choudhary.EntityRepo.CategoryRepository;
import com.example.choudhary.EntityService.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		
		Category category=this.DtoToCategory(categoryDto);
		Category savedCategory=this.categoryRepository.save(category);
		return this.CategoryToDTo(savedCategory);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		// TODO Auto-generated method stub
		Category category=this.categoryRepository.findById(categoryId).
				orElseThrow(()->new ResourceNotFoundException("Category", "id", categoryId));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updateCategory=this.categoryRepository.save(category);
		return this.CategoryToDTo(updateCategory);
	}

	@Override
	public List<CategoryDto> getAllData() {
		// TODO Auto-generated method stub
		List<Category>data=this.categoryRepository.findAll();
		List<CategoryDto>DtoData=data.stream().map(sdata->this.CategoryToDTo(sdata)).collect(Collectors.toList());
		return DtoData;
	}

	@Override
	public CategoryDto getByIdCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		
		Category category=this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "id", categoryId));
		
		return this.CategoryToDTo(category);
	}

	@Override
	public void deleteById(Integer categoryId) {
		// TODO Auto-generated method stub
		Category category=this.categoryRepository.findById(categoryId).
				orElseThrow(()->new ResourceNotFoundException("Category", "id", categoryId));
		this.categoryRepository.delete(category);
	}

	
	//DTO->CATEGORY
	public Category DtoToCategory(CategoryDto categoryDto)
	{
		Category Dtoctg=this.modelMapper.map(categoryDto,Category.class);
		return Dtoctg;
	}
	
	//Category->DTO
	
	public CategoryDto CategoryToDTo(Category category)
	{
	CategoryDto ctgDto=this.modelMapper.map(category, CategoryDto.class);
	
	return ctgDto;
		
		
	}

}
