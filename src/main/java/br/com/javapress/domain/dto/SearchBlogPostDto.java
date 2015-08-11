package br.com.javapress.domain.dto;

public class SearchBlogPostDto {

	private String title;
	private Long categoryId;
	
	public SearchBlogPostDto(String title, Long categoryId) {
		this.setTitle(title);
		this.setCategoryId(categoryId);
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
}