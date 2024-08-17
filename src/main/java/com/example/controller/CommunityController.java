package com.example.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.BoardVO;
import com.example.domain.FriendsVO;
import com.example.domain.PostOfficeVO;
import com.example.domain.PostVO;
import com.example.service.BoardService;
import com.example.service.PostService;
import com.example.service.UserService;

@CrossOrigin(origins="http://223.130.154.211:3000")
@RestController
public class CommunityController {

    //오토와이어

    @Autowired
    private BoardService boardService;

    @Autowired
    private PostService postService;
    
    @Autowired
    private UserService userService;


    //////////////////////////////////////////////// Board 로직
    @GetMapping("/boardingList")
    public List<BoardVO> boardingList(){
        List<BoardVO> boardingList = boardService.boardingList();        
        System.err.println(boardingList + "보드리스트");
        return boardingList;
    }

    

    //////////////////////////////////////////////// Post 로직
    @GetMapping("/postingList")
    public List<PostVO> postingList(){
        List<PostVO> postList = postService.postingList();        
        System.err.println(postList + "포스트리스트");
        return postList;
    }
    
    
 // 내게 온 메세지 가져오기
    @GetMapping("notiList")
    public List<PostOfficeVO> notiList(@Param("userNo") int userNo) {
    	System.out.println("/notiList" + userNo);
    	List<PostOfficeVO> notiList = userService.notiList(userNo);
    	System.out.println("내게온메세지 호출" + notiList);
    	return notiList; 	 	
    	
    }
    
    /////////////////////////////////////////////////////////////////////////// 친구관련
    @PostMapping("/blockList")
    public List<FriendsVO> blockList(@Param("userNo") int userNo){
    	System.out.println(userNo + "블락리스트");
    	List<FriendsVO> block = userService.blockList(userNo);
    	System.out.println(block);
    	return block;
    }
    
    @PostMapping("/friendList")
    public List<FriendsVO> friendList(@Param("userNo") int userNo){
    	System.out.println(userNo + "친구리스트");
    	List<FriendsVO> friend = userService.friendList(userNo);
    	System.out.println(friend);    	
    	
    	return friend;
    }

    @GetMapping("friendPosts")
    public List<PostVO> friendPosts(@Param("relatedUserNo") int relatedUserNo){
        System.out.println(relatedUserNo + "친구포스트");
        List<PostVO> friendPosts = postService.friendPosts(relatedUserNo);
        System.out.println(friendPosts);
        return friendPosts;
    }

    @GetMapping("friendBoards")
    public List<BoardVO> friendBoards(@Param("relatedUserNo") int relatedUserNo){
        System.out.println(relatedUserNo + "친구보드");
         List<BoardVO> friendBoards = boardService.friendBoards(relatedUserNo);
         System.out.println(friendBoards);
         return friendBoards;
    }








}
