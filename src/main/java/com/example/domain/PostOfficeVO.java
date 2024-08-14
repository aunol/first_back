package com.example.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.Data;

@Data
public class PostOfficeVO {
	
	private int postOfficeNo;
	private int fromNo;
	private int toNo;
	private String message;
	private LocalDateTime createdAt; 
	
}




