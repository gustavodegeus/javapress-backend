package br.com.javapress.domain.repository.recipe;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.javapress.domain.entity.recipe.Recipe;

public interface IRecipeRepository extends
		PagingAndSortingRepository<Recipe, Long> {

	List<Recipe> findAll();
}
