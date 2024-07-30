package com.example.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.PetDAO;
import com.example.domain.PetVO;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetDAO petDAO;

    @Override
    public void createPet(PetVO vo) {
        petDAO.createPet(vo);
    }

    @Override
    public List<PetVO> petList(int userNo) {
        return petDAO.petList(userNo);
    }
}