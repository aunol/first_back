package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.FriendsVO;
import com.example.domain.PostOfficeVO;
import com.example.service.UserService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
public class NofiController {
	
	@Autowired
	private UserService userService;
	
	
	
///////////////////////////////
	
	// 친구수락
	@PostMapping("/accept")
	public Map<String, String> accept(int fromNo, int toNo, int postOfficeNo){
		System.out.println("친구 수락" + fromNo + toNo + postOfficeNo);
		
		Map<String, String> response = new HashMap<>();
		
		userService.acceptFriend(fromNo, toNo);
		userService.acceptFriendReverse(fromNo, toNo);
		userService.removeNoti(postOfficeNo);
//		String a = userService.getUserName(fromNo);
//		String b = userService.getUserName(toNo);	
//		userService.mkChatRoom(a, b);
		
		response.put("message", "success");
    	return response;
		
	}
	
	
	// 친구거절
	@PostMapping("/refuse")
	public Map<String, String> refuse(int postOfficeNo){
		System.out.println("친구 거절" + postOfficeNo);
		
		Map<String, String> response = new HashMap<>();		
		
		userService.removeNoti(postOfficeNo);
		
		response.put("message", "success");
    	return response;
		
	}

	// 차단
	@PostMapping("/block")
    public void block(
    		@RequestParam("userNo") String userNo, 
    		@RequestParam("friendName") String friendName) {
        System.out.println(userNo + " " + friendName);
        int relatedUserNo = userService.getUserNo(friendName);

        FriendsVO vo = new FriendsVO();
        vo.setUserNo(Integer.parseInt(userNo));        
        vo.setRelatedUserNo(relatedUserNo);

        userService.block(vo);
    }
	

	// 친구요청
	@PostMapping("/askFriend")
    public Map<String, String> askFriend(
    		@RequestParam("userNo") String userNo, 
    		@RequestParam("userName") String userName,
    		@RequestParam("friendName") String friendName) {
    	System.out.println(userNo + " " + friendName);
    	
    	
    	// 보내는 사람 번호를 현재 사용자 번호로 설정
        int fromNo = Integer.parseInt(userNo);        
        // 친구 요청을 받을 사용자의 번호를 가져오기
        int toNo = userService.getUserNo(friendName);        
        // 친구 요청 메시지 생성
        String message = userName + "님 에게서 친구신청이 왔습니다.";
        
        Map<String, String> response = new HashMap<>();
        
    	if (userService.askCheck(fromNo,toNo)) {    		
    		response.put("message", "alreadySent");
    		System.out.println("이미됬나 신청이?/");
    		return response;
    	} else if (userService.checkFriend(fromNo,toNo)) {
    		response.put("message", "alreadyFriend");
    		System.out.println("이미됬나 친구가?/");
    		return response;
    	}
    	
    	
           
        // PostOfficeVO 객체 생성 및 설정
        PostOfficeVO vo = new PostOfficeVO();
        vo.setFromNo(fromNo);
        vo.setToNo(toNo);
        vo.setMessage(message);
    	
    	userService.askFriend(vo);
    	
    	response.put("message", "success");
    	return response;
    }
	
}
