package _3217_3288_4055.diploma_management_app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import _3217_3288_4055.diploma_management_app.model.Application;
import _3217_3288_4055.diploma_management_app.model.Professor;
import _3217_3288_4055.diploma_management_app.model.Student;
import _3217_3288_4055.diploma_management_app.model.Subject;
import _3217_3288_4055.diploma_management_app.model.Thesis;
import _3217_3288_4055.diploma_management_app.model.User;
import _3217_3288_4055.diploma_management_app.service.ProfessorService;
import _3217_3288_4055.diploma_management_app.service.StudentService;
import _3217_3288_4055.diploma_management_app.service.SubjectService;
import _3217_3288_4055.diploma_management_app.service.ThesisService;
import _3217_3288_4055.diploma_management_app.service.UserService;

@Controller
@RequestMapping("/professor")
public class ProfessorController {
	
	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ThesisService thesisService;
	
	@Autowired
	public ProfessorController(ProfessorService theProfessorService, StudentService theStudentService, UserService theUserService, SubjectService theSubjectService, ThesisService theThesisService) {
		professorService = theProfessorService;
		studentService = theStudentService;
		userService = theUserService;
		subjectService = theSubjectService;
		thesisService = theThesisService;
	}

    @RequestMapping("/dashboard")
    public String getProfessorHome(){	
        return "professor/dashboard";
    }
    
    @RequestMapping("/showProfile")
    public String getProfessorProfile(Model theModel){
    	
    	Professor theProfessor = getProfessor();
    	
    	if(theProfessor != null) {
    		theModel.addAttribute("professor", theProfessor);
    		return "professor/show-profile";
    	}
    	else {
    		return "professor/no-profile";
    	}
    }
    
    @RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
    	Professor theProfessor = new Professor();
		
		theModel.addAttribute("professor", theProfessor);
		
