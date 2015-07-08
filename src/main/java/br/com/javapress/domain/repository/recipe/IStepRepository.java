package br.com.javapress.domain.repository.recipe;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.javapress.domain.entity.recipe.Step;

public interface IStepRepository extends PagingAndSortingRepository<Step, Long> {

	public List<Step> findAll();
}
