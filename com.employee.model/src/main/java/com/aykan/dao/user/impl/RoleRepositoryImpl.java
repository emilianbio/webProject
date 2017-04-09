package com.aykan.dao.user.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aykan.dao.user.RoleRepository;
import com.aykan.domain.user.Role;

@Repository
@Transactional
public class RoleRepositoryImpl implements RoleRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Role saveRole(Role role) {
		entityManager.persist(role);
		return role;
	}

	@Override
	public Role updateRole(Role role) {
		Role updatedRole = entityManager.merge(role);
		entityManager.flush();
		return updatedRole;
	}

	@Override
	public Role deleteRole(Role role) {
		if(entityManager.contains(role)){
			entityManager.remove(role);
			return role;
		}
		Role deleteRole = findRoleById(role.getId());
		entityManager.remove(deleteRole);
		return deleteRole;
	}

	@Override
	public Role findRoleById(Long id) {
		if(id == null){
			return null;
		}
		return entityManager.createNamedQuery("Role.findById", Role.class).setParameter("roleId", id).getSingleResult();
	}

	@Override
	public Role findRoleByName(String roleName) {
		if(roleName == null || roleName.trim().length() <= 0){
			return null;
		}
		return entityManager.createNamedQuery("Role.findByName", Role.class).setParameter("roleName", roleName).getSingleResult();
	}

	@Override
	public List<Role> findAllRoles() {
		return entityManager.createNamedQuery("Role.findAll", Role.class).getResultList();
	}
	
}
