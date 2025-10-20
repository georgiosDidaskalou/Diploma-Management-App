package _3217_3288_4055.diploma_management_app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import _3217_3288_4055.diploma_management_app.model.Professor;

@Repository
public interface ProfessorDAO extends JpaRepository<Professor, Integer> {
	
	public Professor findById(int theId);
	
	public Professor findByUserId(int theId);
	
}
