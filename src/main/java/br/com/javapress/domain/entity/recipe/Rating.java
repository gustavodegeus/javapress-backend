package br.com.javapress.domain.entity.recipe;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import br.com.javapress.domain.entity.AbstractEntity;
import br.com.javapress.domain.entity.user.Client;

@Entity
@SequenceGenerator(name = "rating_gen", sequenceName = "RATING_SEQUENCE", allocationSize=1)
public class Rating extends AbstractEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rating_gen")
	private Long id;
	@ManyToOne(optional=false)
	@NotNull
	private Client client;
	@ManyToOne(optional=false)
	@NotNull
	private Recipe recipe;
	@NotNull
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
