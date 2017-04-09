package com.aykan.dao.employee.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aykan.dao.employee.LocationRepository;
import com.aykan.domain.employee.Location;

@Repository
@Transactional(rollbackFor = {RuntimeException.class, Throwable.class})
public class LocationRepositoryImpl implements LocationRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public boolean saveLocation(Location location) {
		// TODO Auto-generated method stub
		entityManager.persist(location);
		return true;
	}

	@Override
	public boolean deleteLocation(Location location) {
		// TODO Auto-generated method stub
		if(entityManager.contains(location)){
			entityManager.remove(location);
		}else{
			Location deleteLocation = findLocationById(location.getLocationId());
			entityManager.remove(deleteLocation);
		}
		return true;
	}

	@Override
	public Location updateLocation(Location location) {
		// TODO Auto-generated method stub
		Location updatedLocation = entityManager.merge(location);
		entityManager.flush();
		return updatedLocation;
	}

	@Override
	@Transactional(readOnly = true)
	public Location findLocationById(Long locationId) {
		// TODO Auto-generated method stub
		return entityManager.createNamedQuery("Location.findDepartmentById", Location.class).setParameter("locationId", locationId).getSingleResult();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Location> findAllLocations() {
		// TODO Auto-generated method stub
		return entityManager.createNamedQuery("Location.findAll", Location.class).getResultList();
	}
}