		return "professor/professor-form";
	}
    
    @RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(Model theModel) {
		    	
    	Professor theProfessor = getProfessor();
		
		theModel.addAttribute("professor", theProfessor);
		
		return "professor/professor-form";			
	}
    
    @RequestMapping("/save")
	public String saveProfessor(@ModelAttribute("student") Professor theProfessor){

    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String username = authentication.getName();
    	Optional<User> user = userService.findByUsername(username);

    	theProfessor.setUser(user.get());
    	
    	professorService.save(theProfessor);
		
		return "redirect:/professor/showProfile";
	}
    
    @RequestMapping("/listProfessorSubjects")
    public String getListProfessorSubjects(Model theModel){
    	
    	Professor theProfessor = getProfessor();
    	
    	if(theProfessor != null) {
    		List<Subject> theSubjects = subjectService.findByHasThesisAndProfessorId(false, theProfessor.getId());
    		theModel.addAttribute("subjects", theSubjects);
    		return "professor/list-professor-subjects";
    	}
    	else {
    		return "professor/no-profile";
    	}
    }
    
    @RequestMapping("/showSubjectFormForAdd")
	public String showSubjectFormForAdd(Model theModel) {
		
    	Professor theProfessor = getProfessor();
    	
    	Subject theSubject = new Subject();
    	theSubject.setProfessor(theProfessor);
		
		theModel.addAttribute("subject", theSubject);
		
		return "professor/subject-form";
	}
    
    @RequestMapping("/saveSubject")
	public String saveSubject(@ModelAttribute("subject") Subject theSubject){
    	
    	subjectService.save(theSubject);
		
		return "redirect:/professor/listProfessorSubjects";
	}
    
    @RequestMapping("/deleteSubject")
	public String deleteSubject(@RequestParam("subjectId") int theId) {
    	
		subjectService.deleteById(theId);
		
		return "redirect:/professor/listProfessorSubjects";
	}
    
    @RequestMapping("/listApplications")
    public String getListApplications(Model theModel){
    	Professor theProfessor = getProfessor();
    	
    	if(theProfessor != null) {   		
    		List<Application> applications = new ArrayList<Application>();
    		
    		for(Subject sub : theProfessor.getSubjects()) {
    			if (sub.getHasThesis() == false) {
    				for(Application app : sub.getApplications()) {
    					if(app.getStudent().getHasThesis() == false) {
    						applications.add(app);
    					}
    				}
    			}
    		}

    		theModel.addAttribute("applications", applications);
    		return "professor/list-professor-applications";
    	}
    	else {
    		return "professor/no-profile";
    	}
    }
    
    @RequestMapping("/showSubjectDetail")
    public String getSubjectDetail(@RequestParam("subjectId") int theSubjectId, Model theModel){

    	Subject theSubject = subjectService.findById(theSubjectId);
    	
    	theModel.addAttribute("subject", theSubject);

    	return "professor/show-subject";
    }
    
    @RequestMapping("/showProfileStudent")
    public String getStudentProfile(@RequestParam("studentId") int theStudentId, Model theModel){
    	Student theStudent = studentService.findById(theStudentId);
    	
    	theModel.addAttribute("student", theStudent);
    	return "professor/show-profile-student";
    }
    
    @RequestMapping("/assignSubjectOptions")
    public String assignSubjectOptions(@RequestParam("subjectId") int theSubjectId, Model theModel){
    	
    	theModel.addAttribute("subjectId", theSubjectId);
    	
    	return "professor/assign-subject-options";
    }
    
    @RequestMapping("/assignSubjectToStudent")
    public String assignSubjectToStudent(@RequestParam("subjectId") int theSubjectId, @RequestParam("option") String theOption, 
    										@RequestParam(required=false, defaultValue = "0", name="averageCoursesGrade") double theAverageCoursesGrade, @RequestParam(required=false, defaultValue = "0", name="numberOfRemainingCourses") int theNumberOfRemainingCourses, Model theModel){
    	
    	Professor theProfessor = getProfessor();
    	
    	boolean assigned = subjectService.assignSubjectToStudent(theSubjectId, theOption, theProfessor, theAverageCoursesGrade, theNumberOfRemainingCourses);
    	
    	if (assigned == true) {
    		return "redirect:/professor/listProfessorSubjects";
    	} else {
    		return "professor/no-student-to-assign-subject";
    	}
    	
    }
    
    @RequestMapping("/listThesis")
    public String getListThesis(Model theModel){
    	
    	Professor theProfessor = getProfessor();
    	
    	if (theProfessor != null) {
    		List<Thesis> theThesis = thesisService.findByProfessorId(theProfessor.getId());
        	
        	theModel.addAttribute("thesis", theThesis);
        	
        	return "professor/list-thesis";
    	} else {
    		return "professor/no-profile";
    	}
    }
    
    @RequestMapping("/showFormForUpdateThesis")
	public String showFormForUpdateThesis(@RequestParam("thesisId") int theThesisId, Model theModel) {
    	
    	Thesis theThesis = thesisService.findById(theThesisId);
		
		theModel.addAttribute("thesis", theThesis);
		
		return "professor/thesis-form";			
	}
    
    @RequestMapping("/showThresholdForm")
	public String showThresholdForm(@RequestParam("subjectId") int theSubjectId, @RequestParam("option") String theOption, Model theModel) {
    			
		theModel.addAttribute("subjectId", theSubjectId);
		
		theModel.addAttribute("option", theOption);
		
		return "professor/show-threshold-form";			
	}
    
    @RequestMapping("/saveThesis")
	public String saveProfessor(@ModelAttribute("thesis") Thesis theThesis){
    	
    	double overall = theThesis.getImplementationGrade() * 0.7 + theThesis.getReportGrade() * 0.15 + theThesis.getPresentationGrade() * 0.15;
    	double roundOff = Math.round(overall * 100.0) / 100.0;
        
        theThesis.setOverallGrade(roundOff);
    	
    	thesisService.save(theThesis);
		
		return "redirect:/professor/listThesis";
	}
    
    private Professor getProfessor() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String username = authentication.getName();
    	Optional<User> user = userService.findByUsername(username);
    	Professor theProfessor = professorService.findByUserId(user.get().getId());
    	return theProfessor;
    }
}
