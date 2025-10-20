package _3217_3288_4055.diploma_management_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _3217_3288_4055.diploma_management_app.dao.ProfessorDAO;
import _3217_3288_4055.diploma_management_app.model.Professor;

@Service
public class ProfessorServiceImpl implements ProfessorService {
	
	@Autowired
	private ProfessorDAO professorRepository;
	
	@Autowired
	public ProfessorServiceImpl(ProfessorDAO theProfessorRepository) {
		professorRepository = theProfessorRepository;
	}
	
	@Override
	@Transactional
	public Professor findById(int theId) {
		Professor result = professorRepository.findById(theId);
		return result;
	}
	
	@Override
	@Transactional
	public Professor findByUserId(int theId) {
		Professor result = professorRepository.findByUserId(theId);
		return result;
	}
	
	@Override
	@Transactional
	public void save(Professor theProfessor) {
		professorRepository.save(theProfessor);
	}
	
}
