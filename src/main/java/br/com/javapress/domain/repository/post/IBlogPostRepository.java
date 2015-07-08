package br.com.javapress.domain.repository.post;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.javapress.domain.entity.post.BlogPost;

public interface IBlogPostRepository extends
		PagingAndSortingRepository<BlogPost, Long> {

	List<BlogPost> findAll();
}
