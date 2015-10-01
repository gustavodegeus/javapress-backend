package br.com.javapress.application.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.javapress.application.infrastructure.config.security.TokenAuthenticationService;
import br.com.javapress.domain.dto.UserDto;
import br.com.javapress.domain.entity.user.User;
import br.com.javapress.domain.entity.user.UserAuthentication;
import br.com.javapress.domain.service.CustomUserDetailsService;

@RestController
public class AuthenticationController {
	
	@Autowired
	private TokenAuthenticationService tokenAuthenticationService;

	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(@RequestBody UserDto userDto, HttpServletResponse response) throws Exception {
		User user = this.userDetailsService.login(userDto);
		if ( user != null ){
			UserAuthentication authentication = new UserAuthentication(user);
			tokenAuthenticationService.addAuthentication(response, authentication);
		}
	}
	
}
