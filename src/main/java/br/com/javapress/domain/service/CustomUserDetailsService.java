package br.com.javapress.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.javapress.domain.dto.UserDto;
import br.com.javapress.domain.entity.user.User;
import br.com.javapress.domain.repository.user.IUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private IUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {
		final User user = this.userRepository.findByEmail(email);
		
		if ( user == null )
		{
			throw new UsernameNotFoundException("Email not found.");
		}
		
		return user;
	}
	
	public User login(UserDto userDto) throws Exception{
		User dbUser = (User) this.loadUserByUsername(userDto.getEmail());
		
		if(dbUser.getPassword().equals(userDto.getPassword())){
			return dbUser;
		} else {
			throw new Exception("Invalid password");
		}
	}

}
