package com.aykan.formatter.employee;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.aykan.domain.employee.Job;

@Component
public class JobFormatter implements Formatter<Job>{

	@Override
	public String print(Job job, Locale arg1) {
		// TODO Auto-generated method stub
		return job.getJobId().toString();
	}

	@Override
	public Job parse(String jobId, Locale arg1) throws ParseException {
		// TODO Auto-generated method stub
		Job job = new Job();
		job.setJobId(Long.parseLong(jobId));
		return job;
	}
	
	

}
