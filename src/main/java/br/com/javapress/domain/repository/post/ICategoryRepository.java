package br.com.javapress.domain.repository.post;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.javapress.domain.entity.post.Category;
import br.com.javapress.domain.entity.post.PostCategory;
import br.com.javapress.domain.entity.recipe.RecipeCategory;

public interface ICategoryRepository extends
		PagingAndSortingRepository<Category<?>, Long> {
	
	public List<Category<?>> findAll();

	@Query("from PostCategory")	
	public List<PostCategory> findAllPostCategories();	
	
	@Query("from RecipeCategory")	
	public List<RecipeCategory> findAllRecipeCategories();
	
}
