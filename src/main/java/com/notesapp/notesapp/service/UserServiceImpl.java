package com.notesapp.notesapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.notesapp.notesapp.DAO.UserDAO;
import com.notesapp.notesapp.DTO.UserDTO;

import jakarta.transaction.Transactional;

//Created by mallikarjun.awati on 16/02/2024.

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDAO userDAO;
	
	
	  @Autowired private PasswordEncoder passwordEncoder;
	 
	

	@Override
	public List<UserDTO> findAllUsers() {
		return userDAO.findAll();
	}


	@Override
	public UserDTO createUser(UserDTO user) {	
		user.setPassword(passwordEncoder.encode(user.getPassword())); 
		return userDAO.save(user);
	}


	@Override
	public UserDTO getUserDetailByMobNo(String mobileNo) {
		return userDAO.getUserDetailByMobNo(mobileNo);
	}


	@Override
	public UserDTO checkMobileNoAvailable(UserDTO user) {
		return userDAO.getUserDetailByMobNo(user.getMobNo());
	}

	@Transactional
	@Override
	public int updateLastLogin(String mobNo) {
		return userDAO.updateLastLogin(mobNo);
		
	}


}
