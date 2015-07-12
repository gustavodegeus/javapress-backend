package br.com.javapress.test.repository;

import java.util.List;

import org.junit.Assert;
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
	
	@Test
	public void testSavePostCategory(){
		//Given
		PostCategory category = new PostCategory();
		category.setName("Post category");
		
		//When
		this.categoryRepository.save(category);
		
		//Then
		Assert.assertNotNull(category.getId());
		Assert.assertEquals("postCategory", category.getType());
		
		//Given
		category.setName("New post category name");
		
		//When
		this.categoryRepository.save(category);
		
		//Then
		Category<?> dbCategory = this.categoryRepository.findOne(category.getId());
		Assert.assertEquals(dbCategory.getName(), category.getName());
		Assert.assertEquals("postCategory", dbCategory.getType());
		
		//Given
		PostCategory otherCategory = new PostCategory();
		otherCategory.setParent(category);
		otherCategory.setName("Other category");
		
		//When
		this.categoryRepository.save(otherCategory);
		
		//Then
		Assert.assertNotNull(otherCategory.getId());
		Assert.assertEquals(category.getId(), otherCategory.getParent().getId());
		Assert.assertEquals("postCategory", otherCategory.getType());
		
	}
	
	@Test
	public void testSaveRecipeCategory(){
		//Given
		RecipeCategory category = new RecipeCategory();
		category.setName("Recipe category");
		
		//When
		this.categoryRepository.save(category);
		
		//Then
		Assert.assertNotNull(category.getId());
		Assert.assertEquals("recipeCategory", category.getType());
		
		//Given
		category.setName("New recipe category name");
		
		//When
		this.categoryRepository.save(category);
		
		//Then
		Category<?> dbCategory = this.categoryRepository.findOne(category.getId());
		Assert.assertEquals(dbCategory.getName(), category.getName());
		Assert.assertEquals("recipeCategory", dbCategory.getType());
		
		//Given
		RecipeCategory otherCategory = new RecipeCategory();
		otherCategory.setParent(category);
		otherCategory.setName("Other recipe category");
		
		//When
		this.categoryRepository.save(otherCategory);
		
		//Then
		Assert.assertNotNull(otherCategory.getId());
		Assert.assertEquals(category.getId(), otherCategory.getParent().getId());
		Assert.assertEquals("recipeCategory", otherCategory.getType());
	}
	
	@Test
	public void testDelete(){
		//Given
		Category<RecipeCategory> category = new RecipeCategory();
		category.setName("Category to be deleted");
		this.categoryRepository.save(category);
		Assert.assertNotNull(category.getId());
		
		//When
		this.categoryRepository.delete(category);
		
		//Then
		Category<?> dbCategory = this.categoryRepository.findOne(category.getId());
		Assert.assertNull(dbCategory);
	}
	
	@Test
	public void testFindAll(){
		//Given
		Category<PostCategory> category = new PostCategory();
		category.setName("New post category");
		this.categoryRepository.save(category);
		
		Category<PostCategory> category2 = new PostCategory();
		category.setName("New recipe category");
		this.categoryRepository.save(category2);
		
		//When
		List<PostCategory> postCategories = this.categoryRepository.findAllPostCategories();
		
		//Then
		Assert.assertTrue(postCategories.size()>1);
		
		//Given
		Category<RecipeCategory> recipeCategory = new RecipeCategory();
		category.setName("New post category");
		this.categoryRepository.save(recipeCategory);		
		Category<RecipeCategory> recipeCategory2 = new RecipeCategory();
		category.setName("New recipe category");
		this.categoryRepository.save(recipeCategory2);
		
		//When
		List<RecipeCategory> recipeCategories = this.categoryRepository.findAllRecipeCategories();
		
		//Then
		Assert.assertTrue(recipeCategories.size()>1);
		
		//Given
		List<Category<?>> allCategories = this.categoryRepository.findAll();
		
		//Then
		Assert.assertEquals((postCategories.size() + recipeCategories.size()), allCategories.size());
	}
}
