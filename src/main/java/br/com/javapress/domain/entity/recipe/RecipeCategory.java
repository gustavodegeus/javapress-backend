package br.com.javapress.domain.entity.recipe;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import br.com.javapress.domain.entity.AbstractEntity;

@Entity
@SequenceGenerator(name = "recipe_category_gen", sequenceName = "RECIPE_CATEGORY_SEQUENCE")
public class RecipeCategory extends AbstractEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipe_category_gen")
	private Long id;
	private String name;
	@ManyToOne
	private RecipeCategory parent;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public RecipeCategory getParent() {
		return parent;
	}
	public void setParent(RecipeCategory parent) {
		this.parent = parent;
	}
}