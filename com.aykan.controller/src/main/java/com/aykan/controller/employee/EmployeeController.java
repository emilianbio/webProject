package com.aykan.controller.employee;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aykan.domain.employee.Department;
import com.aykan.domain.employee.Employee;
import com.aykan.domain.employee.Job;
import com.aykan.service.employee.DepartmentService;
import com.aykan.service.employee.EmployeeService;
import com.aykan.service.employee.JobService;
import com.aykan.spring.validator.EmployeeValidator;
import com.aykan.web.exceptiýon.EmployeeNotFoundException;

//wwww.aykan.com/employees/...
@Controller
@RequestMapping(value = "/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private EmployeeValidator employeeValidator;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getEmployees(Model model){
	
		List<Employee> employees = employeeService.findEmployees();
		
		model.addAttribute("employees", employees);
		return "employees";
	}
	
	// /employees?saveEmployee
	@RequestMapping(params = "saveEmployee")
	public String createEmployeeForm(Model model){
		model.addAttribute("employee", new Employee());
		getDepartmentNames(model);
		getJobs(model);
		return "saveEmployee";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView saveEmployee(@ModelAttribute("employee") @Valid Employee employee, BindingResult result){
		if(result.hasErrors()){
			return new ModelAndView("saveEmployee", "employee", employee);
		}
		employee.setHireDate(new Date());
		employeeService.saveEmployee(employee);
		return new ModelAndView("redirect:/employees/"+employee.getEmployeeId());
	}
	//employeees/2233
	@RequestMapping(value = "/{employeeId}")
	public ModelAndView getEmployeeById(@PathVariable Long employeeId){
		Employee employee = employeeService.findEmployeeById(employeeId);
		if(employee == null){
			throw new EmployeeNotFoundException("Kullanýcý bulunamadý.");
		}
		return new ModelAndView("viewEmployee", "employee", employee);
	}
	
	//www.aykan.com/employees/152/edit
	@RequestMapping(value = "/{employeeId}/edit", method = RequestMethod.GET)
	public String editEmployee(@PathVariable Long employeeId, Model model){
		Employee employee = employeeService.findEmployeeById(employeeId);
		if(employee == null){
			throw new EmployeeNotFoundException();
		}
		model.addAttribute("employee", employee);
		getDepartmentNames(model);
		getJobs(model);
		return "editEmployee";
	}
	
	@RequestMapping(value = "/{employeeId}/edit", method = RequestMethod.POST)
	public ModelAndView updateEmployee(@ModelAttribute("employee") Employee employee){
		employeeService.updateEmployee(employee);
		return new ModelAndView("redirect:/employees/"+employee.getEmployeeId());
	}
	
	@ExceptionHandler(Exception.class)
	public String errorEmployee(HttpServletRequest request, HttpServletResponse response, Exception exception, Model model){
		model.addAttribute("message", exception.getMessage());
		model.addAttribute("url", request.getRequestURL());
		return "errorEmployee";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.setValidator(employeeValidator);
		webDataBinder.setAllowedFields("employeeId", "firstName","lastName", "email", "phoneNumber", "hireDate", "job", "salary", "department");
	}
	
	
	private void getDepartmentNames(Model model){
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("departments", departments);
	}
	
	private void getJobs(Model model){
		List<Job> jobs = jobService.findAllJobs();
		model.addAttribute("jobs", jobs);
	}
	
}
