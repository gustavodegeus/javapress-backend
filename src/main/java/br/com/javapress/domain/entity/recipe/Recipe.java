package br.com.javapress.domain.entity.recipe;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import br.com.javapress.domain.entity.post.Post;

@Entity
@SequenceGenerator(name = "recipe_gen", sequenceName = "RECIPE_SEQUENCE")
public class Recipe extends Post{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipe_gen")
	private Long id;
	private String cookTime;
	private String servings;
	private int rating;
	@OneToMany
	private Set<Ingredient> ingredients;
	@OneToMany
	private Set<Step>steps;
	@Enumerated(EnumType.ORDINAL)
	private Dificulty dificulty;
	
	public String getCookTime() {
		return cookTime;
	}
	public void setCookTime(String cookTime) {
		this.cookTime = cookTime;
	}
	public String getServings() {
		return servings;
	}
	public void setServings(String servings) {
		this.servings = servings;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public Set<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	public Set<Step> getSteps() {
		return steps;
	}
	public void setSteps(Set<Step> steps) {
		this.steps = steps;
	}
	public Dificulty getDificulty() {
		return dificulty;
	}
	public void setDificulty(Dificulty dificulty) {
		this.dificulty = dificulty;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
