	package br.com.javapress.test.integration.jacksonMapping;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.javapress.domain.entity.post.Category;
import br.com.javapress.domain.entity.post.CategoryType;
import br.com.javapress.test.config.TestConfiguration;

public class TestDeserialization extends TestConfiguration{

	@Test
	public void shoudDeserializeCategory() throws JsonProcessingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
	    Category category = mapper.reader(Category.class).readValue("{ \"type\":\"POST\", \"name\":\"Test post category\", \"parent\":null }");
	    assertTrue(category.getType().equals(CategoryType.POST));
	}
	
	@Test
	public void shoudDeserializeCategoryWithParent() throws JsonProcessingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		Category test = mapper.readValue(""
	    		+ "{ \"type\":\"RECIPE\", "
	    		+ "\"name\":\"Test post category\", "
	    		+ "\"parent\":{ \"id\": 32, \"type\":\"RECIPE\"}}", Category.class);
		
		assertTrue(test.getParent().getType().equals(CategoryType.RECIPE));
		
	    Category category = mapper.reader(Category.class).readValue(""
	    		+ "{ \"type\":\"POST\", "
	    		+ "\"name\":\"Test post category\", "
	    		+ "\"parent\":{ \"id\": 32, \"type\":\"POST\"}}");

	    assertTrue(category.getType().equals(CategoryType.POST));
	}
}