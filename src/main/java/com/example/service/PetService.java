package com.example.service;

import java.util.List;

import com.example.domain.PetVO;

public interface PetService {

	List<PetVO> petList(int userNo);
    void createPet(PetVO vo);
    void updatePet(PetVO vo);
    void deletePet(PetVO vo);
}
