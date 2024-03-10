package com.notesapp.notesapp.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notesapp.notesapp.DAO.NotesDAO;
import com.notesapp.notesapp.DTO.NotesDTO;
import com.notesapp.notesapp.DTO.UserDTO;

import jakarta.transaction.Transactional;

@Service
public class NotesServiceImpl implements NotesService {

	@Autowired
	NotesDAO notesDAO;
	
	@Override
	public List<NotesDTO> getAllNotes() {
		return notesDAO.findAll();
	}

	@Override
	public List<NotesDTO> getNotesByUser(int userId) {
		return notesDAO.getNotesByUserId(userId);
	}

	@Override
	public NotesDTO saveNotes(NotesDTO notesDTO) {
		return notesDAO.save(notesDTO);	
	}

	@Override
	public UserDTO getUserById(int userId) {

		return notesDAO.getUserById(userId);	
	}

	@Transactional
	@Override
	public void notesAccessControll(Integer notesId, boolean status) {
		notesDAO.notesAccessControll(notesId,status);	
	}

}
