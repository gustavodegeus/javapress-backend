package br.com.javapress.domain.entity.post;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import br.com.javapress.application.validation.annotation.AssertParentCategoryType;

@SequenceGenerator(name = "category_gen", sequenceName = "CATEGORY_SEQUENCE", allocationSize=1)
@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"name"}, name="category_name_uk")})
@AssertParentCategoryType
public class Category{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_gen")
	private Long id;
	private String name;
	@ManyToOne
	private Category parent;
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private CategoryType type;
	
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
	public Category getParent() {
		return parent;
	}
	public void setParent(Category parent) {
		this.parent = parent;
	}
	public CategoryType getType() {
		return type;
	}
	public void setType(CategoryType type) {
		this.type = type;
	}
}