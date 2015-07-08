package br.com.javapress.domain.repository.user;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.javapress.domain.entity.user.Admin;

public interface IAdminRepository extends
		PagingAndSortingRepository<Admin, Long> {

}
