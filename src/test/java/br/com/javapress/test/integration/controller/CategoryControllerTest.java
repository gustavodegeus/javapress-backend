package br.com.javapress.test.integration.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import br.com.javapress.domain.entity.post.Category;
import br.com.javapress.domain.entity.post.PostCategory;
import br.com.javapress.domain.repository.post.ICategoryRepository;
import br.com.javapress.test.config.ControllerTestConfiguration;

public class CategoryControllerTest extends ControllerTestConfiguration {

    @Autowired 
    private ICategoryRepository categoryRepository;

    @Test
    public void shouldFindById() throws Exception {
    	PostCategory category = new PostCategory();
    	category.setName(getRandomString());
    	this.categoryRepository.save(category);
    	
        this.mockMvc.perform(get("/category/{id}",category.getId())
        	.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
            .andExpect(jsonPath("$.name").value(category.getName()))
    		.andExpect(jsonPath("$.id").value(category.getId().intValue()));
    }

    @Test
    public void shouldFindAll() throws Exception {
        this.mockMvc.perform(get("/categories").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
            .andExpect(jsonPath("$.*").isArray());
    }
    
    @Test
    public void shouldCreate() throws Exception {
    	PostCategory category = new PostCategory();
    	category.setName(getRandomString());
    	
        this.mockMvc.perform(post("/category").contentType(MediaType.APPLICATION_JSON_VALUE)
        	.content(asJsonString(category)).accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
            .andExpect(jsonPath("$.name").value(category.getName()));
    }
    
    @Test
    public void shouldUpdate() throws Exception {
    	PostCategory category = new PostCategory();
    	category.setName(getRandomString());
    	this.categoryRepository.save(category);
    	
    	category.setName(getRandomString());
    	
        this.mockMvc.perform(put("/category").contentType(MediaType.APPLICATION_JSON_VALUE)
        	.content(asJsonString(category)).accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
            .andExpect(jsonPath("$.name").value(category.getName()));
        
        Category<?> dbCategory = this.categoryRepository.findOne(category.getId());
        assertEquals(dbCategory.getName(), category.getName());
    }
    
    @Test
    public void shouldDelete() throws Exception {
    	PostCategory category = new PostCategory();
    	category.setName(getRandomString());
    	this.categoryRepository.save(category);
    	
    	this.mockMvc.perform(delete("/category/{id}",category.getId()));
        
        Category<?> dbCategory = this.categoryRepository.findOne(category.getId());
        assertNull(dbCategory);
    }
}
