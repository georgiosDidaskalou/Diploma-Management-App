package _3217_3288_4055.diploma_management_app.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="thesis")
public class Thesis {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;
	
	@ManyToOne
    @JoinColumn(name="professor_id", referencedColumnName = "id", nullable=false)
	private Professor professor;
	
	@Column(name="implementation_grade", unique=false)
	private Double implementationGrade;
	
	@Column(name="report_grade", unique=false)
	private Double reportGrade;
	
	@Column(name="presentation_grade", unique=false)
	private Double presentationGrade;
	
	@Column(name="overall_grade", unique=false)
	private Double overallGrade;
	
	public Thesis() {
	}
	
	public Thesis(Subject subject, Student student, Professor professor) {
		this.subject = subject;
		this.student = student;
		this.professor = professor;
	}
	
	public Thesis(int id, Subject subject, Student student, Professor professor) {
		this.id = id;
		this.subject = subject;
		this.student = student;
		this.professor = professor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	public Double getImplementationGrade() {
		return implementationGrade;
	}

	public void setImplementationGrade(Double implementationGrade) {
		this.implementationGrade = implementationGrade;
	}
	
	public Double getReportGrade() {
		return reportGrade;
	}

	public void setReportGrade(Double reportGrade) {
		this.reportGrade = reportGrade;
	}
	
	public Double getPresentationGrade() {
		return presentationGrade;
	}

	public void setPresentationGrade(Double presentationGrade) {
		this.presentationGrade = presentationGrade;
	}
	
	public Double getOverallGrade() {
		return overallGrade;
	}

	public void setOverallGrade(Double overallGrade) {
		this.overallGrade = overallGrade;
	}
}
