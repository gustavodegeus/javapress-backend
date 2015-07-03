package br.com.javapress.domain.entity.recipe;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import br.com.javapress.domain.entity.AbstractEntity;
import br.com.javapress.domain.entity.user.Client;

@Entity
public class Rating extends AbstractEntity{
	
	@ManyToOne
	private Client client;
	@ManyToOne
	private Recipe recipe;
	private int rating;
	
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Recipe getRecipe() {
		return recipe;
	}
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	

}
