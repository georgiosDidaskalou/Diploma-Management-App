package _3217_3288_4055.diploma_management_app.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="subjects")
public class Subject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="title", unique=false)
	private String title;
	
	@Column(name="objectives", unique=false)
	private String objectives;
	
	@Column(name="hasThesis", unique=false)
	private boolean hasThesis;
	
	@ManyToOne
    @JoinColumn(name="professor_id", referencedColumnName = "id", nullable=false)
	private Professor professor;
	
	@OneToMany(mappedBy="subject", cascade = CascadeType.ALL)
	private List<Application> applications;
	
	public Subject() {
		this.hasThesis = false;
	}
	
	public Subject(int id, String title, String objectives, Professor professor) {
		this.id = id;
		this.title = title;
		this.objectives = objectives;
		this.professor = professor;
	}
	
	public Subject(String title, String objectives, Professor professor) {
		this.title = title;
		this.objectives = objectives;
		this.professor = professor;
	}
	
	public Subject(String title, String objectives) {
		this.title = title;
		this.objectives = objectives;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getObjectives() {
		return objectives;
	}

	public void setObjectives(String objectives) {
		this.objectives = objectives;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
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
