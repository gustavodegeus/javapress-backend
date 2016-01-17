package br.com.javapress.domain.entity.recipe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import br.com.javapress.domain.entity.AbstractEntity;

@Entity
@SequenceGenerator(name = "ingredient_gen", sequenceName = "INGREDIENT_SEQUENCE", allocationSize=1)
public class Ingredient extends AbstractEntity  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingredient_gen")
	private Long id;
	@Column(unique=true)
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
