package br.com.javapress.domain.repository.user;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.javapress.domain.entity.user.Client;

public interface IClientRepository extends
		PagingAndSortingRepository<Client, Long> {

}
