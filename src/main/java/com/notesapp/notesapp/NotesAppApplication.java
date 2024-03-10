package com.notesapp.notesapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class NotesAppApplication{

	
	public static void main(String[] args) {
		SpringApplication.run(NotesAppApplication.class, args);
		System.out.println("-----calling");
	}

}
