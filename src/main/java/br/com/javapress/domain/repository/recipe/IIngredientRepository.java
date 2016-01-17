package br.com.javapress.domain.repository.recipe;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.javapress.domain.entity.recipe.Ingredient;

public interface IIngredientRepository extends
		CrudRepository<Ingredient, Long> {

	List<Ingredient> findByDescription(String description);

}
