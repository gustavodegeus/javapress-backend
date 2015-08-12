package br.com.javapress.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.javapress.domain.dto.SearchCategoryDto;
import br.com.javapress.domain.entity.post.Category;
import br.com.javapress.domain.entity.post.CategoryType;
import br.com.javapress.domain.service.CategoryService;

/**
 * Handles requests for the application home page.
 */
@RestController
public class CategoryController {

	@Autowired
    private MessageSource messageSource;
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public List<Category> find(@RequestParam(value="type", required=false) CategoryType type, 
									 @RequestParam(value="name", required=false) String name,
									 @RequestParam(value="parentName", required=false) String parentName) {
		SearchCategoryDto categorySearchDto = new SearchCategoryDto(name, type, parentName);
		return this.categoryService.findByTypeAndNameAndParentName(categorySearchDto);
	}
	
	@RequestMapping(value="/category/{id}", method = RequestMethod.GET)
	public Category findById(@PathVariable Long id){
		return this.categoryService.findById(id);
	}
	
	@RequestMapping(value="/category", method = RequestMethod.POST)
	public Category create(@RequestBody Category category) throws Exception {
		return this.categoryService.create(category);
	}
	
	@RequestMapping(value="/category", method = RequestMethod.PUT)
	public Category update(@RequestBody Category category) throws Exception {
		return this.categoryService.update(category);
	}
	
	@RequestMapping(value="/category/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id){
		this.categoryService.delete(id);
	}
}