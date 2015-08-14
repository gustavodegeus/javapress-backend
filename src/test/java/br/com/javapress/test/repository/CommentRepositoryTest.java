package br.com.javapress.test.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.validation.Validator;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.javapress.domain.entity.post.BlogPost;
import br.com.javapress.domain.entity.post.Comment;
import br.com.javapress.domain.repository.post.ICommentRepository;
import br.com.javapress.domain.repository.post.IPostRepository;
import br.com.javapress.test.config.TestConfiguration;

public class CommentRepositoryTest extends TestConfiguration {

	@Autowired
	private ICommentRepository commentRepository;
	
	@Autowired
	private IPostRepository postRepository;
	
	@Autowired
	private Validator validator;
	
	@Test
	public void shouldCreateAndUpdateComment(){
		//Given
		Comment comment = getObject();
		
		//When
		this.commentRepository.save(comment);
		
		//Then
		assertNotNull("Id should not be null", comment.getId());
		assertNotNull("Id should not be null", comment.getCreated());
		
		//Given
		comment.setContent("New content");
		
		//When
		this.commentRepository.save(comment);
		
		//Then
		Comment dbComment = (Comment) this.commentRepository.findOne(comment.getId());
		assertEquals("Content should be equal", dbComment.getContent(), comment.getContent());
	}
	
	@Test
	public void shouldDelete(){
		//Given
		Comment comment = this.commentRepository.save(getObject());
		assertNotNull(comment.getId());
		
		//When
		this.commentRepository.delete(comment);
		
		//Then
		Comment dbComment = this.commentRepository.findOne(comment.getId());
		assertNull("Comment shouldn't exist", dbComment);
	}
	
	@Test
	public void shouldFindAll(){
		//Given
		Comment comment = getObject();
		this.commentRepository.save(comment);
		
		Comment comment2 = getObject();
		this.commentRepository.save(comment2);
		
		//When
		List<Comment> comments = this.commentRepository.findByContentAndAnswerAndPostId(null, null, null);
		//Then
		assertTrue("Size should be bigger than one", comments.size()>1);
		
		//When
		comments = this.commentRepository.findByContentAndAnswerAndPostId(comment.getContent(), null, null);
		//Then
		assertEquals(1, comments.size());
		
		//When
		comments = this.commentRepository.findByContentAndAnswerAndPostId(null, null, comment.getPost().getId());
		//then
		assertEquals(1, comments.size());
	}
	
	private Comment getObject(){
		Comment comment = new Comment();
		comment.setContent(getRandomString());
		comment.setPublished(false);
		comment.setSenderEmail("email@email.com");
		comment.setSenderName("Fulano de tal");
		comment.setPublished(false);
		comment.setPost(this.postRepository.save(this.getBlogPostObject()));
		return comment;
	}
	
	private BlogPost getBlogPostObject(){
		BlogPost post = new BlogPost();
		post.setTitle(getRandomString());
		post.setContent(getRandomString());
		post.setPublished(false);
		return post;
	}
}
