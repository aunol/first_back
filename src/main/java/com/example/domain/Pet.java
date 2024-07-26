package com.example.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Pet {	

	private int petNo;
	private int userNo;
	private String petName;
	private String petSpecies;
	private Date petBirth;
	private String petGender;
	

}
