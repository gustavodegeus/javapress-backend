package br.com.javapress.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.javapress.domain.entity.post.Category;
import br.com.javapress.domain.service.CategoryService;

/**
 * Handles requests for the application home page.
 */
@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public List<Category<?>> findAll() {
		return this.categoryService.findAll();
	}
	
	@RequestMapping(value="/category/{id}", method = RequestMethod.GET)
	public Category<?> findById(@PathVariable Long id){
		return this.categoryService.findById(id);
	}
	
	@RequestMapping(value="/category", method = RequestMethod.POST)
	public Category<?> create(@RequestBody Category<?> category){
		return this.categoryService.save(category);
	}
	
	@RequestMapping(value="/category", method = RequestMethod.PUT)
	public Category<?> update(@RequestBody Category<?> category){
		return this.categoryService.save(category);
	}
	
	@RequestMapping(value="/category/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id){
		this.categoryService.delete(id);
	}
}