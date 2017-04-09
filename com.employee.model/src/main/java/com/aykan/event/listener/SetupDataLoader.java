package com.aykan.event.listener;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.aykan.dao.user.PrivilegeRepository;
import com.aykan.dao.user.RoleRepository;
import com.aykan.dao.user.UserRepository;
import com.aykan.domain.employee.Department;
import com.aykan.domain.employee.Employee;
import com.aykan.domain.employee.Job;
import com.aykan.domain.employee.Location;
import com.aykan.domain.user.Privilege;
import com.aykan.domain.user.Role;
import com.aykan.domain.user.User;
import com.aykan.service.employee.DepartmentService;
import com.aykan.service.employee.EmployeeService;
import com.aykan.service.employee.JobService;
import com.aykan.service.employee.LocationService;

@Component
@Transactional
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	private LocationService locationService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private PrivilegeRepository privilegeRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		//Ýlk contextLoader dinliyor çalýsýyor - Sonra springDispatcher calýsýyor.
		if(event.getApplicationContext().getParent() == null){
			createEmployees();
			createUser();
		}	
	}
	private void createUser(){
		Privilege readPrivilege = createPrivilegeIfNotFound("ROLE_READ_PRIVILEGE");
		Privilege writePrivilege = createPrivilegeIfNotFound("ROLE_WRITE_PRIVILEGE");
		
		List<Privilege> adminPrivilege = Arrays.asList(readPrivilege, writePrivilege);
		List<Privilege> userPrivilege = Arrays.asList(readPrivilege);
		
		Role roleUser = createRoleIfNotFound("ROLE_USER", userPrivilege);
		Role roleAdmin = createRoleIfNotFound("ROLE_ADMIN", adminPrivilege);
		createRoleIfNotFound("ROLE_USER", userPrivilege);
		
		User user = new User("Ferhat", "Aykan", "aykanferhat@hotmail.com");
		user.setUsername("admin");
		user.setPassword(passwordEncoder.encode("1234"));
		user.setEnabled(true);
		user.setRole(roleAdmin);
		userRepository.saveUser(user);
		
		User user2 = new User("Baki", "Aykan", "aykanbaki@hotmail.com");
		user2.setUsername("user1");
		user2.setPassword(passwordEncoder.encode("1234"));
		user2.setEnabled(true);
		user2.setRole(roleUser);
		userRepository.saveUser(user2);
		
		User user3 = new User("Ahmet", "Aykan", "aykanahmet@hotmail.com");
		user3.setUsername("user2");
		user3.setPassword(passwordEncoder.encode("1234"));
		user3.setEnabled(true);
		user3.setRole(roleUser);
		userRepository.saveUser(user3);
		
	}


	private Role createRoleIfNotFound(String name, List<Privilege> privileges) {
		Role role = new Role(name, privileges);
		roleRepository.saveRole(role);
		return role;
	}


	private Privilege createPrivilegeIfNotFound(String name) {
		Privilege privilege = new Privilege();
		privilege.setName(name);
		privilegeRepository.savePrivilege(privilege);
		return privilege;
	}


	private void createEmployees(){
		Location location1 = new Location("Pttt", 01200, "Adana");
		Location location2 = new Location("Arr	", 01200, "Istanbul");
		Location location3 = new Location("Kcc", 01200, "A");
		Location location4 = new Location("Bvv", 01200, "Adana");

		locationService.saveLocation(location4);
		locationService.saveLocation(location3);
		locationService.saveLocation(location2);
		locationService.saveLocation(location1);
		
		Department department1 = new Department("SOFTWARE", location1);
		Department department2 = new Department("NETWORK", location2);
		Department department3 = new Department("COURSE", location3);
		Department department4 = new Department("HEALTH", location4);
		
		departmentService.saveDepartment(department4);
		departmentService.saveDepartment(department3);
		departmentService.saveDepartment(department2);
		departmentService.saveDepartment(department1);
		
		Job job1 = new Job("APPLICATION", 2000, 5000);
		Job job2 = new Job("TESTER", 2000, 5000);
		Job job3 = new Job("GRAFIK", 2000, 5000);
		Job job4 = new Job("SDADAS", 2000, 5000);
		
		jobService.saveJob(job2);
		jobService.saveJob(job1);
		jobService.saveJob(job3);
		jobService.saveJob(job4);
		
		Employee employee1 = new Employee("Ferhat", "Aykan", "aykanferhat@hotmail.com", "051223123", null, job1, 5500, department1);
		Employee employee2 = new Employee("Ahmet", "Aykan", "aykanfer@hotmail.com", "051231223", null, job2, 6000, department2);
		Employee employee3 = new Employee("Baki", "Aykan", "aykanferha@hotmail.com", "051231123", null, job3, 7000, department3);
		Employee employee4 = new Employee("Burak", "Aykan", "aykt@hotmail.com", "051231125", null, job4, 8000, department4);
		
		employeeService.saveEmployee(employee4);
		employeeService.saveEmployee(employee3);
		employeeService.saveEmployee(employee2);
		employeeService.saveEmployee(employee1);
	}
	
}
