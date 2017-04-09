package com.aykan.domain.employee;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
	@NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e"),
	@NamedQuery(name = "Employee.findFullById", query = "SELECT e FROM Employee e LEFT OUTER JOIN FETCH e.department "
			+ "LEFT OUTER JOIN FETCH e.job WHERE e.employeeId = :employeeId"),
	@NamedQuery(name = "Employee.count", query = "SELECT count(e) FROM Employee e"),
	@NamedQuery(name = "Employee.betweenSalay", query = "SELECT e FROM Employee e WHERE e.salary > :minSalary AND e.salary < :maxSalary")
})
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long employeeId;
	
	@Column(length = 25)
	private String firstName;
	
	@Column
	private String lastName;
	
	@Column
	private String email;
	
	@Column
	private String phoneNumber;
	
	@Temporal(TemporalType.DATE)
	@Column
	private Date hireDate;
	
	//Biz hireDate nesnesine
	//setHireDate(new Date()); parametresi gönderdigimizde bu date nesnesi gün ay yýl saat dakika saniye hepsini kapsar ve veritabanýna hepsini gecirir.
	//@Temporal(TemporalType.DATE) kullanarak sadece veritabanýmýza gün ay ve yýl gecmesini saglayabiliriz.
	//@Temporal(TemporalType.TIME) diyerek sadece dakika saat ve saniye gecirebiliriz.
	//@Temporal(TemporalType.TIMESTAMP) diyerek yine tüm verilerin düzgün bir formatta gecmesini saglayabiliriz.
	//ben sadece gün ay ve yýl tutmak istedigimden date olarak tanýmladým.
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "jobId", foreignKey = @ForeignKey(foreignKeyDefinition = "jobId_fk"))
	private Job job;
	
	@Column
	private double salary;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "departmentId", foreignKey = @ForeignKey(foreignKeyDefinition = "departmentId_fk"))
	private Department department;
	
	public Employee() {
	}

	public Employee(String firstName, String lastName, String email, String phoneNumber, Date hireDate, Job job,
			double salary, Department department) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.hireDate = hireDate;
		this.job = job;
		this.salary = salary;
		this.department = department;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", phoneNumber=" + phoneNumber + ", hireDate=" + hireDate + ", salary=" + salary + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (employeeId == null) {
			if (other.employeeId != null)
				return false;
		} else if (!employeeId.equals(other.employeeId))
			return false;
		return true;
	}
	
}
