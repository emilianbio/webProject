package com.aykan.service.employee;

import java.util.List;

import com.aykan.domain.employee.Location;

public interface LocationService {

	
	boolean saveLocation(Location location);

	boolean deleteLocation(Location location);

	Location updateLocation(Location location);

	Location findLocationById(Long locationId);

	List<Location> findAllLocations();
}
