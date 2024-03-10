package com.notesapp.notesapp.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.notesapp.notesapp.DTO.NotesDTO;
import com.notesapp.notesapp.DTO.UserDTO;
import com.notesapp.notesapp.service.NotesService;


@RestController
public class NotesController {
	
	@Autowired
	NotesService notesService;
	
	@GetMapping("/auth/home")
	public ModelAndView homePage() {
		System.out.println("----calling home");
		ModelAndView obj = new ModelAndView("home");
		System.out.println("----calling"+obj);
		//return new ModelAndView("redirect:home");
		return obj;
	}
	
	@GetMapping("/auth/getAllNotes")
	public List<NotesDTO> getAllNotes() {
		System.out.println("----All notes");
		List<NotesDTO> notes = notesService.getAllNotes();
		return notes;
	}

	@GetMapping("/auth/getNotesByUser")
	public Map<Object, Object> getNotesByUser(Integer userId) {
		Map<Object, Object> retMap = new HashMap<Object, Object>();
		try {

			if(userId!=null) {
				//UserDTO UserDetail = notesService.getUserById(userId);

					List<NotesDTO> notes = notesService.getNotesByUser(userId);
					retMap.put("code", "200");
					retMap.put("responce",notes);
					return retMap;
				
			}else 
				{ 
				retMap.put("code", "401");
				retMap.put("message", "User id required");
				  return retMap; 
			}
				 
		}catch(Exception e) {
			retMap.put("code", "400");
			retMap.put("message", "An error has occurred. Please contact the admin for assistance");
			return retMap;
		}
	}
	
	@PostMapping("/auth/saveNotes") 
	public Map<Object, Object> saveNotes(@RequestParam("imageUpload") MultipartFile file, String notes, 
			Integer userId,jakarta.servlet.http.HttpSession session)throws IOException {
		Map<Object, Object> retMap = new HashMap<Object, Object>();
		try {
			if(file.getSize()>0 && !notes.isEmpty() && userId !=null) {	
		
				  Random random = new Random(); 
				  long randomNumber = random.nextLong();
			  
				  String path = session.getServletContext().getRealPath("/")+"images"+File.separator+randomNumber+".jpg";
				  byte[] imageData = file.getBytes();
				  FileOutputStream fos = new FileOutputStream(path); //fileOutputStram use to write data and inputStram is read data 
				  fos.write(imageData); fos.close(); 
				  NotesDTO notesDTO = new NotesDTO(); 
				  notesDTO.setImageUrl(path); 
				  notesDTO.setNote(notes);
				  notesDTO.setUserId(userId); 
				  notesService.saveNotes(notesDTO); 
				  
				  retMap.put("code", "200");
				  retMap.put("responce", "Record inserted successfully");
				  return retMap;
			}else {
				retMap.put("code", "401");
				retMap.put("responce", "All fields are required");
				return retMap;
			}
			
		}catch(Exception e) {
			retMap.put("code", "400");
			retMap.put("message", "An error has occurred. Please contact the admin for assistance");
			return retMap;
		}
	 
	}
	
	@PostMapping("/auth/notesAccessControll") 
	public Map<Object, Object> notesAccessControll(Integer notesId, Boolean status){
		Map<Object, Object> retMap = new HashMap<Object, Object>();
		try {
			if(notesId!=null && status!=null) {
				notesService.notesAccessControll(notesId,status); 
				retMap.put("code", "200");
				retMap.put("responce", "Record updated successfully");
				return retMap;
			}else {
				retMap.put("code", "401");
				retMap.put("responce", "All fields are required");
				return retMap;
			}
			
		}catch(Exception e) {
			retMap.put("code", "400");
			retMap.put("message", "An error has occurred. Please contact the admin for assistance");
			return retMap;
		}
		
	}
	
	
}
