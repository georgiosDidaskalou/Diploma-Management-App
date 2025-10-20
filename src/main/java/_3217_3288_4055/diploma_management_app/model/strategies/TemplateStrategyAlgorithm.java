package _3217_3288_4055.diploma_management_app.model.strategies;

import java.util.List;

import _3217_3288_4055.diploma_management_app.model.Application;
import _3217_3288_4055.diploma_management_app.model.Student;

public abstract class TemplateStrategyAlgorithm implements BestApplicantStrategy{
	
	public Student findBestApplicant(List<Application> applications) {
		
		Application bestApplication = null;
		
		for(Application app : applications) {		
			int result = compareApplications(bestApplication, app);
			
			if (result == 2) {
				bestApplication = app;
			}
		}
		
		if (bestApplication != null) {
			return bestApplication.getStudent();
		} else {
			return null;
		}
		
	}
	
	public abstract int compareApplications(Application application1, Application application2);
	
	
}
