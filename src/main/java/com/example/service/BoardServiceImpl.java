package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.BoardDAO;
import com.example.domain.BoardVO;


@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardDAO boardDAO;

    @Override
	public List<BoardVO> boardList(int userNo){
		return boardDAO.boardList(userNo);
	}

    @Override
    public void addBoard(BoardVO vo) {
        boardDAO.addBoard(vo);
    }
    
    @Override
    public void updateBoard(BoardVO vo) {
        boardDAO.updateBoard(vo);
    }

    @Override
    public void deleteBoard(BoardVO vo) {
        boardDAO.deleteBoard(vo);
    }
}
