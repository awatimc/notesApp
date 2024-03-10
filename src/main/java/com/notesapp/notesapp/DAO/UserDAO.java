package com.notesapp.notesapp.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.notesapp.notesapp.DTO.NotesDTO;
import com.notesapp.notesapp.DTO.UserDTO;

import jakarta.transaction.Transactional;

//Created by mallikarjun.awati on 16/02/2024.

@Repository
public interface UserDAO extends JpaRepository<UserDTO, Long>{
	
	Optional<UserDTO> findByName(String username);
	
	public List<UserDTO> findAll();
	
	UserDTO save(UserDTO user);
	 
	@Query(value = "select * from schema_note.user where mobile_number=?1",nativeQuery = true)
	UserDTO getUserDetailByMobNo(String mobNo);
	
	@Modifying
	@Query(value ="update schema_note.user set last_login_date=now() where mobile_number=?1",nativeQuery = true)
	int updateLastLogin(String mobNo );
	

}
