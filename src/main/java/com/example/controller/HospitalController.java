package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.HospitalVO;
import com.example.service.HospitalService;


@CrossOrigin(origins="http://localhost:3000")
@RestController
public class HospitalController {
	
	
	// 오토와이어
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
	

}
