package br.com.javapress.domain.entity.post;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "blog_post_id", referencedColumnName = "post_id")
public class BlogPost extends Post {

	@ManyToOne
	private PostCategory category;

	public PostCategory getCategory() {
		return category;
	}

	public void setCategory(PostCategory category) {
		this.category = category;
	}
}
