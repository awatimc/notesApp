package com.notesapp.notesapp.service;

import java.util.List;

import com.notesapp.notesapp.DTO.UserDTO;

public interface UserService {
	
	List<UserDTO> findAllUsers();

	UserDTO createUser(UserDTO user);
	
	UserDTO getUserDetailByMobNo(String mobileNo);
	
	UserDTO checkMobileNoAvailable(UserDTO user);
	
	int updateLastLogin(String mobNo);
}
