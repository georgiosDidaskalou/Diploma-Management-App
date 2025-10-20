package _3217_3288_4055.diploma_management_app.model.strategies;

import _3217_3288_4055.diploma_management_app.model.Application;

public class BestAvgGradeStrategy extends TemplateStrategyAlgorithm {

	@Override
	public int compareApplications(Application application1, Application application2) {
		
		if (application1 == null) {
			return 2;
		}
		
		double averageGrade1 = application1.getStudent().getCurrentAverageGrade();
		double averageGrade2 = application2.getStudent().getCurrentAverageGrade();
		
		if (averageGrade1 >= averageGrade2) {
			return 1;
		} else {
			return 2;
		}
	}
}
