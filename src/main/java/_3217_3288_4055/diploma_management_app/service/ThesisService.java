package _3217_3288_4055.diploma_management_app.service;

import java.util.List;

import _3217_3288_4055.diploma_management_app.model.Thesis;

public interface ThesisService {
	
	public List<Thesis> findByProfessorId(int theProfessorId);
	
	public Thesis findByStudentId(int theStudentId);
	
	public Thesis findById(int theId);
	
	public void save(Thesis theThesis);
	
}
