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
import br.com.javapress.domain.entity.post.Category;
import br.com.javapress.domain.entity.post.CategoryType;
import br.com.javapress.domain.entity.post.Tag;
import br.com.javapress.domain.entity.user.Admin;
import br.com.javapress.domain.repository.post.ICategoryRepository;
import br.com.javapress.domain.repository.post.IPostRepository;
import br.com.javapress.domain.repository.post.ITagRepository;
import br.com.javapress.domain.repository.user.IAdminRepository;
import br.com.javapress.test.config.TestConfiguration;

public class BlogPostRepositoryTest extends TestConfiguration {

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
	
	@Test
	public void shouldCreateAndUpdateBlogPost(){
		//Given
		Category category = new Category();
		category.setName(getRandomString());
		category.setType(CategoryType.RECIPE);
		
		Admin admin = new Admin();
		admin.setEmail("email@email.com");
		admin.setName("Admin name");
		
		Tag tag = new Tag();
		tag.setName(getRandomString());
		tagRepository.save(tag);
		BlogPost blogPost = getObject();
		blogPost.setCategory(categoryRepository.save(category));
		blogPost.setOwner(adminRepository.save(admin));
		blogPost.addTag(tag);
		
		//When
		this.postRepository.save(blogPost);
		
		//Then
		assertNotNull("Id should not be null", blogPost.getId());
		
		//Given
		blogPost.setContent("New content");
		Category otherCategory = new Category();
		otherCategory.setName(getRandomString());
		otherCategory.setType(CategoryType.POST);
		blogPost.setCategory(this.categoryRepository.save(otherCategory));
		
		//When
		this.postRepository.save(blogPost);
		
		//Then
		BlogPost dbBlogPost = (BlogPost) this.postRepository.findOne(blogPost.getId());
		assertEquals("Content should be equal", dbBlogPost.getContent(), blogPost.getContent());
		assertEquals("Category should be equal", blogPost.getCategory().getName(), dbBlogPost.getCategory().getName());
	}
	
	@Test
	public void shouldSaveDbTagAndNewTag(){
		Tag dbTag = this.tagRepository.findById(1L);
		
		Tag newTag = new Tag();
		newTag.setName(getRandomString());
		this.tagRepository.save(newTag);
		
		BlogPost post = getObject();
		post.addTag(dbTag);
		post.addTag(newTag);
		
		this.postRepository.save(post);
		
		assertEquals("Number of post tags", 2, post.getTags().size());
	}

	@Test
	public void shouldDelete(){
		//Given
		BlogPost blogPost = this.postRepository.save(getObject());
		assertNotNull(blogPost.getId());
		
		//When
		this.postRepository.delete(blogPost);
		
		//Then
		BlogPost dbBlogPost = (BlogPost) this.postRepository.findOne(blogPost.getId());
		assertNull("BlogPost shouldn't exist", dbBlogPost);
	}
	
	@Test
	public void shouldFindAll(){
		//Given
		BlogPost blogPost = getObject();
		this.postRepository.save(blogPost);
		
		BlogPost blogPost2 = getObject();
		this.postRepository.save(blogPost2);
		
		//When
		List<BlogPost> blogPosts = this.postRepository.findBlogPostsByTitleAndCategoryId(null, null);
		assertTrue("Size should be bigger than one", blogPosts.size()>1);
	}
	
	private BlogPost getObject(){
		BlogPost post = new BlogPost();
		post.setTitle(getRandomString());
		post.setContent(getRandomString());
		post.setPublished(false);
		return post;
	}
}
