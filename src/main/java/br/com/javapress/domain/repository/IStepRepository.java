package br.com.javapress.domain.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.javapress.domain.entity.recipe.Step;

public interface IStepRepository extends PagingAndSortingRepository<Step, Long> {

}
