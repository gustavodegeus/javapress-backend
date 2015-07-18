package br.com.javapress.domain.repository.post;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.javapress.domain.entity.post.BlogPost;
import br.com.javapress.domain.entity.post.Post;
import br.com.javapress.domain.entity.recipe.Recipe;

public interface IPostRepository extends
		PagingAndSortingRepository<Post, Long> {

	public List<Post> findAll();
	
	@Query("from BlogPost")	
	public List<BlogPost> findAllBlogPosts();	
	
	@Query("from Recipe")	
	public List<Recipe> findAllRecipes();
}
