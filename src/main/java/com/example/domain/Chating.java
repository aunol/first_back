package com.example.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Chating {
	
	private int messageNo;
	private int roomNo;
	private String senderId;
	private String message;
	private Date createdAt;

}
