package br.com.javapress.domain.entity.user;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Arenas
 *
 */
@Entity
@PrimaryKeyJoinColumn(name = "admin_id", referencedColumnName = "user_id")
public class Admin extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1119360927173377000L;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		final Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.add(UserRole.ADMIN);
		return Collections.unmodifiableSet( authorities );
	}
}