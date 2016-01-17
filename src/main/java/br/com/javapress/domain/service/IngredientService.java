package br.com.javapress.domain.service;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.javapress.domain.dto.SuccessMessageDto;
import br.com.javapress.domain.entity.recipe.Ingredient;
import br.com.javapress.domain.repository.recipe.IIngredientRepository;

@Service
public class IngredientService {

	@Autowired
	private IIngredientRepository ingredientRepository;
	
	@Autowired
	private Validator validator;
	
	@Transactional
	public SuccessMessageDto create(Ingredient ingredient) throws Exception{
		return new SuccessMessageDto("Ingrediente criado com sucesso", this.ingredientRepository.save(ingredient));
	}
	
	@Transactional
	public SuccessMessageDto update(Ingredient ingredient) throws Exception{
		return new SuccessMessageDto("Ingrediente atualizado com sucesso.", this.ingredientRepository.save(ingredient));
	}
	
	@Transactional
	public SuccessMessageDto delete(Long id){
		this.ingredientRepository.delete(id);
		//TODO i18n
		return new SuccessMessageDto("Categoria removida com sucesso");
	}
	
	public List<Ingredient> findByDescription(String description){
		
		if(description == null){
			return (List<Ingredient>) this.ingredientRepository.findAll();
		} 
		
		return this.ingredientRepository.findByDescription(description);
	}

	public Ingredient findById(Long id) {
		return this.ingredientRepository.findOne(id);
	}
}