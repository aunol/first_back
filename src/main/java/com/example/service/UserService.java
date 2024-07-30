package com.example.service;

import com.example.domain.UserVO;



public interface UserService {
	
	boolean isDuplicate(String field, String value);
	boolean addUser(UserVO vo);
	UserVO loginCheck(UserVO vo);
	UserVO mailCheck(String email);
	
	
	

}
