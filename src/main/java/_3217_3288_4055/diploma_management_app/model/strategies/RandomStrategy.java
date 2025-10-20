package _3217_3288_4055.diploma_management_app.model.strategies;

import java.util.Random;

import _3217_3288_4055.diploma_management_app.model.Application;

public class RandomStrategy extends TemplateStrategyAlgorithm {
	
	private Random rand = new Random();

	@Override
	public int compareApplications(Application application1, Application application2) {
		
		if (application1 == null) {
			return 2;
		}
		
		int int_random = rand.nextInt(2);
		
		return int_random + 1;
	}
	
}
