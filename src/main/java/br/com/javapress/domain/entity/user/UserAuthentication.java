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
    
	
	public String getName() {
		return user.getUsername();
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getAuthorities();
	}
	
	public Object getCredentials() {
		return user.getPassword();
	}
	
	public Object getDetails() {
		return user;
	}
	
	public Object getPrincipal() {
		return user.getUsername();
	}
	
	public boolean isAuthenticated() {
		return authenticated;
	}
	
	public void setAuthenticated(boolean isAuthenticated)
			throws IllegalArgumentException {
		this.authenticated = isAuthenticated;
	}
}
