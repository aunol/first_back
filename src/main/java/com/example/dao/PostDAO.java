package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.domain.PostVO;

@Mapper
public interface PostDAO {

	@Select("SELECT p.postNo, p.userNo, p.title, p.content, p.imageUrl, p.loc, p.category, p.createdAt, p.cnt, u.userName " +
	"FROM post p " +
	"JOIN user u ON p.userNo = u.userNo")
	List<PostVO> postingList();

	@Select("SELECT * FROM post WHERE userNo = #{userNo}")
	List<PostVO> postList(@Param("userNo") int userNo);
	
	@Insert("INSERT INTO post ( userNo, title, content, category, loc ) VALUES ( #{userNo},#{title}, #{content}, #{category}, #{loc}  ) ")
	void addPost(PostVO vo);
	
	@Update("UPDATE post SET title = #{title}, content = #{content}, category = #{category} WHERE postNo = #{postNo}")
	void updatePost(PostVO vo);

	@Delete("DELETE FROM post WHERE postNo = #{postNo}")
	void deletePost(PostVO vo);
	

}
