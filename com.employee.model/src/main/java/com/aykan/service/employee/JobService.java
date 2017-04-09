package com.aykan.service.employee;

import java.util.List;

import com.aykan.domain.employee.Job;

public interface JobService {

	
	boolean saveJob(Job job); 
	boolean deleteJob(Job job); 
	Job updateJob(Job job); 
	Job findJobById(Long jobId);
	List<Job> findAllJobs();
	List<String> findJobTitles();
}
