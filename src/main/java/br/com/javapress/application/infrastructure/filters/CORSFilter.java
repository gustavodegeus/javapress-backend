package br.com.javapress.application.infrastructure.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletResponse;

@WebFilter(    urlPatterns = "/*",
filterName = "CORSFilter",
description = "Filter all JSON endpoints")
public class CORSFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		
		HttpServletResponse filteredResponse = (HttpServletResponse) response;
		filteredResponse.setHeader("Access-Control-Allow-Origin", "*");
		filteredResponse.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		filteredResponse.setHeader("Access-Control-Max-Age", "3600");
		filteredResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		
		filterChain.doFilter(request, filteredResponse);
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
