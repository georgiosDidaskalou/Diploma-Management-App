package _3217_3288_4055.diploma_management_app.model.strategies;

import java.util.List;

import _3217_3288_4055.diploma_management_app.model.Application;
import _3217_3288_4055.diploma_management_app.model.Student;

public interface BestApplicantStrategy {
	
	public Student findBestApplicant(List<Application> applications);
	
}