package br.com.javapress.domain.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.javapress.application.validation.groups.PreUpdate;
import br.com.javapress.domain.dto.SearchCommentDto;
import br.com.javapress.domain.dto.SuccessMessageDto;
import br.com.javapress.domain.entity.post.Comment;
import br.com.javapress.domain.repository.post.ICommentRepository;

@Service
public class CommentService {

	@Autowired
	private ICommentRepository commentRepository;
	
	@Autowired
	private Validator validator;
	
	@Transactional
	public SuccessMessageDto create(Comment comment) throws Exception{
		comment.setPublished(false);
		comment.setAnswer(null);
		this.validate(comment);
		return new SuccessMessageDto("Comentário adicionado com sucesso.", this.commentRepository.save(comment));
	}
	
	@Transactional
	public SuccessMessageDto update(Comment comment) throws Exception{
		comment.setPublished(false);
		comment.setAnswer(null);	
		this.validate(comment,PreUpdate.class);
		return new SuccessMessageDto("Comentário atualizado com sucesso.", this.commentRepository.save(comment));
	}
	
	private void validate(Comment comment,Class<?>... classes) throws Exception{
		String message = "";
		Set<ConstraintViolation<Comment>> constraintViolations = validator.validate( comment, classes );
		if(constraintViolations.size() > 0){
			for (ConstraintViolation<Comment> constraintViolation : constraintViolations) {
				message += constraintViolation.getMessage() + "; ";
			}
			throw new Exception(message);
		}
	}
	
	@Transactional
	public SuccessMessageDto delete(Long id){
		this.commentRepository.delete(id);
		//TODO i18n
		return new SuccessMessageDto("Comentário excluído com sucesso.");
	}
	
	@Transactional
	public SuccessMessageDto publish(Long commentId){
		Comment comment = this.commentRepository.findOne(commentId);
		comment.setPublished(true);
		//TODO i18n
		return new SuccessMessageDto("Comentário publicado com sucesso.",this.commentRepository.save(comment));
	}
	
	@Transactional
	public SuccessMessageDto reply(Long commentId, String answer ){
		Comment comment = this.commentRepository.findOne(commentId);
		comment.setAnswer(answer);
		
		//TODO i18n
		return new SuccessMessageDto("Comentário respondido com sucesso.",this.commentRepository.save(comment));
	}
	
	@Transactional
	public SuccessMessageDto replyAndPublish(Long commentId, String answer ){
		Comment comment = this.commentRepository.findOne(commentId);
		comment.setAnswer(answer);
		comment.setPublished(true);
		this.commentRepository.save(comment);
		return new SuccessMessageDto("Comentário respondido e publicado com sucesso.");
	}

	public List<Comment> findByContentAndAnswerAndPostId(SearchCommentDto searchCommentDto){
		return this.commentRepository.findByContentAndAnswerAndPostId(searchCommentDto.getContent(), searchCommentDto.getAnswer(), searchCommentDto.getPostId());
	}

	public Comment findById(Long id) {
		return this.commentRepository.findOne(id);
	}
}