package br.com.javapress.test.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.javapress.domain.entity.post.Category;
import br.com.javapress.domain.entity.post.PostCategory;
import br.com.javapress.domain.entity.recipe.RecipeCategory;
import br.com.javapress.domain.repository.post.ICategoryRepository;
import br.com.javapress.test.TestConfiguration;

public class CategoryRepositoryTest extends TestConfiguration{

	@Autowired
	private ICategoryRepository categoryRepository;
	
//	@Test
	public void shouldCreateAndUpdatePostCategory(){
		//Given
		PostCategory category = new PostCategory();
		category.setName(getRandomString());
		
		//When
		this.categoryRepository.save(category);
		
		//Then
		assertNotNull("Id should not be null", category.getId());
		assertEquals("Category type", "postCategory", category.getType());
		
		//Given
		category.setName(getRandomString());
		
		//When
		this.categoryRepository.save(category);
		
		//Then
		Category<?> dbCategory = this.categoryRepository.findOne(category.getId());
		assertEquals("Category name should be equal", dbCategory.getName(), category.getName());
		assertEquals("Category type should be equal", "postCategory", dbCategory.getType());
		
		//Given
		PostCategory otherCategory = new PostCategory();
		otherCategory.setParent(category);
		otherCategory.setName(getRandomString());
		
		//When
		this.categoryRepository.save(otherCategory);
		
		//Then
		assertNotNull("Id should not be null", otherCategory.getId());
		assertEquals("Parent category should be persisted", category.getId(), otherCategory.getParent().getId());
		assertEquals("Category type", "postCategory", otherCategory.getType());
		
	}
	
//	@Test
	public void shouldCreateAndUpdateRecipeCategory(){
		//Given
		RecipeCategory category = new RecipeCategory();
		category.setName(getRandomString());
		
		//When
		this.categoryRepository.save(category);
		
		//Then
		assertNotNull("Category id should not be null", category.getId());
		assertEquals("Category type", "recipeCategory", category.getType());
		
		//Given
		category.setName(getRandomString());
		
		//When
		this.categoryRepository.save(category);
		
		//Then
		Category<?> dbCategory = this.categoryRepository.findOne(category.getId());
		assertEquals("Name should be equal", dbCategory.getName(), category.getName());
		assertEquals("Category type", "recipeCategory", dbCategory.getType());
		
		//Given
		RecipeCategory otherCategory = new RecipeCategory();
		otherCategory.setParent(category);
		otherCategory.setName(getRandomString());
		
		//When
		this.categoryRepository.save(otherCategory);
		
		//Then
		assertNotNull("Id should not be null", otherCategory.getId());
		assertEquals("Parent category should be persisted", category.getId(), otherCategory.getParent().getId());
		assertEquals("Category type", "recipeCategory", otherCategory.getType());
	}
	
	@Test
	public void shouldDelete(){
		//Given
		Category<RecipeCategory> category = new RecipeCategory();
		category.setName("Category to be deleted");
		this.categoryRepository.save(category);
		assertNotNull(category.getId());
		
		//When
		this.categoryRepository.delete(category);
		
		//Then
		Category<?> dbCategory = this.categoryRepository.findOne(category.getId());
		assertNull(dbCategory);
	}
	
	//@Test
	public void shouldFindAll(){
		//Given
		Category<PostCategory> category = new PostCategory();
		category.setName(getRandomString());
		this.categoryRepository.save(category);
		
		Category<PostCategory> category2 = new PostCategory();
		category.setName(getRandomString());
		this.categoryRepository.save(category2);
		
		//When
		List<PostCategory> postCategories = this.categoryRepository.findAllPostCategories();
		
		//Then
		assertTrue(postCategories.size()>1);
		
		//Given
		Category<RecipeCategory> recipeCategory = new RecipeCategory();
		category.setName(getRandomString());
		this.categoryRepository.save(recipeCategory);		
		Category<RecipeCategory> recipeCategory2 = new RecipeCategory();
		category.setName(getRandomString());
		this.categoryRepository.save(recipeCategory2);
		
		//When
		List<RecipeCategory> recipeCategories = this.categoryRepository.findAllRecipeCategories();
		
		//Then
		assertTrue(recipeCategories.size()>1);
		
		//Given
		List<Category<?>> allCategories = this.categoryRepository.findAll();
		
		//Then
		assertEquals((postCategories.size() + recipeCategories.size()), allCategories.size());
	}
}
