package br.com.javapress.test.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.javapress.domain.entity.post.Category;
import br.com.javapress.domain.entity.post.CategoryType;
import br.com.javapress.domain.repository.post.ICategoryRepository;
import br.com.javapress.test.config.TestConfiguration;

public class CategoryRepositoryTest extends TestConfiguration{

	@Autowired
	private Validator validator;
	
	@Autowired
	private ICategoryRepository categoryRepository;

	@Test
	public void shouldCreateAndUpdateCategory(){
		//Given
		Category category = new Category();
		category.setName(getRandomString());
		category.setType(CategoryType.POST);
		
		//When
		this.categoryRepository.save(category);
		
		//Then
		assertNotNull("Id should not be null", category.getId());
		assertEquals("Category type", CategoryType.POST, category.getType());
		
		//Given
		category.setName(getRandomString());
		
		//When
		this.categoryRepository.save(category);
		
		//Then
		Category dbCategory = this.categoryRepository.findOne(category.getId());
		assertEquals("Category name should be equal", dbCategory.getName(), category.getName());
		assertEquals("Category type should be equal", CategoryType.POST, dbCategory.getType());
		
		//Given
		Category otherCategory = new Category();
		otherCategory.setParent(category);
		otherCategory.setName(getRandomString());
		otherCategory.setType(CategoryType.RECIPE);
		Set<ConstraintViolation<Category>> constraintViolations = validator.validate( otherCategory );
		assertTrue(constraintViolations.size() > 0);
		//When
		this.categoryRepository.save(otherCategory);
		
		//Then
		assertNotNull("Id should not be null", otherCategory.getId());
		assertEquals("Parent category should be persisted", category.getId(), otherCategory.getParent().getId());
		assertEquals("Category type", CategoryType.RECIPE, otherCategory.getType());
		
	}
	
	@Test
	public void shouldDelete(){
		//Given
		Category category = new Category();
		category.setName("Category to be deleted");
		category.setType(CategoryType.POST);
		this.categoryRepository.save(category);
		assertNotNull(category.getId());
		
		//When
		this.categoryRepository.delete(category);
		
		//Then
		Category dbCategory = this.categoryRepository.findOne(category.getId());
		assertNull(dbCategory);
	}
	
	@Test
	public void shouldFindAll(){
		//Given
		Category category = new Category();
		category.setName(getRandomString());
		category.setType(CategoryType.POST);
		this.categoryRepository.save(category);
		
		Category category2 = new Category();
		category2.setName(getRandomString());
		category2.setType(CategoryType.POST);
		this.categoryRepository.save(category2);
		
		//Given
		List<Category> allCategories = this.categoryRepository.findAll();
		
		//Then
		assertTrue(allCategories.size() >= 2);
	}
	
	@Test
	public void shouldFindAllWithFilters(){
		//Given
		Category category = new Category();
		category.setName("xxxx categoria");
		category.setType(CategoryType.POST);
		this.categoryRepository.save(category);
		
		Category category2 = new Category();
		category2.setName("yyy categoria");
		category2.setType(CategoryType.POST);
		this.categoryRepository.save(category2);
		
		//When
		List<Category> categories = this.categoryRepository.findByTypeAndNameAndParentName(null, "xxx", null);
		
		//Then
		assertEquals(1, categories.size());
		
		//When
		categories = this.categoryRepository.findByTypeAndNameAndParentName(CategoryType.POST, null, null);
		
		//Then
		assertTrue(categories.size() >= 2);
		
		//When
		category2.setParent(category);
		this.categoryRepository.save(category2);
		
		categories = this.categoryRepository.findByTypeAndNameAndParentName(null,null, "xxx");
		
		//Then
		assertEquals(1,categories.size());
	}
}
