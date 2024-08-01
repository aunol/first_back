package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.domain.PetVO;

@Mapper
public interface PetDAO {
	
	@Select("SELECT * FROM pet WHERE userNo = #{userNo}")
	List<PetVO> petList(@Param("userNo") int userNo);
		
    @Insert("insert into pet(userNo, petName, petSpecies, petBirth, petGender) values(#{userNo}, #{petName}, #{petSpecies}, #{petBirth}, #{petGender})")
    void createPet(PetVO vo);    
    
    @Update("UPDATE pet SET petName = #{petName}, petSpecies = #{petSpecies}, petBirth = #{petBirth}, petGender = #{petGender} WHERE petNo = #{petNo}")
    void updatePet(PetVO vo);
    
    @Delete("DELETE FROM pet WHERE petNo = #{petNo}")
    void deletePet(PetVO vo);

    
}
