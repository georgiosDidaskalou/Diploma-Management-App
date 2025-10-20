package _3217_3288_4055.diploma_management_app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="applications")
public class Application {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne
    @JoinColumn(name="subject_id", referencedColumnName = "id", nullable=false)
	private Subject subject;
	
	@ManyToOne
    @JoinColumn(name="student_id", referencedColumnName = "id", nullable=false)
	private Student student;
	
	public Application() {
	}
	
	public Application(Subject subject, Student student) {
		this.subject = subject;
		this.student= student;
	}
	
	public Application(int id, Subject subject, Student student) {
		this.id = id;
		this.subject = subject;
		this.student= student;
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
}
