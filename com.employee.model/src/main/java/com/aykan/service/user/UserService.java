package com.aykan.service.user;

import java.util.List;

import com.aykan.domain.user.User;
import com.aykan.web.dto.UserDto;

public interface UserService {
	
	User registerNewUser(UserDto userDto);
	
	boolean checkIfValidOldPassword(User user, String oldPassword);
	
	public void changeUserPassword(User user, String password);
	
	User saveUser(User user);
	
	User updateUser(User user);
	
	User deleteUser(User user);
	
	User findUserById(Long id);

	User findUserByEmail(String email);
	
	User findUserByUsername(String username);
	
	List<User> findAllUsers();
	
}
