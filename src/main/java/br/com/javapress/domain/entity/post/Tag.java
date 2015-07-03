package br.com.javapress.domain.entity.post;

import javax.persistence.Entity;

import br.com.javapress.domain.entity.AbstractEntity;

@Entity
public class Tag extends AbstractEntity{

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
