package br.com.javapress.domain.entity.post;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import br.com.javapress.application.validation.annotation.AssertIdNotNullForUpdate;
import br.com.javapress.application.validation.groups.PreUpdate;
import br.com.javapress.domain.entity.AbstractEntity;
import br.com.javapress.domain.entity.user.User;

@Entity
@SequenceGenerator(name = "comment_gen", sequenceName = "COMMENT_SEQUENCE", allocationSize=1)
public class Comment extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_gen")
	@AssertIdNotNullForUpdate(groups=PreUpdate.class)
	private Long id;
	@Column(columnDefinition="TEXT")
	private String content;
	@Column(columnDefinition="TEXT")
	private String answer;
	private Boolean published;
	private String senderName;
	private String senderEmail;
	@ManyToOne(optional=false,fetch=FetchType.EAGER)
	@NotNull
	private Post post;
	@ManyToOne
	private User user;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Boolean getPublished() {
		return published;
	}
	public void setPublished(Boolean published) {
		this.published = published;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getSenderEmail() {
		return senderEmail;
	}
	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
}
