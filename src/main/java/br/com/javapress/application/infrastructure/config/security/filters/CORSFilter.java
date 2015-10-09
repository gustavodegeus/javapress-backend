package br.com.javapress.application.infrastructure.config.security.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
@Component
public class CORSFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		
		HttpServletResponse filteredResponse = (HttpServletResponse) response;
		filteredResponse.setHeader("Access-Control-Allow-Origin", "*");
		filteredResponse.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		filteredResponse.setHeader("Access-Control-Max-Age", "3600");
		filteredResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
		filteredResponse.setHeader("Access-Control-Expose-Headers", "Authorization");
		HttpServletRequest req = (HttpServletRequest) request;
		if (!req.getMethod().equals("OPTIONS")) {
			filterChain.doFilter(request, filteredResponse);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {

	}
	
	public void destroy() {
		
	}

}
