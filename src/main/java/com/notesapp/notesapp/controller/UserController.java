package com.notesapp.notesapp.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.jasper.tagplugins.jstl.core.Catch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.notesapp.notesapp.DAO.UserDAO;
import com.notesapp.notesapp.DTO.AuthRequestDTO;
import com.notesapp.notesapp.DTO.UserDTO;
import com.notesapp.notesapp.config.JWTService;
import com.notesapp.notesapp.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//Created by mallikarjun.awati on 16/02/2024.

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired 
	JWTService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	  
//Creating user	  
	@GetMapping("/createUser")
	public Map<Object, Object> createUser(@RequestBody UserDTO user ) {
		Map<Object, Object> retMap = new HashMap<Object, Object>();
		try {
			if(!user.getName().isEmpty() && !user.getMobNo().isEmpty() && !user.getPassword().isEmpty()) {
				if(user.getMobNo().length()<5 || user.getPassword().length()<5) {
					retMap.put("code", "401");
					retMap.put("message", "Mobile number and password length should be minimum 5");
					return retMap;
				}
				user.setRoles("ROLE_ADMIN");
				UserDTO userDetail = userService.checkMobileNoAvailable(user);
				if(userDetail!=null) {
					retMap.put("code", "401");
					retMap.put("message", "This mobile number user already available");
					return retMap;
				}else {
					userService.createUser(user);
				}
				
			}else {
				retMap.put("code", "401");
				retMap.put("message", "User name, Mobile no, Password required");
				return retMap;
			}
		}catch(Exception e) {
			retMap.put("code", "400");
			retMap.put("message", "An error has occurred. Please contact the admin for assistance");
			return retMap;
		}
		retMap.put("code", "200");
		retMap.put("message", "User created successfully");
		return retMap;
	}
	
//Checking user auth and generating token.	
	@GetMapping("/authenticate")
	public Map<Object, Object> authenticateAndGetToken(String name,String mobNo,String password,Model model) {
		Map<Object, Object> retMap = new HashMap<Object, Object>();
		try {
			UserDTO user = userService.getUserDetailByMobNo(mobNo);
		    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getName(), password));

		    //Check user credential valid or not
		    if (authentication.isAuthenticated()) {
		        	//ModelAndView obj  = new ModelAndView("home");
		        	//model.addAttribute("token", jwtService.generateToken(user.getName()));
		        	//return obj;
		    	int done = userService.updateLastLogin(mobNo);
		    	retMap.put("code", "200");
		    	retMap.put("token", jwtService.generateToken(user.getName()));
		    	return retMap;
		    } else {
		    	retMap.put("code", "401");
				retMap.put("message", "Bad Credential");
				return retMap;
		        }
		    
		}catch(Exception e) {
			retMap.put("code", "401");
			retMap.put("message", "Bad Credential "+e);
			System.out.println("-------"+e);
			return retMap;
		}
	}
	

	@RequestMapping("/loginPage")
	public ModelAndView loginUserPage() {	
		ModelAndView obj  = new ModelAndView("login");
		return obj;
	}
	
	@GetMapping("/getUsers")
	public List<UserDTO> getUsers() {
		List<UserDTO> allUser = userService.findAllUsers();		
		return allUser;
	}
	
	@RequestMapping("/userRegistraction")
	public ModelAndView userRegistraction() {	
		System.out.println("-------ca123");
		ModelAndView obj  = new ModelAndView("userRegistraction");
		return obj;
	}
		
}
