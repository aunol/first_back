package com.example.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BoardVO {
	
	private int boardNo;
    private int userNo;
    private String title;
    private String content;
    private String imageUrl;
    private String category;
    private LocalDateTime createdAt;
    private int cnt;

	

}
