package br.com.javapress.domain.entity.user;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import org.springframework.security.core.GrantedAuthority;

import br.com.javapress.domain.entity.recipe.Recipe;

@Entity
@PrimaryKeyJoinColumn(name = "client_id", referencedColumnName = "user_id")
public class Client extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2178517663184041654L;
	
	@OneToMany
	private List<Recipe> favoriteRecipes;

	public List<Recipe> getFavoriteRecipes() {
		return favoriteRecipes;
	}

	public void setFavoriteRecipes(List<Recipe> favoriteRecipes) {
		this.favoriteRecipes = favoriteRecipes;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		final Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.add(UserRole.CLIENT);
		return Collections.unmodifiableSet( authorities );
	}

}