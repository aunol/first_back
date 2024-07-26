package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.dao.UserDAO;
import com.example.domain.UserVO;

import jakarta.servlet.http.HttpSession;

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
    
    
    
}