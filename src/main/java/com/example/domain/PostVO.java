package com.example.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PostVO {
	
	 private int postNo;
	 private int userNo;
	 private String title;
	 private String content;
	 private String imageUrl;
	 private String loc;
	 private String category;
	 private LocalDateTime createdAt;
	 private int cnt;

	 private String userName;

}
