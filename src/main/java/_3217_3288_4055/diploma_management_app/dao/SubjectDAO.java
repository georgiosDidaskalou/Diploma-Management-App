package _3217_3288_4055.diploma_management_app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import _3217_3288_4055.diploma_management_app.model.Subject;

@Repository
public interface SubjectDAO extends JpaRepository<Subject, Integer> {
	
	public Subject findById(int theId);
	
	public List<Subject> findByHasThesis(boolean theHasThesis);
	
	public List<Subject> findByHasThesisAndProfessorId(boolean theHasThesis, int theProfessorId);
	
}
