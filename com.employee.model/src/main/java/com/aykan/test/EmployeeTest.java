package com.aykan.test;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/*
import com.aykan.domain.employee.Employee;
import com.aykan.service.employee.JobService;
*/
/*import com.employee.domain.Department;
import com.employee.domain.Employee;
import com.employee.domain.Job;
import com.employee.domain.Location;
import com.employee.service.DepartmentService;
import com.employee.service.EmployeeService;
import com.employee.service.JobService;
import com.employee.service.LocationService;
*/

/*import com.employee.domain.Location;
import com.employee.service.DepartmentService;

import com.employee.service.JobService;
import com.employee.service.LocationService;*/

public class EmployeeTest {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("model-application-context.xml");
		
/*		JobService jobService = applicationContext.getBean("jobServiceImpl", JobService.class);
		List<Employee> employees = jobService.findJobById(new Long(9)).getEmployees();
		
		employees.forEach(System.out::println);*/
		
/*		LocationService locationService = applicationContext.getBean("locationServiceImpl", LocationService.class);
		DepartmentService departmentService = applicationContext.getBean("departmentServiceImpl", DepartmentService.class);
		JobService jobService = applicationContext.getBean("jobServiceImpl", JobService.class);
		EmployeeService employeeService = applicationContext.getBean("employeeServiceImpl", EmployeeService.class);*/
		
/*		
		employeeService.findEmployees().forEach(System.out::println);
		
		List<Object[]> list = employeeService.findFullEmployees();
		Employee employee = new Employee();
		for(Object [] object : list){
			employee = (Employee) object[0];
			employee.setDepartment((Department)object[1]);
			employee.setJob((Job)object[2]);
			System.out.println(employee + " " + employee.getDepartment() + " " + employee.getJob());
		}
		*/
		
/*		Location location1 = new Location("Pttt", 01200, "Adana");
		Location location2 = new Location("Pttt", 01200, "Adana");
		Location location3 = new Location("Pttt", 01200, "Adana");
		Location location4 = new Location("Pttt", 01200, "Adana");
		
		locationService.saveLocation(location4);
		locationService.saveLocation(location3);
		locationService.saveLocation(location2);
		locationService.saveLocation(location1);
		
		Department department1 = new Department("SOFTWARE", null, location1);
		Department department2 = new Department("NETWORK", null, location2);
		Department department3 = new Department("COURSE", null, location3);
		Department department4 = new Department("HEALTH", null, location4);
		
		Job job1 = new Job("APPLICATION", 2000, 5000);
		Job job2 = new Job("TESTER", 2000, 5000);
		Job job3 = new Job("GRAFIK", 2000, 5000);
		Job job4 = new Job("SDADAS", 2000, 5000);
		
		Employee employee1 = new Employee("Ferhat", "Aykan", "aykanferhat@hotmail.com", "055555555", null, job1, 5500, department1);
		Employee employee2 = new Employee("Ahmet", "Aykan", "aykanferhat@hotmail.com", "055555555", null, job2, 6000, department2);
		Employee employee3 = new Employee("Baki", "Aykan", "aykanferhat@hotmail.com", "055555555", null, job3, 7000, department3);
		Employee employee4 = new Employee("Burak", "Aykan", "aykanferhat@hotmail.com", "055555555", null, job4, 8000, department4);
		
		
		departmentService.saveDepartment(department4);
		departmentService.saveDepartment(department3);
		departmentService.saveDepartment(department2);
		departmentService.saveDepartment(department1);
		
		
		jobService.saveJob(job2);
		jobService.saveJob(job1);
		jobService.saveJob(job3);
		jobService.saveJob(job4);
		
		
		employeeService.saveEmployee(employee4);
		employeeService.saveEmployee(employee3);
		employeeService.saveEmployee(employee2);
		employeeService.saveEmployee(employee1);*/
		
		applicationContext.close();
	}
}
