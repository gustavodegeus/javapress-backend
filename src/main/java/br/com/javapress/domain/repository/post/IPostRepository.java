package br.com.javapress.domain.repository.post;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.com.javapress.domain.entity.post.BlogPost;
import br.com.javapress.domain.entity.post.Post;
import br.com.javapress.domain.entity.recipe.Recipe;

public interface IPostRepository extends
		PagingAndSortingRepository<Post, Long> {

	public List<Post> findAll();
	
	@Query("FROM BlogPost WHERE (LOWER(title) LIKE LOWER('%' || CAST(:title AS string)|| '%') or :title is null) "
			+ "AND (category.id = :categoryId or :categoryId is null)")	
	public List<BlogPost> findBlogPostsByTitleAndCategoryId(@Param("title") String title, @Param("categoryId") Long categoryId);	
	
	@Query("from Recipe")	
	public List<Recipe> findAllRecipes();
}
