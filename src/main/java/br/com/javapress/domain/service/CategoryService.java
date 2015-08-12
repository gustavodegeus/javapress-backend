package br.com.javapress.domain.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.javapress.application.validation.groups.PreUpdate;
import br.com.javapress.domain.dto.SearchCategoryDto;
import br.com.javapress.domain.dto.SuccessMessageDto;
import br.com.javapress.domain.entity.post.Category;
import br.com.javapress.domain.repository.post.ICategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private ICategoryRepository categoryRepository;
	
	@Autowired
	private Validator validator;
	
	@Transactional
	public Category create(Category category) throws Exception{
		this.loadCategoryData(category);
		this.validate(category);
		return this.categoryRepository.save(category);
	}
	
	@Transactional
	public Category update(Category category) throws Exception{
		this.loadCategoryData(category);
		this.validate(category, PreUpdate.class);
		return this.categoryRepository.save(category);
	}
	
	private void loadCategoryData(Category category){
		if(category.getParent() != null){
			category.setParent(this.categoryRepository.findOne(category.getParent().getId()));
		}
	}
	
	private void validate(Category category,Class<?>... classes) throws Exception{
		String message = "";
		Set<ConstraintViolation<Category>> constraintViolations = validator.validate( category, classes );
		if(constraintViolations.size() > 0){
			for (ConstraintViolation<Category> constraintViolation : constraintViolations) {
				message += constraintViolation.getMessage() + "; ";
			}
			throw new Exception(message);
		}
	}

	@Transactional
	public SuccessMessageDto delete(Long id){
		this.categoryRepository.delete(id);
		//TODO i18n
		return new SuccessMessageDto("Categoria removida com sucesso");
	}
	
	public List<Category> findByTypeAndNameAndParentName(SearchCategoryDto categorySearchDto){
		return this.categoryRepository.findByTypeAndNameAndParentName(categorySearchDto.getType(), categorySearchDto.getName(), categorySearchDto.getParentName());
	}

	public Category findById(Long id) {
		return this.categoryRepository.findOne(id);
	}
}