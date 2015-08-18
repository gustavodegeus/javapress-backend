package br.com.javapress.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.javapress.domain.dto.SearchBlogPostDto;
import br.com.javapress.domain.dto.SuccessMessageDto;
import br.com.javapress.domain.entity.post.BlogPost;
import br.com.javapress.domain.entity.post.Post;
import br.com.javapress.domain.service.PostService;

/**
 * Handles requests for the application home page.
 */
@RestController
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@RequestMapping(value = "/blogPosts", method = RequestMethod.GET)
	public List<BlogPost> findBlogPosts(@RequestParam(value="title",required=false) String title, 
										  @RequestParam(value="categoryId",required=false) Long categoryId) {
		SearchBlogPostDto searchBlogPostDto = new SearchBlogPostDto(title, categoryId);
		return this.postService.findBlogPostsByTitleAndCategoryId(searchBlogPostDto);
	}
	
	@RequestMapping(value="/post/{id}", method = RequestMethod.GET)
	public Post findById(@PathVariable Long id){
		return this.postService.findById(id);
	}
	
	@RequestMapping(value="/post", method = RequestMethod.POST)
	public SuccessMessageDto create(@RequestBody Post post) throws Exception{
		return this.postService.create(post);
	}
	
	@RequestMapping(value="/post", method = RequestMethod.PUT)
	public SuccessMessageDto update(@RequestBody Post post) throws Exception{
		return this.postService.update(post);
	}
	
	@RequestMapping(value="/post/{id}", method = RequestMethod.DELETE)
	public SuccessMessageDto delete(@PathVariable Long id){
		return this.postService.delete(id);
	}
}