package com.notesapp.notesapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class AuthRequestDTO {
	
	   private String username ;
	    private String password;
	    
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public AuthRequestDTO(String username, String password) {
			super();
			this.username = username;
			this.password = password;
		}
	    
	    

}
