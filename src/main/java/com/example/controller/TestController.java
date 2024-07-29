package com.example.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.HospitalVO;
import com.example.domain.UserVO;
import com.example.service.EmailService;
import com.example.service.HospitalService;
import com.example.service.UserService;


@CrossOrigin(origins="http://localhost:3000")
@RestController
public class TestController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private HospitalService hospitalService;
    

    // 병원리스트 불러오기
    @GetMapping("/hospitals")
    public List<HospitalVO> hospitalList() {
        System.out.println("/hospitals ");
        List<HospitalVO> hospitals = hospitalService.hospitalList();
        System.out.println("hospitalList 호출: " + hospitals);
        return hospitals;
    }

    









    // 아이디, 이메일, 닉네임 중복 체크
    @RequestMapping("/checkDuplicate")
    public ResponseEntity<Map<String, Boolean>> checkDuplicate(@RequestBody Map<String, String> request) {
        
        String field = request.get("field");
        String value = request.get("value");

        if (field == null || value == null) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("isDuplicate", false));
        }

        boolean isDuplicate = userService.isDuplicate(field, value);

        return ResponseEntity.ok(Collections.singletonMap("isDuplicate", isDuplicate));
    }
    
    
    // 회원가입
    @PostMapping("/addUser")
    @ResponseBody
    public Map<String, String> addUser(@RequestBody UserVO vo) {       
        Map<String, String> response = new HashMap<>();
                
        try {
            // 필수 필드 검증
            if (vo.getUserId() == null || vo.getPassword() == null || vo.getEmail() == null || vo.getUserName() == null) {
                response.put("message", "모든 필드를 입력해야 합니다.");                
                return response;
            }
            // 사용자 추가 로직            
            userService.addUser(vo);            
            response.put("message", "가입되었습니다.");             
            return response;
            
        } catch (Exception e) {
            // 예외 처리
            response.put("message", "회원 가입 중 오류가 발생했습니다.");            
            e.printStackTrace(); // 예외의 자세한 정보를 로그에 기록합니다.
        }
        return response;
    }
        
    // 로그인
    @PostMapping("/login")  
    @ResponseBody
    public Map<String, String> login(@RequestBody UserVO vo) {   	
    	
    	
        System.out.println(vo.toString());
        
        // 클라이언트로 보내는 메세지
        Map<String, String> response = new HashMap<>();
        
        // 사용자 인증
        UserVO result = userService.loginCheck(vo);
        if (result == null) {
            response.put("message", "아이디 혹은 비밀번호를 확인해주세요.");            
            return response;
        } else if (vo.getPassword().equals(result.getPassword())) {
            // 비밀번호 일치 확인            
            response.put("message", "로그인 성공");
            response.put("UserNo", String.valueOf(result.getUserNo()));
            response.put("UserId", result.getUserId());
            response.put("UserName", result.getUserName());
            response.put("UserLoc", result.getUserLoc());
            return response;
        } else {
            response.put("message", "아이디 혹은 비밀번호를 확인해주세요.");
            System.out.println("nono");
            return response;
        }
    }
    
    
    // 메일체크
    @PostMapping("/mailCheck")
        public Map<String, String> mailCheck(@RequestBody Map<String, String> requestBody){
    	Map<String, String> response = new HashMap<>();
    	       	
    	String email = requestBody.get("email");
    	System.out.println(email); 
    	UserVO result = userService.mailCheck(email);
    	
    	
    	 if (result != null) {
    		 System.out.println(result.toString());
    		 emailService.sendIdPass(email, result.getUserId(), result.getPassword());
             response.put("message", "기재하신 메일주소로 아이디를 전송하였습니다.");  
         } else if(result == null) {
        	 System.out.println("No user found with email: " + email);
             response.put("message", "기재하신 메일주소가 등록된 아이디와 일치하지 않습니다.");
         }    	     	 
    	    
    	return response;
    }
    
    
    
    

    
    
    
}
