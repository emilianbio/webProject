package com.aykan.service.employee.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aykan.dao.employee.JobRepository;
import com.aykan.domain.employee.Job;
import com.aykan.service.employee.JobService;

@Service
@Transactional(rollbackFor = {RuntimeException.class, Throwable.class})
public class JobServiceImpl implements JobService{

	@Autowired
	private JobRepository jobRepository;
	
	@Override
	public boolean saveJob(Job job) {
		return jobRepository.saveJob(job);
	}

	@Override
	public boolean deleteJob(Job job) {
		return jobRepository.deleteJob(job);
	}

	@Override
	public Job updateJob(Job job) {
		return jobRepository.updateJob(job);
	}

	@Override
	public Job findJobById(Long jobId) {
		return jobRepository.findJobById(jobId);
	}

	@Override
	public List<Job> findAllJobs() {
		return jobRepository.findAllJobs();
	}

	@Override
	public List<String> findJobTitles() {
		return jobRepository.findJobTitles();
	}
	
}
