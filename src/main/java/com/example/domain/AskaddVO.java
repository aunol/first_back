package com.example.domain;

import java.util.Date;

import lombok.Data;

@Data
public class AskaddVO {
	
	private int myHosNo;
	private int friendNo;
	private int userNo;
	private String status;
	private Date ask;
	private Date answel;

}
