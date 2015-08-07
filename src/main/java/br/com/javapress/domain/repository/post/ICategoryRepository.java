package br.com.javapress.domain.repository.post;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.com.javapress.domain.entity.post.Category;
import br.com.javapress.domain.entity.post.CategoryType;

public interface ICategoryRepository extends
		PagingAndSortingRepository<Category, Long> {
	
	public List<Category> findAll();
	
	@Query("SELECT category FROM Category category "
			+ "LEFT JOIN category.parent parent "
			+ "WHERE (category.type = :type or :type = null) "
			+ "AND (LOWER(category.name) like '%' || LOWER(CAST(:name AS string) || '%') or :name = null) "
			+ "AND (LOWER(parent.name) like '%' || LOWER(CAST(:parentName AS string) || '%') or :parentName = null)")
	public List<Category> findByTypeAndNameAndParentName(@Param("type") CategoryType type, @Param("name") String name, @Param("parentName") String parentName);

}
