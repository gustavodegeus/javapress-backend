package br.com.javapress.test.repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.javapress.domain.entity.post.BlogPost;
import br.com.javapress.domain.entity.post.PostCategory;
import br.com.javapress.domain.entity.post.Tag;
import br.com.javapress.domain.entity.user.Admin;
import br.com.javapress.domain.repository.post.IBlogPostRepository;
import br.com.javapress.domain.repository.post.ICategoryRepository;
import br.com.javapress.domain.repository.post.ITagRepository;
import br.com.javapress.domain.repository.user.IAdminRepository;
import br.com.javapress.test.TestConfiguration;

public class BlogPostRepositoryTest extends TestConfiguration {

	@Autowired
	private IBlogPostRepository blogPostRepository;
	
	@Autowired
	private ICategoryRepository categoryRepository;
	
	@Autowired
	private IAdminRepository adminRepository;

	@Autowired
	private ITagRepository tagRepository;
	
	@Test
	public void testSave(){
		//Given
		PostCategory category = new PostCategory();
		category.setName("New Category");
		
		Admin admin = new Admin();
		admin.setEmail("email@email.com");
		admin.setName("Admin name");
		
		Tag tag = new Tag();
		tag.setName("Tag1");
		Set<Tag> tags = new HashSet<Tag>();
		tags.add(tag);
		
		BlogPost blogPost = new BlogPost();
		blogPost.setContent("This is the content of a post");
		blogPost.setCategory(categoryRepository.save(category));
		blogPost.setOwner(adminRepository.save(admin));
		blogPost.setPublished(false);
		blogPost.setTags(tags);
		
		//When
		this.blogPostRepository.save(blogPost);
		
		//Then
		Assert.assertNotNull(blogPost.getId());
		
		//Given
		blogPost.setContent("New content");
		PostCategory otherCategory = new PostCategory();
		otherCategory.setName("Other category");
		blogPost.setCategory(this.categoryRepository.save(otherCategory));
		
		//When
		this.blogPostRepository.save(blogPost);
		
		//Then
		BlogPost dbBlogPost = this.blogPostRepository.findOne(blogPost.getId());
		Assert.assertEquals(dbBlogPost.getContent(), blogPost.getContent());
		Assert.assertEquals(blogPost.getCategory().getName(), dbBlogPost.getCategory().getName());
	}
	
	@Test
	public void testDelete(){
		//Given
		BlogPost blogPost = new BlogPost();
		this.blogPostRepository.save(blogPost);
		Assert.assertNotNull(blogPost.getId());
		
		//When
		this.blogPostRepository.delete(blogPost);
		
		//Then
		BlogPost dbBlogPost = this.blogPostRepository.findOne(blogPost.getId());
		Assert.assertNull(dbBlogPost);
	}
	
	@Test
	public void testFindAll(){
		//Given
		BlogPost blogPost = new BlogPost();
		this.blogPostRepository.save(blogPost);
		
		BlogPost blogPost2 = new BlogPost();
		this.blogPostRepository.save(blogPost2);
		
		//When
		List<BlogPost> blogPosts = this.blogPostRepository.findAll();
		Assert.assertTrue(blogPosts.size()>1);
	}
}
