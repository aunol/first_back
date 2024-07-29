package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.domain.HospitalVO;

@Mapper
public interface HospitalDAO {

    @Select("select * from hospital")
     List<HospitalVO> hospitalList();
    
}
