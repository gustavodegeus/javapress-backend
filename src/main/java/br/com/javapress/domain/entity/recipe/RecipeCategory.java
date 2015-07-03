package br.com.javapress.domain.entity.recipe;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import br.com.javapress.domain.entity.AbstractEntity;

@Entity
public class RecipeCategory extends AbstractEntity{

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