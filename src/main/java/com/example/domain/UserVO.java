package com.example.domain;

import java.util.Date;

import lombok.Data;

@Data
public class UserVO {
	
	private int userNo;
	private String userId;
	private String userName;
	private String password;
	private String email;
	private Date createDate;
	private String userLoc;
	private Date lastLogin;
	private int failCount;
	private int failNum;
	private Date failDate;
	private Date lastTry;
	
		

}
