package br.com.javapress.application.infrastructure.config.security;

import org.joda.time.DateTime;

import br.com.javapress.domain.entity.user.User;
import br.com.javapress.domain.service.CustomUserDetailsService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenHandler {

	private String secret;
	private CustomUserDetailsService userDetailsService;

	public boolean shouldBeRefreshed(String token) {
		DateTime issueAt = new DateTime(Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody().getIssuedAt());		
		return (issueAt.plusHours(1).getMillis() <= DateTime.now().getMillis());
	}

	public User parseUserFromToken(String token) {
		String username = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
		return (User) userDetailsService.loadUserByUsername(username);
	}

	public String createTokenForUser(User user) {
		return Jwts.builder().setSubject(user.getUsername()).signWith(SignatureAlgorithm.HS512, secret)
				.setExpiration(DateTime.now().plusWeeks(2).toDate()).compact();
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public CustomUserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(CustomUserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	public String getSecret() {
		return secret;
	}
}