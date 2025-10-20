package _3217_3288_4055.diploma_management_app.service;

import java.util.List;

import _3217_3288_4055.diploma_management_app.model.Application;
import _3217_3288_4055.diploma_management_app.model.Student;

public interface StudentService {
	
	public Student findById(int theId);
	
	public Student findByUserId(int theId);
	
	public void save(Student theStudent);
	
	public List<Application> findApplicationByStudentId(int theStudentId);
	
}
