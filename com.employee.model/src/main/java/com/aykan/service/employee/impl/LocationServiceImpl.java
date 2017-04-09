package com.aykan.service.employee.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aykan.dao.employee.LocationRepository;
import com.aykan.domain.employee.Location;
import com.aykan.service.employee.LocationService;

@Service
@Transactional(rollbackFor = {RuntimeException.class, Throwable.class})
public class LocationServiceImpl implements LocationService{

	@Autowired
	private LocationRepository locationRepository;
	
	@Override
	public boolean saveLocation(Location location) {
		return locationRepository.saveLocation(location);
	}

	@Override
	public boolean deleteLocation(Location location) {
		return locationRepository.deleteLocation(location);
	}

	@Override
	public Location updateLocation(Location location) {
		return locationRepository.updateLocation(location);
	}

	@Override
	public Location findLocationById(Long locationId) {
		return locationRepository.findLocationById(locationId);
	}

	@Override
	public List<Location> findAllLocations() {
		return locationRepository.findAllLocations();
	}
}
