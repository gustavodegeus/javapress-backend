package br.com.javapress.domain.entity.post;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import br.com.javapress.domain.entity.AbstractEntity;
import br.com.javapress.domain.entity.user.User;

@Entity
@SequenceGenerator(name = "comment_gen", sequenceName = "COMMENT_SEQUENCE")
public class Comment extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_gen")
	private Long id;
	private String content;
	private Boolean published;
	@ManyToOne
	private User user;
	@ManyToOne
	private Comment parent;
	
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
	public Comment getParent() {
		return parent;
	}
	public void setParent(Comment parent) {
		this.parent = parent;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
