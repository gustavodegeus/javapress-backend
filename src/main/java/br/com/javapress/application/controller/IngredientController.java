package br.com.javapress.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.javapress.domain.dto.SuccessMessageDto;
import br.com.javapress.domain.entity.recipe.Ingredient;
import br.com.javapress.domain.service.IngredientService;

@RestController
public class IngredientController {

	@Autowired
    private MessageSource messageSource;
	
	@Autowired
	private IngredientService ingredientService;
	
	@RequestMapping(value = "/ingredients", method = RequestMethod.GET)
	public List<Ingredient> find(@RequestParam(value="description", required=false) String description) {
		return this.ingredientService.findByDescription(description);
	}
	
	@RequestMapping(value="/ingredient/{id}", method = RequestMethod.GET)
	public Ingredient findById(@PathVariable Long id){
		return this.ingredientService.findById(id);
	}
	
	@RequestMapping(value="/ingredient", method = RequestMethod.POST)
	public SuccessMessageDto create(@RequestBody Ingredient ingredient) throws Exception {
		return this.ingredientService.create(ingredient);
	}
	
	@RequestMapping(value="/ingredient", method = RequestMethod.PUT)
	public SuccessMessageDto update(@RequestBody Ingredient ingredient) throws Exception {
		return this.ingredientService.update(ingredient);
	}
	
	@RequestMapping(value="/ingredient/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id){
		this.ingredientService.delete(id);
	}
}