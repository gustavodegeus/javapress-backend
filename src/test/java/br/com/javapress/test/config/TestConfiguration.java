package br.com.javapress.test.config;

import java.util.UUID;

import javax.transaction.Transactional;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import br.com.javapress.application.infrastructure.config.AppConfig;
import br.com.javapress.application.infrastructure.config.MvcConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, MvcConfig.class})
@WebAppConfiguration
@TransactionConfiguration(defaultRollback=true)
@Transactional
public abstract class TestConfiguration {

	protected String getRandomString(){
		return UUID.randomUUID().toString();
	}
}
