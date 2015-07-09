package br.com.javapress.domain.entity.recipe;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import br.com.javapress.domain.entity.post.Category;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("recipeCategory")
public class RecipeCategory extends Category<RecipeCategory>{

	public RecipeCategory(){
		this.setType("recipeCategory");
	}
}