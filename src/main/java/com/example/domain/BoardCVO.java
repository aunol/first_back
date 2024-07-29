package com.example.domain;

import java.util.Date;

import lombok.Data;

@Data
public class BoardCVO {
	
	private int commentNo;
	private int articleNo;
	private int userNo;
	private String comment;
	private Date createdAt;
	

}
