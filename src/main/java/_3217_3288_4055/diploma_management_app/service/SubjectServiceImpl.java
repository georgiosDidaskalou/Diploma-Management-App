package _3217_3288_4055.diploma_management_app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _3217_3288_4055.diploma_management_app.dao.ApplicationDAO;
import _3217_3288_4055.diploma_management_app.dao.StudentDAO;
import _3217_3288_4055.diploma_management_app.dao.SubjectDAO;
import _3217_3288_4055.diploma_management_app.dao.ThesisDAO;
import _3217_3288_4055.diploma_management_app.model.Application;
import _3217_3288_4055.diploma_management_app.model.Professor;
import _3217_3288_4055.diploma_management_app.model.Student;
import _3217_3288_4055.diploma_management_app.model.Subject;
import _3217_3288_4055.diploma_management_app.model.Thesis;
import _3217_3288_4055.diploma_management_app.model.strategies.BestApplicantStrategy;
import _3217_3288_4055.diploma_management_app.model.strategies.BestApplicantStrategyFactory;

@Service
public class SubjectServiceImpl implements SubjectService {
	
	@Autowired
	private SubjectDAO subjectRepository;
	
	@Autowired
	private ApplicationDAO applicationRepository;
	
	@Autowired
	private ThesisDAO thesisRepository;
	
	@Autowired
	private StudentDAO studentRepository;
	
	@Autowired
	public SubjectServiceImpl(SubjectDAO theSubjectRepository, ApplicationDAO theApplicationRepository, ThesisDAO theThesisRepository, StudentDAO theStudentRepository) {
		subjectRepository = theSubjectRepository;
		applicationRepository = theApplicationRepository;
		thesisRepository = theThesisRepository;
		theStudentRepository = studentRepository;
	}
	
	@Override
	@Transactional
	public Subject findById(int theId) {
		Subject result = subjectRepository.findById(theId);
		return result;
	}
	
	@Override
	@Transactional
	public void save(Subject theSubject) {
		subjectRepository.save(theSubject);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		subjectRepository.deleteById(theId);
	}
	
	@Override
	@Transactional
	public void applyToSubject(Application application) {
		applicationRepository.save(application);
	}
	
	@Override
	@Transactional
	public List<Subject> findByHasThesis(boolean theHasThesis) {
		List<Subject> theSubjects = subjectRepository.findByHasThesis(theHasThesis);
		return theSubjects;
	}
	
	@Override
	@Transactional
	public List<Subject> findByHasThesisAndProfessorId(boolean theHasThesis, int theProfessorId) {
		List<Subject> theSubjects = subjectRepository.findByHasThesisAndProfessorId(theHasThesis, theProfessorId);
		return theSubjects;
	}
	
	@Override
	@Transactional
	public boolean assignSubjectToStudent(int theSubjectId, String theOption, Professor theProfessor, double theAverageCoursesGrade, int theNumberOfRemainingCourses) {
		
		BestApplicantStrategyFactory theFactory = new BestApplicantStrategyFactory();
		
		BestApplicantStrategy theBestApplicantStrategy = theFactory.getApplicantStrategy(theOption, theAverageCoursesGrade, theNumberOfRemainingCourses);
		
		Subject theSubject = subjectRepository.findById(theSubjectId);
		List<Application> theApplications = theSubject.getApplications();
		
		List<Application> theApplicationsForAssign = new ArrayList<Application>();
		
		for(Application app : theApplications) {
			if (app.getStudent().getHasThesis() == false) {
				theApplicationsForAssign.add(app);
			}
		}
		
		Student theStudent = theBestApplicantStrategy.findBestApplicant(theApplicationsForAssign);
		
		if (theStudent == null) {
			return false;
		}
		
		Thesis theThesis = new Thesis(theSubject, theStudent, theProfessor);
		
		thesisRepository.save(theThesis);
		
		theSubject.setHasThesis(true);
		subjectRepository.save(theSubject);
		
		theStudent.setHasThesis(true);
		studentRepository.save(theStudent);
		
		return true;
	}
}
