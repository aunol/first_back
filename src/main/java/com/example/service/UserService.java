package com.example.service;

import com.example.domain.UserVO;



public interface UserService {
	
	boolean isDuplicate(String field, String value);
	boolean addUser(UserVO vo);
	UserVO loginCheck(UserVO vo);
	UserVO mailCheck(String email);
	int passwordCheck(String userId, String currentPassword);
	void changePassword(String userId, String currentPassword, String newPassword);
	void titleFix(String userId, String newTitle);
	void updateLoc(String userNo, String newLoc);
	
	

}
