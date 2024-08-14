package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.UserDAO;
import com.example.domain.FriendsVO;
import com.example.domain.PostOfficeVO;
import com.example.domain.UserVO;



@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public boolean isDuplicate(String field, String value) {
        switch (field) {
            case "userId":
                return userDAO.existsByUserId(value);
            case "email":
                return userDAO.existsByEmail(value);
            case "userName":
                return userDAO.existsByUserName(value);
            default:
                return false;
        }
    }

    @Override
    public boolean addUser(UserVO vo) {
        return userDAO.addUser(vo) > 0;
    }
    
    @Override
    public UserVO loginCheck(UserVO vo) {
    	
    	return userDAO.loginCheck(vo);
    	
    }
    
    @Override
    public UserVO mailCheck(String email) {
    	return userDAO.mailCheck(email);
    }

    @Override
    public int passwordCheck(String userId, String currentPassword) {
        return userDAO.passwordCheck(userId, currentPassword);
    }

    @Override
    public void changePassword(String userId, String currentPassword, String newPassword) {
        userDAO.changePassword(userId, currentPassword, newPassword);
    }
    
    @Override
    public void titleFix(String userId, String newTitle) {
    	userDAO.titleFix(userId, newTitle);
    }
    
    @Override
    public void updateLoc(String userNo, String newLoc) {
    	userDAO.updateLoc(userNo, newLoc);
    }
    
    
    
    @Override
    public int getUserNo(String friendName) {
    	return userDAO.getUserNo(friendName);
    }
    
    @Override
    public void block(FriendsVO vo) {
    	userDAO.block(vo);
    }
    
    @Override
    public List<FriendsVO> friendList(int userNo) {
    	return userDAO.friendList(userNo);
    }
    
    	
    @Override
    public List<FriendsVO> blockList(int userNo) {
    	return userDAO.blockList(userNo);
    }
    
    @Override
    public boolean askCheck(int fromNo,int toNo) {
    	return userDAO.askCheck(fromNo, toNo);
    }
    
    @Override
    public boolean checkFriend(int fromNo,int toNo) {
    	return userDAO.checkFriend(fromNo, toNo);
    }
    
    @Override
    public void askFriend(PostOfficeVO vo) {
    	userDAO.askFriend(vo);
    }
    
    @Override
    public List<PostOfficeVO> notiList(int userNo){
    	return userDAO.notiList(userNo);
    }
    
    @Override
    public void acceptFriend(int fromNo, int toNo) {
    	userDAO.acceptFriend(fromNo, toNo);
    }
    
    @Override
    public void acceptFriendReverse(int fromNo, int toNo) {
    	userDAO.acceptFriendReverse(fromNo, toNo);
    }
    
    @Override
    public void removeNoti(int postOfficeNo) {
    	userDAO.removeNoti(postOfficeNo);
    }
    
    
    
    
}