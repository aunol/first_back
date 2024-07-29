package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.HospitalDAO;
import com.example.domain.HospitalVO;

@Service
public class HospitalServiceImpl implements HospitalService{
    
    @Autowired
    private HospitalDAO hospitalDAO;

    @Override
    public  List<HospitalVO> hospitalList(){
        return hospitalDAO.hospitalList();
    }
}
