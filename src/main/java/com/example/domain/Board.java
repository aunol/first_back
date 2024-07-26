package com.example.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Board {
	
	private int articleNo;
	private int userNo;
	private String title;
	private Date createdAt;
	private String content;
	private int myHosNo;

}
