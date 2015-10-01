package br.com.javapress.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.javapress.domain.dto.SearchCommentDto;
import br.com.javapress.domain.dto.SuccessMessageDto;
import br.com.javapress.domain.entity.post.Comment;
import br.com.javapress.domain.service.CategoryService;
import br.com.javapress.domain.service.CommentService;

@RestController
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private CategoryService categoryService;
	
	
	@RequestMapping(value = "/comments", method = RequestMethod.GET)
	public List<Comment> find(@RequestParam(value="content",required=false) String content, 
							  @RequestParam(value="answer",required=false) String answer,
							  @RequestParam(value="postId",required=false) Long postId) {
		SearchCommentDto searchCommentDto = new SearchCommentDto(content,answer,postId);
		return this.commentService.findByContentAndAnswerAndPostId(searchCommentDto);
	}
	
	@RequestMapping(value="/comment/{id}", method = RequestMethod.GET)
	public Comment findById(@PathVariable Long id){
		return this.commentService.findById(id);
	}
	
	@RequestMapping(value="/comment", method = RequestMethod.POST)
	public SuccessMessageDto create(@RequestBody Comment comment) throws Exception{
		return this.commentService.create(comment);
	}
	
	@RequestMapping(value="/comment", method = RequestMethod.PUT)
	public SuccessMessageDto update(@RequestBody Comment comment) throws Exception{
		return this.commentService.update(comment);
	}
	
	@RequestMapping(value="/comment/{id}/publish", method = RequestMethod.PUT)
	public SuccessMessageDto publish(@PathVariable Long id) throws Exception{
		return this.commentService.publish(id);
	}
	
	@RequestMapping(value="/comment/{id}/reply", method = RequestMethod.PUT)
	public SuccessMessageDto reply(@PathVariable Long id, @RequestBody String answer) throws Exception{
		return this.commentService.reply(id, answer);
	}
	
	@RequestMapping(value="/comment/{id}/replyAndPublish", method = RequestMethod.PUT)
	public SuccessMessageDto replyAndPublish(@PathVariable Long id, @RequestBody String answer) throws Exception{
		return this.commentService.replyAndPublish(id, answer);
	}
	
	@RequestMapping(value="/comment/{id}", method = RequestMethod.DELETE)
	public SuccessMessageDto delete(@PathVariable Long id){
		return this.commentService.delete(id);
	}
}