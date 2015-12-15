package br.com.javapress.infrastructure.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.javapress.domain.entity.user.UserRole;
import br.com.javapress.domain.service.CustomUserDetailsService;
import br.com.javapress.infrastructure.security.TokenAuthenticationService;
import br.com.javapress.infrastructure.security.TokenHandler;
import br.com.javapress.infrastructure.security.filters.CORSFilter;
import br.com.javapress.infrastructure.security.filters.StatelessAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CustomUserDetailsService userDetailsService;	
	@Autowired
	private StatelessAuthenticationFilter statelessAuthenticationFilter;
	@Autowired
	private CORSFilter corsFilter;
    
	 public SecurityConfig() {
	      super(true);
    }
	 
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    auth
	      .inMemoryAuthentication()
	        .withUser("user")
	          .password("password")
	          .roles("USER")
	          .and()
	          .withUser("admin") 
	          .password("password")
	          .roles("ADMIN","USER");
	  }

	  @Override
	  public void configure(WebSecurity web) throws Exception {
	    web
	      .ignoring()
	         .antMatchers("/resources/**");
	  }

	  @Override
	  protected void configure(HttpSecurity http) throws Exception {		  
		  http
		  .exceptionHandling().accessDeniedHandler(new AccessDeniedHandlerImpl())
		  .and()
		  .anonymous()
		  .and()
		  .authorizeRequests()
		   //allow anonymous resource requests
		   .antMatchers("/favicon.ico").permitAll()
		   .antMatchers("/resources/**").permitAll()
		   //allow anonymous POSTs to login
		   .antMatchers(HttpMethod.POST, "/login").permitAll()
		   .antMatchers("/**").hasAnyAuthority(UserRole.ADMIN.getAuthority(), UserRole.CLIENT.getAuthority())
		   .and()
		   // Custom Token based authentication based on the header previously given to the client
           .addFilterBefore(statelessAuthenticationFilter,
                    UsernamePasswordAuthenticationFilter.class)
		  .addFilterBefore(corsFilter,
                  StatelessAuthenticationFilter.class);
	  }
	  
	  @Override
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		  auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	  }
	 
	  @Bean
	  @Override
	  public AuthenticationManager authenticationManagerBean() throws Exception {
	      return super.authenticationManagerBean();
	  }
	  	  
	  @Bean 
	  public TokenAuthenticationService tokenAuthenticationService(){
		  TokenAuthenticationService tokenAuthenticationService = new TokenAuthenticationService();
		  tokenAuthenticationService.setTokenHandler(tokenHandler());
		  return tokenAuthenticationService;
	  }
	  
	  @Bean 
	  public TokenHandler tokenHandler(){
		  TokenHandler tokenHandler = new TokenHandler();
		  tokenHandler.setSecret("Xalablau");
		  tokenHandler.setUserDetailsService(userDetailsService);
		  return tokenHandler;
	  }
}