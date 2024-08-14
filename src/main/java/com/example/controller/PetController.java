package com.example.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.PetVO;
import com.example.service.PetService;
import com.example.service.UserService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
public class PetController {
	
	// 오토와이어 
    
	@Autowired
    private PetService petService;
	
///////////////////////////////////////              /로직
	
	
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


}
