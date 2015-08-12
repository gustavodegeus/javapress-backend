package br.com.javapress.domain.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.javapress.application.validation.groups.PreUpdate;
import br.com.javapress.domain.dto.SearchBlogPostDto;
import br.com.javapress.domain.dto.SuccessMessageDto;
import br.com.javapress.domain.entity.post.BlogPost;
import br.com.javapress.domain.entity.post.Post;
import br.com.javapress.domain.entity.post.Tag;
import br.com.javapress.domain.repository.post.ICategoryRepository;
import br.com.javapress.domain.repository.post.IPostRepository;
import br.com.javapress.domain.repository.post.ITagRepository;
import br.com.javapress.domain.repository.user.IAdminRepository;

@Service
public class PostService {

	@Autowired
	private IPostRepository postRepository;
	
	@Autowired
	private ICategoryRepository categoryRepository;
	
	@Autowired
	private IAdminRepository adminRepository;
	
	@Autowired
	private ITagRepository tagRepository;
	
	@Autowired
	private Validator validator;
	
	public Post create(Post post) throws Exception{
		this.loadPostData(post);
		this.validate(post);
		return this.save(post);
	}
	
	public Post update(Post post) throws Exception{
		this.loadPostData(post);
		this.validate(post,PreUpdate.class);
		return this.save(post);
	}
	
	private void loadPostData(Post post){
		if(post.getCategory() != null){
			post.setCategory(this.categoryRepository.findOne(post.getCategory().getId()));
		}
	}
	
	private void validate(Post post,Class<?>... classes) throws Exception{
		String message = "";
		Set<ConstraintViolation<Post>> constraintViolations = validator.validate( post, classes );
		if(constraintViolations.size() > 0){
			for (ConstraintViolation<Post> constraintViolation : constraintViolations) {
				message += constraintViolation.getMessage() + "; ";
			}
			throw new Exception(message);
		}
	}
	
	@Transactional
	private Post save(Post post){
		prepareTags(post);
		return this.postRepository.save(post);
	}
	
	@Transactional
	public SuccessMessageDto delete(Long id){
		this.postRepository.delete(id);
		//TODO i18n
		return new SuccessMessageDto("Post excluído com sucesso.");
	}

	public List<BlogPost> findBlogPostsByTitleAndCategoryId(SearchBlogPostDto searchBlogPostDto){
		return this.postRepository.findBlogPostsByTitleAndCategoryId(searchBlogPostDto.getTitle(), searchBlogPostDto.getCategoryId()); 
	}
	
	public List<Post> findAll(){
		return this.postRepository.findAll();
	}

	public Post findById(Long id) {
		return this.postRepository.findOne(id);
	}
	
	private void prepareTags(Post post){
		Set<Tag> tags = new HashSet<Tag>();
		for (Tag tag : post.getTags()) {
			Tag dbTag = this.tagRepository.findByName(tag.getName()); 
			
			if( dbTag != null){
				tags.add(dbTag);
			} else {
				tags.add(this.tagRepository.save(tag));
			}
		}
		post.setTags(tags);
	}
}