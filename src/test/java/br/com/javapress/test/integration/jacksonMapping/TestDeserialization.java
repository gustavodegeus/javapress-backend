package br.com.javapress.test.integration.jacksonMapping;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import br.com.javapress.domain.entity.post.Category;
import br.com.javapress.domain.entity.post.PostCategory;
import br.com.javapress.test.config.TestConfiguration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestDeserialization extends TestConfiguration{

	@Test
	public void shoudDeserializeCategory() throws JsonProcessingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
	    Category<?> category = mapper.reader(Category.class).readValue("{ \"type\":\"postCategory\", \"name\":\"Test post category\", \"parent\":null }");

	    assertTrue(category instanceof PostCategory);
	}
	
	@Test
	public void shoudDeserializeCategoryWithParent() throws JsonProcessingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
	    Category<?> category = mapper.reader(Category.class).readValue(""
	    		+ "{ \"type\":\"postCategory\", "
	    		+ "\"name\":\"Test post category\", "
	    		+ "\"parent\":{ \"id\": 32, \"type\":\"postCategory\"}}");

	    assertTrue(category instanceof PostCategory);
	}
}