package br.com.javapress.domain.dto;

public class SearchCommentDto {

	private String content;
	private String answer;
	private Long postId;
	
	public SearchCommentDto(String content, String answer, Long postId) {
		this.content = content;
		this.answer = answer;
		this.postId = postId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}
}