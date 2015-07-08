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
		Category category = new PostCategory();
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
		Category dbCategory = this.categoryRepository.findOne(category.getId());
		Assert.assertEquals(dbCategory.getName(), category.getName());
		
		//Given
		Category otherCategory = new RecipeCategory();
		otherCategory.setName("Child category");
		otherCategory.setParent(dbCategory);
		
		//When
		this.categoryRepository.save(otherCategory);
		
		//Then
		Assert.assertNotNull(otherCategory.getId());
		Assert.assertEquals(otherCategory.getParent().getId(), dbCategory.getId());
	}
	
	@Test
	public void testSaveRecipeCategory(){
		//Given
		Category category = new RecipeCategory();
		category.setName("Recipe category");
		
		//When
		this.categoryRepository.save(category);
		
		//Then
		Assert.assertNotNull(category.getId());
		Assert.assertEquals("recipeCategory",category.getType());
		
		//Given
		category.setName("New recipe category name");
		
		//When
		this.categoryRepository.save(category);
		
		//Then
		Category dbCategory = this.categoryRepository.findOne(category.getId());
		Assert.assertEquals(dbCategory.getName(), category.getName());
		
		//Given
		Category otherCategory = new RecipeCategory();
		otherCategory.setName("Child category");
		otherCategory.setParent(dbCategory);
		
		//When
		this.categoryRepository.save(otherCategory);
		
		//Then
		Assert.assertNotNull(otherCategory.getId());
		Assert.assertEquals(otherCategory.getParent().getId(), dbCategory.getId());
	}
	
	@Test
	public void testDelete(){
		//Given
		Category category = new PostCategory();
		category.setName("Category to be deleted");
		this.categoryRepository.save(category);
		Assert.assertNotNull(category.getId());
		
		//When
		this.categoryRepository.delete(category);
		
		//Then
		Category dbCategory = this.categoryRepository.findOne(category.getId());
		Assert.assertNull(dbCategory);
	}
	
	@Test
	public void testFindAll(){
		//Given
		Category category = new PostCategory();
		category.setName("New post category");
		this.categoryRepository.save(category);
		
		Category category2 = new RecipeCategory();
		category.setName("New recipe category");
		this.categoryRepository.save(category2);
		
		//When
		List<Category> categorys = this.categoryRepository.findAll();
		Assert.assertTrue(categorys.size()>1);
	}
}
