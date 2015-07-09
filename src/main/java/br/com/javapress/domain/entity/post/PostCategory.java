package br.com.javapress.domain.entity.post;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("postCategory")
public class PostCategory extends Category<PostCategory>{
	
	public PostCategory(){
		this.setType("postCategory");
	}

}