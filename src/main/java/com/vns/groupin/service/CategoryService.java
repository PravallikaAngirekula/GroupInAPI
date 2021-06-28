package com.vns.groupin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vns.groupin.entity.Category;
import com.vns.groupin.entity.UserRegistration;
import com.vns.groupin.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	public List<Category> getAllCategories(){
		List<Category> categoryReg = new ArrayList<>();
		categoryRepo.findAll().forEach(categoryReg::add);
		return categoryReg;
	}

	public Category findByCategoryName(String categoryName) {
		return categoryRepo.findByCategoryName(categoryName);
	}

	public Category saveCategory(Category category) {
		return categoryRepo.save(category);
		
	}

	public Category getByCategoryId(int categoryId) {
			return categoryRepo.findByCategoryId(categoryId);
	}

}
