package _3217_3288_4055.diploma_management_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _3217_3288_4055.diploma_management_app.dao.ApplicationDAO;
import _3217_3288_4055.diploma_management_app.dao.StudentDAO;
import _3217_3288_4055.diploma_management_app.model.Application;
import _3217_3288_4055.diploma_management_app.model.Student;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentDAO studentRepository;
	
	@Autowired
	private ApplicationDAO applicationRepository;
	
	@Autowired
	public StudentServiceImpl(StudentDAO theStudentRepository, ApplicationDAO theApplicationRepository) {
		studentRepository = theStudentRepository;
		applicationRepository = theApplicationRepository;
	}
	
	@Override
	@Transactional
	public Student findById(int theId) {
		Student result = studentRepository.findById(theId);
		return result;
	}
	
	@Override
	@Transactional
	public Student findByUserId(int theId) {
		Student result = studentRepository.findByUserId(theId);
		return result;
	}
	
	
	@Override
	@Transactional
	public void save(Student theStudent) {
		studentRepository.save(theStudent);
	}
	
	@Override
	@Transactional
	public List<Application> findApplicationByStudentId(int theStudentId) {
		List<Application> applications = applicationRepository.findByStudentId(theStudentId);
		return applications;
	}
}
