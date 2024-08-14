package com.example.service;

import java.util.List;

import com.example.domain.FriendsVO;
import com.example.domain.PostOfficeVO;
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
		
	int getUserNo(String friendName);
	void block(FriendsVO vo);
	List<FriendsVO> friendList(int userNo);	
	List<FriendsVO> blockList(int userNo);
	boolean askCheck(int fromNo,int toNo);
	boolean checkFriend(int fromNo,int toNo);
	void askFriend(PostOfficeVO vo);
	List<PostOfficeVO> notiList(int userNo);
	
	void acceptFriend(int fromNo, int toNo);
	void acceptFriendReverse(int fromNo, int toNo);
	void removeNoti(int postOfficeNo);
	
	
	

}
