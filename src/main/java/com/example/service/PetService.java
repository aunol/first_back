package com.example.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.domain.PetVO;

public interface PetService {

	List<PetVO> petList(int userNo);
    void createPet(PetVO vo);
}
