package br.com.javapress.domain.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.javapress.domain.entity.post.BlogPost;
import br.com.javapress.domain.entity.post.Post;
import br.com.javapress.domain.entity.post.Tag;
import br.com.javapress.domain.repository.post.IPostRepository;
import br.com.javapress.domain.repository.post.ITagRepository;

@Service
public class PostService {

	@Autowired
	private IPostRepository postRepository;
	
	@Autowired
	private ITagRepository tagRepository;
	
	@Transactional
	public Post save(Post post){
		prepareTags(post);
		return this.postRepository.save(post);
	}
	
	public void delete(Long id){
		this.postRepository.delete(id);
	}
	
	public List<BlogPost> findAllBlogPosts(){
		return this.postRepository.findAllBlogPosts(); 
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
			Tag dbTag = this.tagRepository.findByName(); 
			
			if( dbTag != null){
				tags.add(dbTag);
			} else {
				tags.add(this.tagRepository.save(tag));
			}
		}
		post.setTags(tags);
	}
}