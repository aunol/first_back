package com.example.service;

import org.springframework.web.bind.annotation.RequestBody;

import com.example.domain.UserVO;

import jakarta.servlet.http.HttpSession;

public interface UserService {
	
	boolean isDuplicate(String field, String value);
	boolean addUser(UserVO vo);
	UserVO loginCheck(UserVO vo);
	UserVO mailCheck(String email);
	
	
	

}
