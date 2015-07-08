package br.com.javapress.domain.repository.post;

import org.springframework.data.repository.CrudRepository;

import br.com.javapress.domain.entity.post.Tag;

public interface ITagRepository extends
		CrudRepository<Tag, Long> {

}
