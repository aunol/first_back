package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.domain.BoardVO;



@Mapper
public interface BoardDAO {

    @Select("SELECT * FROM board WHERE userNo = #{userNo}")
	List<BoardVO> boardList(@Param("userNo") int userNo);
    
    @Insert("INSERT INTO board ( userNo, title, content, category ) VALUES ( #{userNo},#{title}, #{content}, #{category} ) ")
	void addBoard(BoardVO vo);
	
	@Update("UPDATE board SET title = #{title}, content = #{content}, category = #{category} WHERE boardNo = #{boardNo}")
	void updateBoard(BoardVO vo);

	@Delete("DELETE FROM board WHERE boardNo = #{boardNo}")
	void deleteBoard(BoardVO vo);



}
