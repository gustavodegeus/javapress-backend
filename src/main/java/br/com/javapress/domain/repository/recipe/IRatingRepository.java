package br.com.javapress.domain.repository.recipe;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.javapress.domain.entity.recipe.Rating;

public interface IRatingRepository extends
		CrudRepository<Rating, Long> {

	@Query("SELECT AVG(rating) from Rating rating "
			+ "WHERE rating.recipe.id = :id")
	public int calculateAverageByRecipeId(@Param("id") Long recipeId);
}
