package br.com.javapress.test.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.javapress.domain.entity.recipe.Step;
import br.com.javapress.domain.repository.recipe.IStepRepository;
import br.com.javapress.test.config.TestConfiguration;

public class StepRepositoryTest extends TestConfiguration{

	@Autowired
	private IStepRepository stepRepository;
	
	@Test
	public void testSave(){
		//Given
		Step step = new Step();
		step.setDescription("new step");
		
		//When
		this.stepRepository.save(step);
		
		//Then
		Assert.assertNotNull(step.getId());
		
		//Given
		step.setDescription("other step");
		
		//When
		this.stepRepository.save(step);
		
		//Then
		Step dbStep = this.stepRepository.findOne(step.getId());
		Assert.assertEquals(dbStep.getDescription(), step.getDescription());
	}
	
	@Test
	public void testDelete(){
		//Given
		Step step = new Step();
		step.setDescription("new step");
		this.stepRepository.save(step);
		
		Assert.assertNotNull(step.getId());
		
		//When
		this.stepRepository.delete(step);
		
		//Then
		Step dbStep = this.stepRepository.findOne(step.getId());
		Assert.assertNull(dbStep);
	}
	
	@Test
	public void testFindAll(){
		//Given
		Step step = new Step();
		step.setDescription("new step");
		this.stepRepository.save(step);
		
		Step step2 = new Step();
		step.setDescription("new step2");
		this.stepRepository.save(step2);
		
		//When
		List<Step> steps = this.stepRepository.findAll();
		Assert.assertTrue(steps.size()>1);
	}
}
