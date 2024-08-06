package com.example.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.HospitalVO;
import com.example.domain.PetVO;
import com.example.domain.PostVO;
import com.example.domain.UserVO;
import com.example.domain.BoardVO;
import com.example.service.BoardService;
import com.example.service.EmailService;
import com.example.service.HospitalService;
import com.example.service.PetService;
import com.example.service.PostService;
import com.example.service.UserService;



@CrossOrigin(origins="http://localhost:3000")
@RestController
public class TestController {


    // 오토와이어
    @Autowired
    private UserService userService;
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private PetService petService;

    @Autowired
    private BoardService boardService;
    
    @Autowired
    private PostService postService;  




///////////////////////////////////////              /로직


    // 병원리스트 불러오기
    @GetMapping("/hospitals")
    public List<HospitalVO> hospitalList() {
        System.out.println("/hospitals ");
        List<HospitalVO> hospitals = hospitalService.hospitalList();
        System.out.println("hospitalList 호출: " + hospitals);
        return hospitals;
    }





////////////////////////////////////////////       / 펫 관련    
    // 펫 리스트
    @GetMapping("/petList")
    public List<PetVO> petList(@Param("userNo") int userNo){
    	System.out.println("/petList");
    	List<PetVO> petList = petService.petList(userNo);
    	System.out.println("petList 호출" + petList);
    	return petList;
    }
    
    // 펫 생성
    @PostMapping("/createPet")
    public void createPet(@RequestBody PetVO vo) {
        System.out.println("자료확인 : " + vo.toString());
        petService.createPet(vo);
        
    }
    
    //펫 수정
    @PostMapping("/updatePet")
    public void updatePet(@RequestBody PetVO vo) {
    	System.out.println("fix Check : " + vo.toString());
    	petService.updatePet(vo);
    }
    
    
    // 펫 삭제
    @PostMapping("/deletePet")
    public void deletePet(@RequestBody PetVO vo) {
    	System.out.println("del Check : " + vo.toString());
    	petService.deletePet(vo);
    }
    
    

    

//    @GetMapping("/${userNumber}")
//    public String getMethodName(@RequestParam String param) {
//        return new String();
//    }
//    
    









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
        
        // 클라이언트로 보내는 메세지, 아이디 정보
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
            response.put("UserTitle", result.getTitle());
            System.out.println(result);
            
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

    //비밀번호 변경
    @PostMapping("/changePassword")
    public void changePassword(
        @RequestParam String userId, 
        @RequestParam String currentPassword, 
        @RequestParam String newPassword) {
        System.out.println("changePassword Check : " + userId + currentPassword + newPassword);
        
        userService.changePassword(userId, currentPassword, newPassword);
    }
    //지역변경
    @PostMapping("updateLoc")
    public void updateLoc( 
    		@RequestParam String userNo, 
            @RequestParam String newLoc ) {
    	System.out.println("자료 :" + userNo + newLoc);
    	userService.updateLoc(userNo, newLoc);
    	
    	
    }
    
    // 타이틀변경
    @PostMapping("/titleFix")
    public void titleFix(@RequestParam String userId, @RequestParam String newTitle) {
        System.out.println("요청: " + userId + " " + newTitle);
       userService.titleFix(userId, newTitle);    
        

       
    }
    
    /////////////////////////////////////  포스팅
  
    // 포스팅 리스트가져오기
    @GetMapping("/postList")
    public List<PostVO> postList(@Param("userNo") int userNo) {
        System.out.println("/postList 호출");
        List<PostVO> postList = postService.postList(userNo);
        System.out.println("postList 호출" + postList);
        return postList;
    
    }
    
    // 포스팅 추가
    @PostMapping("/addPost")
    public void addPost(@RequestBody Map<String, String> requestBody ) {
    	System.out.println("갖고온거 "+requestBody);
    	
    	// Map에서 PostVO 객체로 변환
        PostVO vo = new PostVO();
        vo.setUserNo(Integer.parseInt(requestBody.get("userNo")));
        vo.setTitle(requestBody.get("title"));
        vo.setContent(requestBody.get("content"));
        vo.setLoc(requestBody.get("userLoc")); // 필드 이름 수정
        vo.setCategory(requestBody.get("category"));
        
        System.out.println("디비로보내는거"+vo);
        // 서비스 호출
        postService.addPost(vo);
    }


    /// 포스팅 수정
    @PostMapping("/updatePost")
    public void updatePost(@RequestBody PostVO vo){
        System.out.println("updatePost 호출" + vo);
        postService.updatePost(vo);
    }

    /// 포스팅 삭제
    @PostMapping("/deletePost")
    public void deletePost(@RequestBody PostVO vo) {
        System.out.println("deletePost 호출" + vo);
        postService.deletePost(vo);
    }
    
    

/////////////////////////////////////////////////


    /// 게시물리스트
    @GetMapping("/boardList")
    public List<BoardVO> boardList(@Param("userNo") int userNo){
        System.out.println("/boardList");
        List<BoardVO> boardList = boardService.boardList(userNo);
        System.out.println("boardList 호출" + boardList);
        return boardList;      
        }


    /// 게시물추가
    @PostMapping("/addBoard")
    public void addBoard(@RequestBody BoardVO vo) {
    	System.out.println("fix Check : " + vo.toString());
        boardService.addBoard(vo);
       }


    /// 게시물수정
   @PostMapping("/updateBoard")
    public void updateBoard(@RequestBody BoardVO vo) {
    	System.out.println("fix Check : " + vo.toString());
        boardService.updateBoard(vo);
       }


    /// 게시물삭제
    @PostMapping("/deleteBoard")
    public void deleteBoard(@RequestBody BoardVO vo) {
    	System.out.println("fix Check : " + vo.toString());
        boardService.deleteBoard(vo);
       }


    
    //    //펫 수정
    //    @PostMapping("/updatePet")
    //    public void updatePet(@RequestBody PetVO vo) {
    //        System.out.println("fix Check : " + vo.toString());
    //        petService.updatePet(vo);
    //    }
       
       
    //    // 펫 삭제
    //    @PostMapping("/deletePet")
    //    public void deletePet(@RequestBody PetVO vo) {
    //        System.out.println("del Check : " + vo.toString());
    //        petService.deletePet(vo);
    //    }
       

    
    
    
}
