package br.com.javapress.test.integration.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.javapress.test.TestConfiguration;

public class ControllerTestConfiguration extends TestConfiguration {

	protected MockMvc mockMvc;
	
	@Autowired
    protected WebApplicationContext wac;

	 
	@Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
        		.defaultRequest(get("/").accept(MediaType.APPLICATION_JSON))
				.alwaysExpect(status().isOk())
				.alwaysExpect(content().contentType("application/json;charset=UTF-8"))
				.build();
    }
}
