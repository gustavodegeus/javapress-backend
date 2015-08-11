package br.com.javapress.test.integration.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import br.com.javapress.domain.dto.SearchCategoryDto;
import br.com.javapress.domain.entity.post.Category;
import br.com.javapress.domain.entity.post.CategoryType;
import br.com.javapress.domain.repository.post.ICategoryRepository;
import br.com.javapress.test.config.ControllerTestConfiguration;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CategoryControllerTest extends ControllerTestConfiguration {

    @Autowired 
    private ICategoryRepository categoryRepository;

    @Test
    public void shouldFindById() throws Exception {
    	Category category = new Category();
    	category.setName(getRandomString());
    	category.setType(CategoryType.POST);
    	this.categoryRepository.save(category);
    	
        this.mockMvc.perform(get("/category/{id}",category.getId())
        	.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
            .andExpect(jsonPath("$.name").value(category.getName()))
    		.andExpect(jsonPath("$.id").value(category.getId().intValue()));
    }

    @Test
    public void shouldFindAll() throws Exception {
    	
    	Category category = new Category();
    	category.setName(getRandomString());
    	category.setType(CategoryType.POST);
    	this.categoryRepository.save(category);
    	Category category2 = new Category();
    	category2.setName(getRandomString());
    	category2.setType(CategoryType.POST);
    	this.categoryRepository.save(category2);
    	
    	SearchCategoryDto searchDto = new SearchCategoryDto(null, CategoryType.POST, null);
    	
        MvcResult result = this.mockMvc.perform(get("/categories")
    		.param("name", searchDto.getName())
    		.param("type", searchDto.getType().toString())
    		.param("parentName", searchDto.getParentName())
    		.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
            .andExpect(jsonPath("$.*").isArray())
            .andReturn();
        
        List<Category> categories = deserializeJsonToCategoryList(result);
        
        assertTrue(categories.size() >=2);
    }

    @Test
    public void shouldCreate() throws Exception {
    	Category parent = new Category();
    	parent.setName(getRandomString());
    	parent.setType(CategoryType.POST);
    	this.categoryRepository.save(parent);
    	Category category = new Category();
    	category.setName(getRandomString()); 
    	category.setType(CategoryType.POST);
    	category.setParent(parent);
    	
        this.mockMvc.perform(post("/category").contentType(MediaType.APPLICATION_JSON_VALUE)
        	.content(asJsonString(category)).accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
            .andExpect(jsonPath("$.name").value(category.getName()));
    }
    
    @Test
    public void shouldUpdate() throws Exception {
    	Category category = new Category();
    	category.setName(getRandomString());
    	category.setType(CategoryType.POST);
//    	this.categoryRepository.save(category);
    	
    	category.setName(getRandomString());
    	
        this.mockMvc.perform(put("/category").contentType(MediaType.APPLICATION_JSON_VALUE)
        	.content(asJsonString(category)).accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
            .andExpect(jsonPath("$.name").value(category.getName()));
        
        Category dbCategory = this.categoryRepository.findOne(category.getId());
        assertEquals(dbCategory.getName(), category.getName());
    }
    
    @Test
    public void shouldDelete() throws Exception {
    	Category category = new Category();
    	category.setName(getRandomString());
    	category.setType(CategoryType.POST);
    	this.categoryRepository.save(category);
    	
    	this.mockMvc.perform(delete("/category/{id}",category.getId()));
        
        Category dbCategory = this.categoryRepository.findOne(category.getId());
        assertNull(dbCategory);
    }
    
    private List<Category> deserializeJsonToCategoryList(MvcResult result) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		String content = result.getResponse().getContentAsString();
        return mapper.readValue(content, List.class);
	}
}