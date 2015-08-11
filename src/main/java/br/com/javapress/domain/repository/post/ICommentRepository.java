package br.com.javapress.domain.repository.post;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.javapress.domain.entity.post.Comment;

public interface ICommentRepository extends
		CrudRepository<Comment, Long> {

	@Query("SELECT comment FROM Comment comment "
			+ "WHERE (LOWER(comment.content) like '%' || LOWER(CAST(:content AS string) || '%') or :content = null) "
			+ "AND (LOWER(comment.answer) like '%' || LOWER(CAST(:answer AS string) || '%') or :answer = null) "
			+ "AND (comment.post.id = :postId or :postId = null)")
	public List<Comment> findByContentAndAnswerAndPostId(@Param("content") String content,
			@Param("answer") String answer,@Param("postId") Long postId);

}
