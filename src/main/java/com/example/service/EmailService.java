package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender emailSender;
	
	public void sendIdPass(String to, String userId, String password) {
		
		try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject("가입한 아이디입니다. ");
            message.setText("아이디: " + userId + "\n비밀번호: " + password);
            emailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace(); // 로그에 에러 기록
            throw new RuntimeException("이메일 전송 중 오류가 발생했습니다.", e);
        }
    }

}
