package br.com.javapress.domain.entity.user;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class UserAuthentication implements Authentication{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1848829381355077002L;
	
	private final User user;
    private boolean authenticated = true;
    
    public UserAuthentication(User user) {
        this.user = user;
    }
    
	@Override
	public String getName() {
		return user.getUsername();
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getAuthorities();
	}
	@Override
	public Object getCredentials() {
		return user.getPassword();
	}
	@Override
	public Object getDetails() {
		return user;
	}
	@Override
	public Object getPrincipal() {
		return user.getUsername();
	}
	@Override
	public boolean isAuthenticated() {
		return authenticated;
	}
	@Override
	public void setAuthenticated(boolean isAuthenticated)
			throws IllegalArgumentException {
		this.authenticated = isAuthenticated;
	}
}
