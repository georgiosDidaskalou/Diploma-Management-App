package _3217_3288_4055.diploma_management_app.service;

import java.util.List;

import _3217_3288_4055.diploma_management_app.model.Application;
import _3217_3288_4055.diploma_management_app.model.Professor;
import _3217_3288_4055.diploma_management_app.model.Subject;

public interface SubjectService {
	
	public Subject findById(int theId);
	
	public void save(Subject theSubject);
	
	public void deleteById(int theId);
	
	void applyToSubject(Application application);
	
	public List<Subject> findByHasThesis(boolean theHasThesis);
	
	public List<Subject> findByHasThesisAndProfessorId(boolean theHasThesis, int theProfessorId);
	
	public boolean assignSubjectToStudent(int theSubjectId, String theOption, Professor theProfessor, double theAverageCoursesGrade, int theNumberOfRemainingCourses);
	
}
