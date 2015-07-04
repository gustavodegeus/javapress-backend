package br.com.javapress.domain.entity.post;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "blog_post_gen", sequenceName = "BLOG_POST_SEQUENCE")
public class BlogPost extends Post {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blog_post_gen")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
