package _3217_3288_4055.diploma_management_app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import _3217_3288_4055.diploma_management_app.dao.UserDAO;
import _3217_3288_4055.diploma_management_app.model.User;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public Optional<User> findByUsername(String username) {
		return userDAO.findByUsername(username);
	}
	
	@Override
	public void saveUser(User user) {
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userDAO.save(user);	
    }

	@Override
	public boolean isUserPresent(User user) {
		Optional<User> storedUser = userDAO.findByUsername(user.getUsername());
		return storedUser.isPresent();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 return userDAO.findByUsername(username).orElseThrow(
	                ()-> new UsernameNotFoundException(
	                        String.format("USER_NOT_FOUND", username)
	                ));
	}
}

