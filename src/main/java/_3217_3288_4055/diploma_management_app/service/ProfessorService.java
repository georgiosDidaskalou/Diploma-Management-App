package _3217_3288_4055.diploma_management_app.service;

import _3217_3288_4055.diploma_management_app.model.Professor;

public interface ProfessorService {
	
	public Professor findById(int theId);
	
	public Professor findByUserId(int theId);
	
	public void save(Professor theStudent);
	
}