package com.notesapp.notesapp.service;

import java.math.BigInteger;
import java.util.List;

import com.notesapp.notesapp.DTO.NotesDTO;
import com.notesapp.notesapp.DTO.UserDTO;

public interface NotesService {
	
	List<NotesDTO> getAllNotes();
	
	List<NotesDTO> getNotesByUser(int userId);
	
	public NotesDTO saveNotes(NotesDTO notesDTO);
	
	UserDTO getUserById(int userId);
	
	public void notesAccessControll(Integer notesId,boolean status);

}
