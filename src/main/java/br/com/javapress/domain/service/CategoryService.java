package br.com.javapress.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.javapress.domain.entity.post.Category;
import br.com.javapress.domain.entity.post.PostCategory;
import br.com.javapress.domain.entity.recipe.RecipeCategory;
import br.com.javapress.domain.repository.post.ICategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private ICategoryRepository categoryRepository;
	
	public Category<?> save(Category<?> category){
		return this.categoryRepository.save(category);
	}
	
	public void delete(Long id){
		this.categoryRepository.delete(id);
	}
	
	public List<PostCategory> findAllPostCategory(){
		return this.categoryRepository.findAllPostCategories(); 
	}
	
	public List<RecipeCategory> findAllRecipeCategory(){
		return this.categoryRepository.findAllRecipeCategories(); 
	}
	
	public List<Category<?>> findAll(){
		return this.categoryRepository.findAll();
	}

	public Category<?> findById(Long id) {
		return this.categoryRepository.findOne(id);
	}
}
