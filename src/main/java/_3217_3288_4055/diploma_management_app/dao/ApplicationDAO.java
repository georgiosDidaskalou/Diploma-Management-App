package _3217_3288_4055.diploma_management_app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import _3217_3288_4055.diploma_management_app.model.Application;

public interface ApplicationDAO extends JpaRepository<Application, Integer> {
	
	public Application findById(int theId);
	
	public List<Application> findByStudentId(int theStudentId);
	
}
