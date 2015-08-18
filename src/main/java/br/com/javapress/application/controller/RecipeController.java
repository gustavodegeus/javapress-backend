package br.com.javapress.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.javapress.domain.dto.SearchRecipeDto;
import br.com.javapress.domain.dto.SuccessMessageDto;
import br.com.javapress.domain.entity.recipe.Recipe;
import br.com.javapress.domain.service.RecipeService;

/**
 * Handles requests for the application home page.
 */
@RestController
public class RecipeController {
	
	@Autowired
	private RecipeService recipeService;
	
	@RequestMapping(value = "/recipes", method = RequestMethod.GET)
	public List<Recipe> findRecipes(@RequestParam(value="title",required=false) String title, 
										  @RequestParam(value="categoryId",required=false) Long categoryId) {
		SearchRecipeDto searchRecipeDto = new SearchRecipeDto(title, categoryId);
		return this.recipeService.findRecipesByTitleAndCategoryId(searchRecipeDto);
	}
	
	@RequestMapping(value="/recipe/{id}", method = RequestMethod.GET)
	public Recipe findById(@PathVariable Long id){
		return this.recipeService.findById(id);
	}
	
	@RequestMapping(value="/recipe", method = RequestMethod.POST)
	public SuccessMessageDto create(@RequestBody Recipe recipe) throws Exception{
		return this.recipeService.create(recipe);
	}
	
	@RequestMapping(value="/recipe", method = RequestMethod.PUT)
	public SuccessMessageDto update(@RequestBody Recipe recipe) throws Exception{
		return this.recipeService.update(recipe);
	}
	
	@RequestMapping(value="/recipe/{id}", method = RequestMethod.DELETE)
	public SuccessMessageDto delete(@PathVariable Long id){
		return this.recipeService.delete(id);
	}
}