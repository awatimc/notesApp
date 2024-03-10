package com.notesapp.notesapp.DAO;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.notesapp.notesapp.DTO.NotesDTO;
import com.notesapp.notesapp.DTO.UserDTO;

import jakarta.persistence.criteria.CriteriaBuilder.In;


@Repository
public interface NotesDAO extends JpaRepository<NotesDTO, Long> {
	
	public List<NotesDTO> findAll();
	
	@Query(value = "select * from schema_note.notes where user_id=?1  or status=false",nativeQuery = true)
	List<NotesDTO> getNotesByUserId(int userId);
	
	NotesDTO save(NotesDTO notesDTO);
	
	@Query(value = "select * from schema_note.user where id=?1",nativeQuery = true)
	UserDTO getUserById(int userId);
	
	@Modifying
	@Query(value ="update schema_note.notes set status=:status where id=:notesId",nativeQuery = true)
	void notesAccessControll(@Param("notesId")Integer notesId,@Param("status")Boolean status);
}
