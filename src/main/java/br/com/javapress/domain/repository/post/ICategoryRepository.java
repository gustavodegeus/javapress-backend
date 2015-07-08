package br.com.javapress.domain.repository.post;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.javapress.domain.entity.post.Category;

public interface ICategoryRepository extends
		CrudRepository<Category, Long> {

	List<Category> findAll();
}
