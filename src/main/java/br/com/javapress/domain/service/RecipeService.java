package br.com.javapress.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.javapress.application.validation.groups.PreUpdate;
import br.com.javapress.domain.dto.SearchRecipeDto;
import br.com.javapress.domain.dto.SuccessMessageDto;
import br.com.javapress.domain.entity.post.Post;
import br.com.javapress.domain.entity.recipe.Rating;
import br.com.javapress.domain.entity.recipe.Recipe;
import br.com.javapress.domain.repository.post.IPostRepository;
import br.com.javapress.domain.repository.recipe.IRatingRepository;

@Service
public class RecipeService{
	
	@Autowired
	private IRatingRepository ratingRepository;
	
	@Autowired
	private IPostRepository postRepository;
	
	@Autowired
	private PostService postService;
	
	public SuccessMessageDto create(Recipe recipe) throws Exception{
		this.postService.loadPostData(recipe);
		this.validate(recipe);
		return new SuccessMessageDto("Receita criada com sucesso.", this.save(recipe));
	}
	
	public SuccessMessageDto update(Recipe recipe) throws Exception{
		this.postService.loadPostData(recipe);
		this.validate(recipe,PreUpdate.class);
		return new SuccessMessageDto("Receita atualizada com sucesso.", this.save(recipe));
	}
	
	protected void validate(Recipe recipe,Class<?>... classes) throws Exception{
		this.postService.validate(recipe, classes);
	}
	
	@Transactional
	protected Recipe save(Recipe recipe){
		this.postService.prepareTags(recipe);
		return this.postRepository.save(recipe);
	}
	
	@Transactional
	public SuccessMessageDto delete(Long id){
		this.postRepository.delete(id);
		//TODO i18n
		return new SuccessMessageDto("Post excluído com sucesso.");
	}

	public List<Recipe> findRecipesByTitleAndCategoryId(SearchRecipeDto searchBlogPostDto){
		return this.postRepository.findRecipeByTitleAndCategoryId(searchBlogPostDto.getTitle(), searchBlogPostDto.getCategoryId()); 
	}
	
	public List<Post> findAll(){
		return this.postService.findAll();
	}
	
	@Transactional
	public SuccessMessageDto rateRecipe(Rating rating){
		this.ratingRepository.save(rating);
		return new SuccessMessageDto("Avaliação enviada com sucesso.", rating);
	}
	
	public int calculateRating(Long recipeId){
		return this.ratingRepository.calculateAverageByRecipeId(recipeId);
	}

	public Recipe findById(Long id) {
		return (Recipe) this.postService.findById(id);
	}
}