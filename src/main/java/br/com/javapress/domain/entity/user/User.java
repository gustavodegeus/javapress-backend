package br.com.javapress.domain.entity.user;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.javapress.domain.entity.AbstractEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Inheritance(strategy=InheritanceType.JOINED)
@Entity
@SequenceGenerator(name = "user_gen", sequenceName = "USER_SEQUENCE", allocationSize=1)
@Table(name="\"User\"")
public class User extends AbstractEntity implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8189787418269518866L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gen")
	@Column(name="user_id")
	private Long id;
	
	private String name;
	private String email;
	private String password;
	@Transient
	private boolean isAuthenticated;
	@Transient
	private Object details;
	@Transient
	private long expires;
	@NotNull
	private boolean accountExpired;
	@NotNull
	private boolean accountLocked;
	@NotNull
	private boolean credentialsExpired;
	@NotNull
	private boolean accountEnabled;
	@Transient
	private String newPassword;
	@Transient
	@JsonIgnore
	private Collection<? extends GrantedAuthority> authorities = null;
	@JsonIgnore
	@Transient
	private UserRole userRole;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	
	@JsonIgnore
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public String getUsername(){
		return this.email;
	}
	
	@JsonIgnore
	public String getNewPassword() {
		return newPassword;
	}

	@JsonProperty
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + ": " + getUsername();
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
}
