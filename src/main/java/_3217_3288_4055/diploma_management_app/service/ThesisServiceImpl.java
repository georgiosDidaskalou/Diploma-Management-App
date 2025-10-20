package _3217_3288_4055.diploma_management_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _3217_3288_4055.diploma_management_app.dao.ThesisDAO;
import _3217_3288_4055.diploma_management_app.model.Thesis;

@Service
public class ThesisServiceImpl implements ThesisService{

	@Autowired
	private ThesisDAO thesisRepository;
	
	@Autowired
	public ThesisServiceImpl(ThesisDAO theThesisRepository) {
		thesisRepository = theThesisRepository;
	}
	
	@Override
	@Transactional
	public List<Thesis> findByProfessorId(int theProfessorId) {
		return thesisRepository.findByProfessorId(theProfessorId);
	}
	
	@Override
	@Transactional
	public Thesis findByStudentId(int theStudentId) {
		return thesisRepository.findByStudentId(theStudentId);
	}
	
	@Override
	@Transactional
	public Thesis findById(int theId) {
		return thesisRepository.findById(theId);
	}
	
	@Override
	@Transactional
	public void save(Thesis theThesis) {
		thesisRepository.save(theThesis);
	}
}
