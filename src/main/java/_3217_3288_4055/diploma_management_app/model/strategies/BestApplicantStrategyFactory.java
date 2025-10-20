package _3217_3288_4055.diploma_management_app.model.strategies;

public class BestApplicantStrategyFactory {
	
	public BestApplicantStrategy getApplicantStrategy(String strategy, double theAverageCoursesGrade, int theNumberOfRemainingCourses){
	      if(strategy == null){
	         return null;
	      }		
	      if(strategy.equalsIgnoreCase("1")){
	         return new RandomStrategy();
	      } else if(strategy.equalsIgnoreCase("2")){
	         return new BestAvgGradeStrategy();
	      } else if(strategy.equalsIgnoreCase("3")){
	         return new FewestCoursesStrategy();
	      } else if(strategy.equalsIgnoreCase("4")){
	    	  return new ThresholdStrategy(theAverageCoursesGrade, theNumberOfRemainingCourses);
	      }
	      
	      return null;
	   }
}
