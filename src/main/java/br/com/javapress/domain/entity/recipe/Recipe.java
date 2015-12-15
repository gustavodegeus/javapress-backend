package br.com.javapress.domain.entity.recipe;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;

import br.com.javapress.application.validation.annotation.AssertPostCategoryType;
import br.com.javapress.domain.entity.post.CategoryType;
import br.com.javapress.domain.entity.post.Post;

@Entity
@PrimaryKeyJoinColumn(name = "recipe_id", referencedColumnName = "post_id")
@AssertPostCategoryType(value=CategoryType.RECIPE)
public class Recipe extends Post{

	private static String PATH = "recipes";
	private static String STEPS_PATH = "/steps/";
	private static String INGREDIENTS_PATH = "/ingredients/";
			
	private String cookTime;
	private String servings;
	@Transient
	private int rating;
	@OneToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE}, fetch=FetchType.EAGER, orphanRemoval=true)
	private Set<Ingredient> ingredients;
	@OneToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE}, fetch=FetchType.EAGER, orphanRemoval=true)
	private Set<Step> steps;
	@Enumerated(EnumType.ORDINAL)
	private Dificulty dificulty;
	
	public Recipe(){
		this.steps = new HashSet<Step>();
		this.ingredients = new HashSet<Ingredient>();
	}
	
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
	public void addIngredient(Ingredient ingredient){
		this.ingredients.add(ingredient);
	}
	public void addStep(Step step){
		this.steps.add(step);
	}

	public String getImagePath() {
		return PATH;
	}
}