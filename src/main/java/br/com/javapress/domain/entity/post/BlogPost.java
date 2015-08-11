package br.com.javapress.domain.entity.post;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import org.springframework.validation.annotation.Validated;

import br.com.javapress.application.validation.annotation.AssertPostCategoryType;

@Entity
@PrimaryKeyJoinColumn(name = "blog_post_id", referencedColumnName = "post_id")
@AssertPostCategoryType(value=CategoryType.POST)
@Validated
public class BlogPost extends Post {

}
