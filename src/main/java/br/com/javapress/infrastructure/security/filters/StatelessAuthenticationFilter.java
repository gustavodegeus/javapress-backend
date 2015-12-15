package br.com.javapress.infrastructure.security.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import br.com.javapress.infrastructure.security.TokenAuthenticationService;
import io.jsonwebtoken.ExpiredJwtException;

@Component
public class StatelessAuthenticationFilter extends GenericFilterBean {

	private static final String AUTH_HEADER_NAME = "Authorization";
	@Autowired
	private TokenAuthenticationService authenticationService;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String token = httpRequest.getHeader(AUTH_HEADER_NAME);
		if (!StringUtils.isEmpty(token)) {
			try {
				SecurityContextHolder.getContext()
						.setAuthentication(authenticationService.getAuthentication(token.substring(7)));
				authenticationService.refreshToken(token.substring(7), httpResponse);
			} catch (ExpiredJwtException e) {
				throw new AccessDeniedException("Invalid Token");				
			}
		}
		filterChain.doFilter(request, httpResponse);
		SecurityContextHolder.getContext().setAuthentication(null);
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}
}