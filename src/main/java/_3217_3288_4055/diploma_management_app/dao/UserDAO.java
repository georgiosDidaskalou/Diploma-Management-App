package _3217_3288_4055.diploma_management_app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import _3217_3288_4055.diploma_management_app.model.User;

public interface UserDAO extends JpaRepository<User, Integer> {
	
	Optional<User> findByUsername(String username);

}
