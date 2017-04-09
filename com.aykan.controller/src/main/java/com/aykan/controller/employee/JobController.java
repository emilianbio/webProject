package com.aykan.controller.employee;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aykan.domain.employee.Employee;
import com.aykan.domain.employee.Job;
import com.aykan.service.employee.JobService;

@Controller
@RequestMapping(value = "/jobs")
public class JobController {

	@Autowired
	private JobService jobService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getJobs(){
		List<Job> jobs = jobService.findAllJobs();
		return new ModelAndView("jobs", "jobs", jobs);
	}
	
	@RequestMapping(params = "saveJob")
	public ModelAndView createJobForm(){
		return new ModelAndView("saveJob", "job", new Job());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView saveJob(@ModelAttribute("job") Job job){
		jobService.saveJob(job);
		return new ModelAndView("redirect:/jobs/"+job.getJobId());
	}
	
	@RequestMapping(value = "/{jobId}")
	public String getJobById(@PathVariable Long jobId, Map<String, Object> model){
		Job job = jobService.findJobById(jobId);
		List<Employee> employees = job.getEmployees();
		model.put("job", job);
		model.put("employees", employees);
		return "viewJob";
	}
	
	@RequestMapping(value = "/{jobId}/edit", method = RequestMethod.GET)
	public ModelAndView editJob(@PathVariable Long jobId){
		Job job = jobService.findJobById(jobId);
		return new ModelAndView("editJob", "job", job);
	}
	
	@RequestMapping(value = "/{jobId}/edit", method = RequestMethod.POST)
	public ModelAndView updateJob(@ModelAttribute("job") Job job){
		jobService.updateJob(job);
		return new ModelAndView("redirect:/jobs/"+job.getJobId());
	}

	@RequestMapping(value = "/{jobId}/delete", method = RequestMethod.GET)
	public ModelAndView deleteJob(@PathVariable Long jobId){
		Job job = jobService.findJobById(jobId);
		jobService.deleteJob(job);
		return new ModelAndView("redirect:/jobs");
	}
}
