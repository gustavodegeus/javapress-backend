package br.com.javapress.application.jackson;

import java.io.IOException;

import br.com.javapress.domain.entity.post.Category;
import br.com.javapress.domain.entity.post.PostCategory;
import br.com.javapress.domain.entity.recipe.RecipeCategory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CategoryDeserializer extends JsonDeserializer<Category<?>> {

	@Override
	public Category<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
			throws IOException, JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonParser);
        
        if(node.get("type").asText().equals("postCategory")){
        	PostCategory parent = mapper.convertValue(node, new TypeReference<PostCategory>() {});
        	return parent;
        }else if (node.get("type").asText().equals("recipeCategory")){
        	RecipeCategory parent = mapper.convertValue(node, new TypeReference<RecipeCategory>() {});
        	return parent;
        } else {
        	return null;
        }
	}

}
