package br.com.javapress.test.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.validation.Validator;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.javapress.domain.entity.post.Category;
import br.com.javapress.domain.entity.post.CategoryType;
import br.com.javapress.domain.entity.post.Tag;
import br.com.javapress.domain.entity.recipe.Ingredient;
import br.com.javapress.domain.entity.recipe.Recipe;
import br.com.javapress.domain.entity.recipe.Step;
import br.com.javapress.domain.entity.user.Admin;
import br.com.javapress.domain.repository.post.ICategoryRepository;
import br.com.javapress.domain.repository.post.IPostRepository;
import br.com.javapress.domain.repository.post.ITagRepository;
import br.com.javapress.domain.repository.user.IAdminRepository;
import br.com.javapress.test.config.TestConfiguration;

public class RecipeRepositoryTest extends TestConfiguration {

	@Autowired
	private IPostRepository postRepository;
	
	@Autowired
	private ICategoryRepository categoryRepository;
	
	@Autowired
	private IAdminRepository adminRepository;

	@Autowired
	private ITagRepository tagRepository;
	
	@Autowired
	private Validator validator;
	
	@Test
	public void shouldCreateAndUpdateRecipe(){
		//Given
		Tag tag = new Tag();
		tag.setName(getRandomString());
		tagRepository.save(tag);
		Recipe recipe = getObject();
		recipe.addTag(tag);
		
		recipe.addIngredient(this.getIngredient("trigo"));
		recipe.addIngredient(this.getIngredient("fermento"));
		recipe.addStep(this.getStep("primeiro passo"));
		recipe.addStep(this.getStep("segundo passo"));
		
		//When
		this.postRepository.save(recipe);
		
		//Then
		assertNotNull("Id should not be null", recipe.getId());
		
		//Given
		recipe.setContent("New content");
		Category otherCategory = new Category();
		otherCategory.setName(getRandomString());
		otherCategory.setType(CategoryType.RECIPE);
		recipe.setCategory(this.categoryRepository.save(otherCategory));
		recipe.addStep(this.getStep("step 03"));
		
		//When
		this.postRepository.save(recipe);
		
		//Then
		Recipe dbRecipe = (Recipe) this.postRepository.findOne(recipe.getId());
		assertEquals("Content should be equal", dbRecipe.getContent(), recipe.getContent());
		assertEquals("Category should be equal", recipe.getCategory().getName(), dbRecipe.getCategory().getName());
		assertEquals("Should have three steps", 3, dbRecipe.getSteps().size());
	}
	
	@Test
	public void shouldDelete(){
		//Given
		Recipe recipe = this.getObject();
		this.postRepository.save(recipe);
		
		//When
		this.postRepository.delete(recipe);
		
		//Then
		Recipe dbRecipe = (Recipe) this.postRepository.findOne(recipe.getId());
		assertNull("Recipe shouldn't exist", dbRecipe);
	}
	
	@Test
	public void shouldFindAll(){
		//Given
		Recipe recipe = getObject();
		this.postRepository.save(recipe);
		
		Recipe recipe2 = getObject();
		this.postRepository.save(recipe2);
		
		//When
		List<Recipe> recipes = this.postRepository.findRecipeByTitleAndCategoryId(null, null);
		assertTrue("Size should be bigger than one", recipes.size()>1);
	}
	
	private Recipe getObject(){
		Category category = new Category();
		category.setName(getRandomString());
		category.setType(CategoryType.RECIPE);
		
		Admin admin = new Admin();
		admin.setEmail("email@email.com");
		admin.setName("Admin name");
		
		Recipe recipe = new Recipe();
		recipe.setTitle(getRandomString());
		recipe.setContent(getRandomString());
		recipe.setPublished(false);
		recipe.setCategory(categoryRepository.save(category));
		recipe.setOwner(adminRepository.save(admin));
		return recipe;
	}
	
	private Step getStep(String stepDescription){
		Step step = new Step();
		step.setDescription(stepDescription);
		return step;
	}
	
	private Ingredient getIngredient(String name){
		Ingredient ingredient = new Ingredient();
		ingredient.setDescription(name);
		return ingredient;
	}
}
