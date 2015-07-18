package br.com.javapress.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.javapress.domain.entity.post.BlogPost;
import br.com.javapress.domain.entity.post.Category;
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
	public List<BlogPost> findAllBlogPosts() {
		return this.postService.findAllBlogPosts();
	}
	
	@RequestMapping(value="/post/{id}", method = RequestMethod.GET)
	public Post findById(@PathVariable Long id){
		return this.postService.findById(id);
	}
	
	@RequestMapping(value="/post", method = RequestMethod.POST)
	public Post create(Post post){
		return this.postService.save(post);
	}
	
	@RequestMapping(value="/post", method = RequestMethod.PUT)
	public Post update(Post post){
		return this.postService.save(post);
	}
	
	@RequestMapping(value="/post/{id}", method = RequestMethod.DELETE)
	public void delete(Long id){
		this.postService.delete(id);
	}
}