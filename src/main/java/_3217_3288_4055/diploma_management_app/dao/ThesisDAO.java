package _3217_3288_4055.diploma_management_app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import _3217_3288_4055.diploma_management_app.model.Thesis;

public interface ThesisDAO extends JpaRepository<Thesis, Integer> {
	
	public List<Thesis> findByProfessorId(int theProfessorId);
	
	public Thesis findByStudentId(int theStudentId);
	
	public Thesis findById(int theId);
	
}