package br.com.javapress.domain.repository.post;

import org.springframework.data.repository.CrudRepository;

import br.com.javapress.domain.entity.post.Comment;

public interface ICommentRepository extends
		CrudRepository<Comment, Long> {

}
