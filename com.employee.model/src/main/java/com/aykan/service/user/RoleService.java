package com.aykan.service.user;

import java.util.List;

import com.aykan.domain.user.Role;

public interface RoleService {
	
	Role saveRole(Role role);
	
	Role updateRole(Role role);
	
	Role deleteRole(Role role);
	
	Role findRoleById(Long id);

	Role findRoleByName(String roleName);
	
	List<Role> findAllRoles();
}
