package br.com.javapress.domain.dto;

import br.com.javapress.domain.entity.post.CategoryType;

public class SearchCategoryDto {

	private String name;
	private CategoryType type;
	private String parentName;
	
	
	public SearchCategoryDto(String name, CategoryType type, String parentName) {
		this.name = name;
		this.type = type;
		this.parentName = parentName;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CategoryType getType() {
		return type;
	}
	public void setType(CategoryType type) {
		this.type = type;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}
