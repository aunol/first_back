package com.example.domain;

import java.util.Date;

import lombok.Data;

@Data
public class PetC {

	private int petContentNo;
	private int petNo;
	private int userNo;
	private String petContent;
	private Date createdAt;
	
}
