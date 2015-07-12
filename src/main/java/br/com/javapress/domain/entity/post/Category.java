package br.com.javapress.domain.entity.post;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeInfo.As;

import br.com.javapress.domain.entity.recipe.RecipeCategory;

@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@SequenceGenerator(name = "category_gen", sequenceName = "CATEGORY_SEQUENCE", allocationSize=1)
@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.PROPERTY, property = "classType")
@JsonSubTypes({
    @JsonSubTypes.Type(value = PostCategory.class, name = "PostCategory"),
    @JsonSubTypes.Type(value = RecipeCategory.class, name = "RecipeCategory")
})
public abstract class Category<T extends Category<T>>{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_gen")
	private Long id;
	private String name;
	@ManyToOne(targetEntity=Category.class)
	private T parent;
	@Column(insertable=false,updatable=false)
	private String type;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public T getParent() {
		return parent;
	}
	public void setParent(T parent) {
		this.parent = parent;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}