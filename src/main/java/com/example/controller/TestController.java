package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.UserService;



@CrossOrigin(origins="http://localhost:3000")
@RestController
public class TestController {


    // 오토와이어
    @Autowired
    private UserService userService; 

  

    ////윈도우 파일 저장 경로 지정 (컨트롤러에서 직접 지정)
    private final String imageDirectory = "/root/downloads/img/";
    
    

 
    
}
