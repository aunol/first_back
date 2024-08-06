package com.example.service;

import java.util.List;

import com.example.domain.BoardVO;

public interface BoardService {

    List<BoardVO> boardList(int userNo);
    void addBoard(BoardVO vo);
    void updateBoard(BoardVO vo);
    void deleteBoard(BoardVO vo);
    
}
