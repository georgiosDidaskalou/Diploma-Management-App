package _3217_3288_4055.diploma_management_app.model.strategies;

import _3217_3288_4055.diploma_management_app.model.Application;

public class FewestCoursesStrategy extends TemplateStrategyAlgorithm {

	@Override
	public int compareApplications(Application application1, Application application2) {
		
		if (application1 == null) {
			return 2;
		}
		
		double fewestCourse1 = application1.getStudent().getNumberOfRemainingCourses();
		double fewestCourse2 = application2.getStudent().getNumberOfRemainingCourses();
		
		if (fewestCourse1 <= fewestCourse2) {
			return 1;
		} else {
			return 2;
		}
	}

}
