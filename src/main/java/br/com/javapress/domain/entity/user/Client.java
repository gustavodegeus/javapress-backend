package br.com.javapress.domain.entity.user;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import br.com.javapress.domain.entity.recipe.Recipe;

@Entity
public class Client extends User {

	@OneToMany
	private List<Recipe> favoriteRecipes;

	public List<Recipe> getFavoriteRecipes() {
		return favoriteRecipes;
	}

	public void setFavoriteRecipes(List<Recipe> favoriteRecipes) {
		this.favoriteRecipes = favoriteRecipes;
	}

}