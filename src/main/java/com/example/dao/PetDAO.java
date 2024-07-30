package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.domain.HospitalVO;
import com.example.domain.PetVO;

@Mapper
public interface PetDAO {
	
	@Select("SELECT * FROM pet WHERE userNo = #{userNo}")
	List<PetVO> petList(@Param("userNo") int userNo);	
	
	
    @Insert("insert into pet(userNo, petName, petSpecies, petBirth, petGender) values(#{userNo}, #{petName}, #{petSpecies}, #{petBirth}, #{petGender})")
    void createPet(PetVO vo);
}
