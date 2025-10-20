package _3217_3288_4055.diploma_management_app.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import _3217_3288_4055.diploma_management_app.model.User;

@Service
public interface UserService {
	
	public Optional<User> findByUsername(String username);
	
	public void saveUser(User user);
	
    public boolean isUserPresent(User user);
    
}
