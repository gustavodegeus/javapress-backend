package br.com.javapress.domain.entity.user;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Rafael Arenas
 * @since 27/08/2013
 * @version 1.0
 * @category Enum
 */
public enum UserRole implements GrantedAuthority
{
	//DON'T CHANGE ORDER
	CLIENT, ADMIN;
	
	/**
	 * 
	 */
	@Override
	public String getAuthority()
	{
		return this.name();
	}
}