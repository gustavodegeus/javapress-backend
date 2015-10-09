package br.com.javapress.test.integration.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.javapress.domain.dto.SuccessMessageDto;
import br.com.javapress.domain.entity.post.BlogPost;
import br.com.javapress.domain.repository.post.IPostRepository;
import br.com.javapress.test.config.ControllerTestConfiguration;

public class BlogPostControllerTest extends ControllerTestConfiguration {

    @Autowired 
    private IPostRepository postRepository;

    @Test
    public void shouldFindById() throws Exception {
    	BlogPost post = this.getObject();
    	this.postRepository.save(post);
    	
        this.mockMvc.perform(get("/post/{id}",post.getId())
        	.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
            .andExpect(jsonPath("$.title").value(post.getTitle()))
    		.andExpect(jsonPath("$.id").value(post.getId().intValue()));
    }

    @Test
    public void shouldFindAll() throws Exception {
    	
    	BlogPost post = this.getObject();
    	this.postRepository.save(post);
    	BlogPost post2 = this.getObject();
    	this.postRepository.save(post2);
    	
        MvcResult result = this.mockMvc.perform(get("/blogPosts")
    		.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
            .andExpect(jsonPath("$.*").isArray())
            .andReturn();
        
        List<BlogPost> posts = deserializeJsonToBlogPostList(result);
        
        assertTrue(posts.size() >=2);
        
        result = this.mockMvc.perform(get("/blogPosts")
        		.param("title", post.getTitle())
        		.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(jsonPath("$.*").isArray())
                .andReturn();
            
         posts = deserializeJsonToBlogPostList(result);
         assertEquals("Post title", post.getTitle(), posts.get(0).getTitle());
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void shouldCreate() throws Exception {
    	BlogPost post = this.getObject();
    	
    	MvcResult result = this.mockMvc.perform(post("/post").contentType(MediaType.APPLICATION_JSON_VALUE)
        	.content(asJsonString(post)).accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
            .andExpect(jsonPath("$.value.title").value(post.getTitle()))
            .andReturn();
        
    	ObjectMapper mapper = new ObjectMapper();
    	SuccessMessageDto successMessageDto = mapper.readValue(result.getResponse().getContentAsString(), SuccessMessageDto.class);
    	post = this.converJsonIntoObject((LinkedHashMap<String, Object>) successMessageDto.getValue());
        assertNotNull("Post id should not be null", post.getId());
    }
    
    @Test
    public void shouldUpdate() throws Exception {
    	BlogPost post = this.getObject();
    	this.postRepository.save(post);
    	
    	post.setTitle(getRandomString());
    	
    	this.mockMvc.perform(put("/post").contentType(MediaType.APPLICATION_JSON_VALUE)
        	.content(asJsonString(post)).accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
            .andExpect(jsonPath("$.value.title").value(post.getTitle()))
            .andReturn();
        
    }
    
    @Test
    public void shouldDelete() throws Exception {
    	BlogPost post = this.getObject();
    	this.postRepository.save(post);
    	
    	this.mockMvc.perform(delete("/post/{id}",post.getId()));
    	
    	assertNull("Shouldn't find deleted post", postRepository.findOne(post.getId()));
        
    }

	private List<BlogPost> deserializeJsonToBlogPostList(MvcResult result) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<BlogPost> posts = new ArrayList<BlogPost>();

		String content = result.getResponse().getContentAsString();
		List<LinkedHashMap<String, Object>> jsonPosts = mapper.readValue(content, List.class);
        
        for (LinkedHashMap<String, Object> linkedHashMap : jsonPosts) {
			posts.add(this.converJsonIntoObject(linkedHashMap));
		}
        
        return posts;
	}
	
	private BlogPost converJsonIntoObject(LinkedHashMap<String, Object> linkedHashMap){
		BlogPost post = new BlogPost();
		post.setId(Long.valueOf(((Integer)linkedHashMap.get("id")).longValue() ));
		post.setTitle((String)linkedHashMap.get("title"));
		return post;
	}
    
    private BlogPost getObject(){
		BlogPost post = new BlogPost();
		post.setTitle(getRandomString());
		post.setContent(getRandomString());
		post.setPublished(false);
		return post;
	}
}