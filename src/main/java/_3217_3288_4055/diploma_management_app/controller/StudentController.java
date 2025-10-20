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
import _3217_3288_4055.diploma_management_app.model.Student;
import _3217_3288_4055.diploma_management_app.model.Subject;
import _3217_3288_4055.diploma_management_app.model.Thesis;
import _3217_3288_4055.diploma_management_app.model.User;
import _3217_3288_4055.diploma_management_app.service.StudentService;
import _3217_3288_4055.diploma_management_app.service.SubjectService;
import _3217_3288_4055.diploma_management_app.service.ThesisService;
import _3217_3288_4055.diploma_management_app.service.UserService;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ThesisService thesisService;
	
	@Autowired
	public StudentController(StudentService theStudentService, UserService theUserService, SubjectService theSubjectService, ThesisService theThesisService) {
		studentService = theStudentService;
		userService = theUserService;
		subjectService = theSubjectService;
		thesisService = theThesisService;
	}

    @RequestMapping("/dashboard")
    public String getStudentHome(){
        return "student/dashboard";
    }
    
    @RequestMapping("/showProfile")
    public String getStudentProfile(Model theModel){
    	Student theStudent = getStudent();
    	
    	if(theStudent != null) {
    		theModel.addAttribute("student", theStudent);
    		return "student/show-profile";
    	}
    	else {
    		return "student/no-profile";
    	}
    }
    
    @RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

    	Student theStudent = new Student();
    	theStudent.setHasThesis(false);
		
		theModel.addAttribute("student", theStudent);
		
		return "student/student-form";
	}
    
    @RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(Model theModel) {
    	
    	Student theStudent = getStudent();
		
		theModel.addAttribute("student", theStudent);
		
		return "student/student-form";			
	}
    
    @RequestMapping("/save")
	public String saveStudent(@ModelAttribute("student") Student theStudent){
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String username = authentication.getName();
    	Optional<User> user = userService.findByUsername(username);

    	theStudent.setUser(user.get());
    	
    	
		studentService.save(theStudent);
		
		return "redirect:/student/showProfile";
	}
    
    @RequestMapping("/showAvailableDiplomaThesis")
    public String getListProfessorSubjects(Model theModel){
    	
    	Student theStudent = getStudent();
    	
    	if(theStudent == null) {
    		return "student/no-profile";
    	}
    	
    	Thesis theThesis = thesisService.findByStudentId(theStudent.getId());
    	
    	if (theThesis != null) {
    		return "student/thesis-exists";
    	}
    	    	
    	List<Subject> theSubjects = subjectService.findByHasThesis(false);
    	
    	List<Application> applications = studentService.findApplicationByStudentId(theStudent.getId());
    	List<Integer> subjectsIds = new ArrayList<Integer>();
    	
    	for(Application application : applications) {
    		subjectsIds.add(application.getSubject().getId());
    	}
    	
		List<Subject> finalSubject = new ArrayList<Subject>();
		
		for(Subject sub : theSubjects) {
			if(subjectsIds.contains(sub.getId())) {
				continue;
			}
			
			finalSubject.add(sub);
		}
    	
    	theModel.addAttribute("subjects", finalSubject);
    	
    	return "student/list-subjects";
    }
    
    @RequestMapping("/showSubjectDetail")
    public String getSubjectDetail(@RequestParam("subjectId") int theSubjectId, Model theModel){

    	Subject theSubject = subjectService.findById(theSubjectId);
    	
    	theModel.addAttribute("subject", theSubject);

    	return "student/show-subject";
    }
    
    @RequestMapping("/applyToSubject")
    public String applyToSubject(@RequestParam("subjectId") int theSubjectId, Model theModel){
    	
    	Student theStudent = getStudent();
    	
    	Subject theSubject = subjectService.findById(theSubjectId);
    	
    	Application application = new Application(theSubject, theStudent);
    	
    	subjectService.applyToSubject(application);

    	return "redirect:/student/showAvailableDiplomaThesis";
    }
    
    private Student getStudent() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String username = authentication.getName();
    	Optional<User> user = userService.findByUsername(username);
    	Student theStudent = studentService.findByUserId(user.get().getId());
    	return theStudent;
    }
}
