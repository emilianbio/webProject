package com.aykan.dao.user;

import java.util.List;

import com.aykan.domain.user.Role;

public interface RoleRepository {

	Role saveRole(Role role);
	
	Role updateRole(Role role);
	
	Role deleteRole(Role role);
	
	Role findRoleById(Long id);

	Role findRoleByName(String roleName);
	
	List<Role> findAllRoles();
	
}
