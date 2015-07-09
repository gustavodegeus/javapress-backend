package br.com.javapress.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.javapress.domain.entity.post.Category;
import br.com.javapress.domain.repository.post.ICategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private ICategoryRepository categoryRepository;
	
	public Category save(Category category) throws Exception{
		if(category.getParent() != null) {
			if (!category.getParent().getType().equals(category.getType())){
				throw new Exception("Invalid");
			}
		}
		return this.categoryRepository.save(category);
	}
	
	public void delete(Long id){
		this.categoryRepository.delete(id);
	}
	
	public List<Category> findAll(){
		return this.categoryRepository.findAll();
	}

	public Category findById(Long id) {
		return this.categoryRepository.findOne(id);
	}
}
