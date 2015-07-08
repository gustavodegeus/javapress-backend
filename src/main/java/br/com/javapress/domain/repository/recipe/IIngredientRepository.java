package br.com.javapress.domain.repository.recipe;

import org.springframework.data.repository.CrudRepository;

import br.com.javapress.domain.entity.recipe.Ingredient;

public interface IIngredientRepository extends
		CrudRepository<Ingredient, Long> {

}
