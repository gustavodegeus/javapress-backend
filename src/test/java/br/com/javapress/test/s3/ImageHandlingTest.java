package br.com.javapress.test.s3;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.javapress.domain.entity.recipe.Recipe;
import br.com.javapress.infrastructure.s3.ImageRepository;
import br.com.javapress.test.config.TestConfiguration;

public class ImageHandlingTest extends TestConfiguration{

	@Autowired 
	private ImageRepository imageRepository;
	
	@Test
	public void testUpload(){
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		
		try{
			assertTrue(imageRepository.uploadImage(recipe));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
