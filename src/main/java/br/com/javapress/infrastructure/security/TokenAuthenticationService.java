package br.com.javapress.infrastructure.security;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

import br.com.javapress.domain.entity.user.User;
import br.com.javapress.domain.entity.user.UserAuthentication;
import br.com.javapress.domain.entity.user.UserRole;
import io.jsonwebtoken.ExpiredJwtException;

public class TokenAuthenticationService {

	private static final String AUTH_HEADER_NAME = "Authorization";
	private TokenHandler tokenHandler;

	public void addAuthenticationToken(HttpServletResponse response, UserAuthentication authentication) {
		final User user = (User) authentication.getDetails();
		response.addHeader(AUTH_HEADER_NAME, this.tokenHandler.createTokenForUser(user));
	}

	public Authentication getAuthentication(String token) {
		if (token != null) {
			try {
				final User user = tokenHandler.parseUserFromToken(token);
				if (user != null) {
					ArrayList<UserRole> authorities = new ArrayList<UserRole>();
					// TODO --> Fech authorities from database
					authorities.add(UserRole.ADMIN);
					authorities.add(UserRole.CLIENT);
					user.setAuthorities(authorities);
					return new UserAuthentication(user);
				}
			} catch (ExpiredJwtException expiredJwtException) {
				System.out.println("Token expired");
				return null;
			}
		}
		return null;
	}

	public void refreshToken(String token, HttpServletResponse httpResponse) {
		if (this.tokenHandler.shouldBeRefreshed(token)) {
			String refreshedToken = this.tokenHandler.createTokenForUser(this.tokenHandler.parseUserFromToken(token));
			httpResponse.setHeader(AUTH_HEADER_NAME, refreshedToken);
		}
	}

	public TokenHandler getTokenHandler() {
		return tokenHandler;
	}

	public void setTokenHandler(TokenHandler tokenHandler) {
		this.tokenHandler = tokenHandler;
	}

}