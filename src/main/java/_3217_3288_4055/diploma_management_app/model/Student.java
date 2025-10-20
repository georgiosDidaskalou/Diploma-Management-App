package _3217_3288_4055.diploma_management_app.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="students")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="full_name", unique=false)
	private String fullName;
	
	@Column(name="year_of_studies", unique=false)
	private int yearOfStudies;
	
	@Column(name="current_average_grade", unique=false)
	private double currentAverageGrade;
	
	@Column(name="number_of_remaining_courses", unique=false)
	private int numberOfRemainingCourses;
	
	@Column(name="has_thesis", unique=false)
	private boolean hasThesis;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
	
	@OneToMany(mappedBy="student")
	private List<Application> applications;
	
	public Student() {
	}
	
	public Student(String fullName, int yearOfStudies, double currentAverageGrade, int numberOfRemainingCourses, boolean hasThesis) {
		this.fullName = fullName;
		this.yearOfStudies = yearOfStudies;
		this.currentAverageGrade = currentAverageGrade;
		this.numberOfRemainingCourses = numberOfRemainingCourses;
		this.hasThesis = hasThesis;
	}
	
	public Student(int id, String fullName, int yearOfStudies, double currentAverageGrade, int numberOfRemainingCourses, boolean hasThesis) {
		this.id = id;
		this.fullName = fullName;
		this.yearOfStudies = yearOfStudies;
		this.currentAverageGrade = currentAverageGrade;
		this.numberOfRemainingCourses = numberOfRemainingCourses;
		this.hasThesis = hasThesis;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getYearOfStudies() {
		return yearOfStudies;
	}

	public void setYearOfStudies(int yearOfStudies) {
		this.yearOfStudies = yearOfStudies;
	}

	public double getCurrentAverageGrade() {
		return currentAverageGrade;
	}

	public void setCurrentAverageGrade(double currentAverageGrade) {
		this.currentAverageGrade = currentAverageGrade;
	}

	public int getNumberOfRemainingCourses() {
		return numberOfRemainingCourses;
	}

	public void setNumberOfRemainingCourses(int numberOfRemainingCourses) {
		this.numberOfRemainingCourses = numberOfRemainingCourses;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public List<Application> getApplications() {
		return applications;
	}
	
	public void setApplcations(List<Application> applications) {
		this.applications = applications;
	}
	
	public boolean getHasThesis() {
		return hasThesis;
	}
	
	public void setHasThesis(boolean hasThesis) {
		this.hasThesis = hasThesis;
	}
}
