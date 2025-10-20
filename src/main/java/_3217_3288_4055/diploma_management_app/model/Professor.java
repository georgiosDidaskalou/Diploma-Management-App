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
@Table(name="professors")
public class Professor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="full_name", unique=false)
	private String fullName;
	
	@Column(name="specialty", unique=false)
	private String specialty;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;
	
	@OneToMany(mappedBy="professor")
	private List<Subject> subjects;
	
	@OneToMany(mappedBy="professor")
	private List<Thesis> thesis;
	
	public Professor() {
	}
	
	public Professor(String fullName, String specialty) {
		this.fullName = fullName;
		this.specialty = specialty;
	}
	
	public Professor(int id, String fullName, String specialty) {
		this.id = id;
		this.fullName = fullName;
		this.specialty = specialty;
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

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public List<Subject> getSubjects(){
		return subjects;
	}
	
	public void setSubjects(List<Subject> subjects){
		this.subjects = subjects;
	}
	
	public List<Thesis> getThesis() {
		return thesis;
	}
	
	public void setThesis(List<Thesis> thesis) {
		this.thesis = thesis;
	}
}
