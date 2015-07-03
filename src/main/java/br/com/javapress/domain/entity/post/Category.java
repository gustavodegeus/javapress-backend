package br.com.javapress.domain.entity.post;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import br.com.javapress.domain.entity.AbstractEntity;

@Entity
public class Category extends AbstractEntity{

	private String name;
	@ManyToOne
	private Category parent;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Category getParent() {
		return parent;
	}
	public void setParent(Category parent) {
		this.parent = parent;
	}
}
