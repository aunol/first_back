package com.example.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.domain.UserVO;

@Mapper
public interface UserDAO {

    @Select("SELECT COUNT(*) > 0 FROM user WHERE userId = #{userId}")
    boolean existsByUserId(@Param("userId") String userId);

    @Select("SELECT COUNT(*) > 0 FROM user WHERE email = #{email}")
    boolean existsByEmail(@Param("email") String email);

    @Select("SELECT COUNT(*) > 0 FROM user WHERE userName = #{userName}")
    boolean existsByUserName(@Param("userName") String userName);

    @Insert("INSERT INTO user (userId, password, userName, email) VALUES (#{userId}, #{password}, #{userName}, #{email})")
    int addUser(UserVO vo);
    
    @Select("SELECT * FROM user WHERE userId= #{userId} AND password= #{password}")
    UserVO loginCheck(UserVO vo);
    
    @Select("SELECT * FROM user WHERE email = #{email}")
    UserVO mailCheck(String email);

    @Select("SELECT COUNT(*) FROM user WHERE userId = #{userId} AND password = #{currentPassword}")
    int passwordCheck(String userId, String currentPassword);


    @Update("UPDATE user SET password = #{newPassword} WHERE userId = #{userId} AND password = #{currentPassword}")
    void changePassword(String userId, String currentPassword, String newPassword);
    
    @Update("UPDATE user SET title = #{newTitle} WHERE userId = #{userId}")
    void titleFix(String userId, String newTitle);
    
    @Update("UPDATE user SET userLoc = #{newLoc} WHERE userNo = #{userNo}")
    void updateLoc(String userNo, String newLoc);
    
}