package br.com.javapress.domain.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.javapress.domain.dto.CategorySearchDto;
import br.com.javapress.domain.entity.post.Category;
import br.com.javapress.domain.repository.post.ICategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private ICategoryRepository categoryRepository;
	
	public Category save(@Valid Category category){
		return this.categoryRepository.save(category);
	}
	
	public void delete(Long id){
		this.categoryRepository.delete(id);
	}
	
	public List<Category> findAll(CategorySearchDto categorySearchDto){
		return this.categoryRepository.findByTypeAndNameAndParentName(categorySearchDto.getType(), categorySearchDto.getName(), categorySearchDto.getParentName());
	}

	public Category findById(Long id) {
		return this.categoryRepository.findOne(id);
	}
}
