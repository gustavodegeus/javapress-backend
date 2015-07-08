package br.com.javapress.domain.entity.post;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "blog_post_gen", sequenceName = "BLOG_POST_SEQUENCE", allocationSize=1)
public class BlogPost extends Post {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blog_post_gen")
	private Long id;
	@ManyToOne
	private PostCategory category;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PostCategory getCategory() {
		return category;
	}

	public void setCategory(PostCategory category) {
		this.category = category;
	}
}
