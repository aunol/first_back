package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.domain.FriendsVO;
import com.example.domain.PostOfficeVO;
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
    
      /////// 친구 
    
    ///// 이름으로 번호확인
    @Select("SELECT userNo FROM user WHERE userName = #{friendName} ")
    int getUserNo(String friendName);
    
    @Insert("INSERT INTO friends (userNo, relatedUserNo, status) VALUES ( #{userNo}, #{relatedUserNo}, 'block' )")
    void block(FriendsVO vo);
    
    @Select("SELECT f.*, u.userName " +
            "FROM friends f " +
            "JOIN user u ON f.relatedUserNo = u.userNo " +
            "WHERE f.userNo = #{userNo} AND f.status = 'friend'")
    List<FriendsVO> friendList(int userNo);
    
    @Select("SELECT * FROM friends WHERE userNo = #{userNo} AND status = 'block'")
    List<FriendsVO> blockList(int userNo);
    
    @Select("SELECT COUNT(*) FROM postOffice WHERE fromNo = #{fromNo} AND toNo = #{toNo} ")
    boolean askCheck(int fromNo,int toNo); 
    
    @Select("SELECT COUNT(*) FROM friends WHERE userNo = #{fromNo} AND relatedUserNo = #{toNo} ")
    boolean checkFriend(int fromNo,int toNo);
    
    @Insert("INSERT INTO postOffice (fromNo, toNo, message) VALUES ( #{fromNo}, #{toNo}, #{message} )")
    void askFriend(PostOfficeVO vo);
    
    @Select("SELECT * FROM postOffice WHERE toNo = #{userNo}")
    List<PostOfficeVO> notiList(int userNo);
    
    @Insert("INSERT INTO friends (userNo, relatedUserNo, status) VALUES ( #{fromNo}, #{toNo}, 'friend' )")
    void acceptFriend(int fromNo, int toNo);
    
    @Insert("INSERT INTO friends (userNo, relatedUserNo, status) VALUES ( #{toNo}, #{fromNo}, 'friend' )")
    void acceptFriendReverse(int fromNo, int toNo);
    
    @Delete("DELETE FROM postOffice WHERE postOfficeNo = #{postOfficeNo} ")
	void removeNoti(int postOfficeNo);

    
}