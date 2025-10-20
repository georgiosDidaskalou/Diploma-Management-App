package _3217_3288_4055.diploma_management_app.model.strategies;

import _3217_3288_4055.diploma_management_app.model.Application;

public class ThresholdStrategy extends TemplateStrategyAlgorithm {
	
	private double Th1;
	
	private int Th2;
	
	public ThresholdStrategy(double Th1, int Th2) {
		this.Th1 = Th1;
		this.Th2 = Th2;
	}
	
	@Override
	public int compareApplications(Application application1, Application application2) {
					
		double currentAverageGrade2 = application2.getStudent().getCurrentAverageGrade();
		double numberOfRemainingCourses2 = application2.getStudent().getNumberOfRemainingCourses();
		
		if (currentAverageGrade2 > Th1 && numberOfRemainingCourses2 < Th2) {
			return 2;
		} else {
			return 1;
		}
	}
	
}
