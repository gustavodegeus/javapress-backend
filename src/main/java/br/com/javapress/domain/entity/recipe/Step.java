package br.com.javapress.domain.entity.recipe;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;

import br.com.javapress.domain.entity.AbstractEntity;

@Entity
@SequenceGenerator(name = "seq_gen", sequenceName = "STEP_SEQUENCE")
public class Step extends AbstractEntity {
	
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
