package com.aykan.dao.employee.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aykan.dao.employee.JobRepository;
import com.aykan.domain.employee.Job;

@Repository
@Transactional(rollbackFor = {RuntimeException.class, Throwable.class})
public class JobRepositoryImpl implements JobRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public boolean saveJob(Job job) {
		// TODO Auto-generated method stub
		entityManager.persist(job);
		return true;
	}

	@Override
	public boolean deleteJob(Job job) {
		// TODO Auto-generated method stub
		if(entityManager.contains(job)){
			entityManager.remove(job);
		}else{
			Job deleteJob = findJobById(job.getJobId());
			entityManager.remove(deleteJob);
		}
		return true;
	}

	@Override
	public Job updateJob(Job job) {
		// TODO Auto-generated method stub
		Job updatedJob = entityManager.merge(job);
		return updatedJob;
	}

	@Override
	@Transactional(readOnly = true)
	public Job findJobById(Long jobId) {
		// TODO Auto-generated method stub
		return entityManager.createNamedQuery("Job.findEmployeesById", Job.class).setParameter("jobId", jobId).getSingleResult();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Job> findAllJobs() {
		// TODO Auto-generated method stub
		return entityManager.createNamedQuery("Job.findAll", Job.class).getResultList();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<String> findJobTitles() {
		// TODO Auto-generated method stub
		return entityManager.createNamedQuery("Job.findJobTitles", String.class).getResultList();
	}
}
