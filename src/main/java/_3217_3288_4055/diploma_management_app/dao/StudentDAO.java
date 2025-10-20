package _3217_3288_4055.diploma_management_app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import _3217_3288_4055.diploma_management_app.model.Student;

@Repository
public interface StudentDAO extends JpaRepository<Student, Integer> {
	
	public Student findById(int theId);
	
	public Student findByUserId(int theId);
	
}