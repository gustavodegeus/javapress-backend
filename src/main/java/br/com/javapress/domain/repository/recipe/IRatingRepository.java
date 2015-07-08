package br.com.javapress.domain.repository.recipe;

import org.springframework.data.repository.CrudRepository;

import br.com.javapress.domain.entity.recipe.Rating;

public interface IRatingRepository extends
		CrudRepository<Rating, Long> {

}
