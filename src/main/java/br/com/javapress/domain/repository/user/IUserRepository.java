package br.com.javapress.domain.repository.user;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.javapress.domain.entity.user.User;

public interface IUserRepository extends
		PagingAndSortingRepository<User, Long> {

	public User findByEmail(String email);
}
