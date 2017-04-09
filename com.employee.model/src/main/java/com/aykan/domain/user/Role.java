package com.aykan.domain.user;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity  
@Table(name = "Role")
@NamedQueries({
	@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r"),
	@NamedQuery(name="Role.findByName", query="SELECT r FROM Role r WHERE r.name = :roleName"),
	@NamedQuery(name="Role.findById", query="SELECT r FROM Role r WHERE r.id = :roleId")
})
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String name;

	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "Roles_Privileges", joinColumns = @JoinColumn(name="roleId", referencedColumnName="id"),
											inverseJoinColumns = @JoinColumn(name="privilegeId", referencedColumnName="id"))
	private List<Privilege> privileges;

	//Role table :
	//roleId --- roleName
	//  1		ROLE_ADMIN
	//	2		ROLE_USER
	// 	3		ROLE_SUPER_USER
	
	//Privilege table :
	//privilegeId  ---  privilegeName
	//	1				ROLE_READ_PRIVILEGE
	//	2				ROLE_WRITE_PRIVILEGE
	//	3				ROLE_UPDATE_PRIVILEGE
	
	//Privileges_Role
	//roleId ---  privilegeId
	//	1				1
	//	1				2
	//	1				3
	//	2				1
	//	3				1
	//	3				3				
	
	@OneToMany(mappedBy = "role")
	private List<User> users;
	
	//	private Role role; User classýndaki role nesnesine eþit olucak mappedBy
	
	public Role() {

	}
	public Role(String name, List<Privilege> privileges) {
		super();
		this.name = name;
		this.privileges = privileges;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Privilege> getPrivileges() {
		return privileges;
	}
	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
