package br.com.javapress.integration.service;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.javapress.application.infrastructure.config.AppConfig;
import br.com.javapress.domain.entity.recipe.Step;
import br.com.javapress.domain.repository.IStepRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class StepTest {

	@Autowired
	private IStepRepository stepRepository;
	
	@Test
	public void testSave(){
		Step step = new Step();
		step.setCreated(Calendar.getInstance());
		step.setDescription("new step");
		this.stepRepository.save(step);
		
	}
}
