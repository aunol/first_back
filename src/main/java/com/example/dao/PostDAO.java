package com.example.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.example.domain.PostVO;

@Mapper
public interface PostDAO {
	
	@Insert("INSERT INTO post ( userNo, title, content, category, loc ) VALUES ( #{userNo},#{title}, #{content}, #{category}, #{loc}  ) ")
	void addPost(PostVO vo);
	
	

}
