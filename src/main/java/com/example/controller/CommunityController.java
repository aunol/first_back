package com.example.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.BoardVO;
import com.example.domain.PostVO;
import com.example.service.BoardService;
import com.example.service.PostService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
public class CommunityController {

    //오토와이어

    @Autowired
    private BoardService boardService;

    @Autowired
    private PostService postService;


    //////////////////////////////////////////////// Board 로직
    @GetMapping("/boardingList")
    public List<BoardVO> boardingList(){
        List<BoardVO> boardingList = boardService.boardingList();        
        System.err.println(boardingList + "보드리스트");
        return boardingList;
    }

    @GetMapping("/boardDetail")
    public BoardVO boardDetail(@Param("boardNo") int boardNo){
        BoardVO boardDetail = boardService.boardDetail(boardNo);
        System.err.println(boardDetail + "보드디테일");
        return boardDetail;
    }
    
    

    //////////////////////////////////////////////// Post 로직
    @GetMapping("/postingList")
    public List<PostVO> postingList(){
        List<PostVO> postList = postService.postingList();        
        System.err.println(postList + "포스트리스트");
        return postList;
    }







}
