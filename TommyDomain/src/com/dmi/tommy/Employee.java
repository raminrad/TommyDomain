package com.dmi.tommy;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@XmlRootElement(name="Employee")
@Entity
public class Employee {

	private Integer employeeId;
	private String firstname;
	private String lastname;
	private String title;
	
	@XmlTransient
	private Employee boss;
	private List<Employee> staff;
		
	public Employee() {}
	public Employee(String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
	}

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@ManyToOne
	@JoinColumn(name="id_boss", referencedColumnName="id")
	@XmlTransient
	public Employee getBoss() {
		return boss;
	}
	public void setBoss(Employee boss) {
		this.boss = boss;
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="id_boss")
	public List<Employee>  getStaff() {
		return staff;
	}
	public void setStaff(List<Employee> staff) {
		this.staff = staff;
	}
	
	public String toString() {
		return firstname + " " + lastname + "\n" +
				title;
	}
}
