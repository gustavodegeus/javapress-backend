package br.com.javapress.application.infrastructure.config.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import br.com.javapress.domain.entity.user.User;
import br.com.javapress.domain.entity.user.UserAuthentication;

public class TokenAuthenticationService {
	 
    private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
    private TokenHandler tokenHandler;
 
	public void addAuthentication(HttpServletResponse response, UserAuthentication authentication) {
        final User user = (User) authentication.getDetails();
        response.addHeader(AUTH_HEADER_NAME, tokenHandler.createTokenForUser(user));
    }
 
    public Authentication getAuthentication(HttpServletRequest request) {
        final String token = request.getHeader(AUTH_HEADER_NAME);
        if (token != null) {
            final User user = tokenHandler.parseUserFromToken(token);
            if (user != null) {
                return new UserAuthentication(user);
            }
        }
        return null;
    }

	public TokenHandler getTokenHandler() {
		return tokenHandler;
	}

	public void setTokenHandler(TokenHandler tokenHandler) {
		this.tokenHandler = tokenHandler;
	}
}