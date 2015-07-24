package br.com.javapress.test.integration.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import br.com.javapress.domain.entity.post.PostCategory;
import br.com.javapress.domain.repository.post.ICategoryRepository;

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
            .andExpect(jsonPath("$.name").value(category.getName()));
    }

    @Test
    public void shouldFindAll() throws Exception {
        this.mockMvc.perform(get("/categories").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
            .andExpect(jsonPath("$.name").value("Lee"));
    }
}
