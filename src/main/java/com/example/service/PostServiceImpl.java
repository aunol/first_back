package com.example.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.PostDAO;
import com.example.domain.PostVO;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	private PostDAO postDAO;
	
	public void addPost(PostVO vo) {
		System.out.println("디비 바로앞"+vo);
		postDAO.addPost(vo);
		
		
		
	}

}
