package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.PostDAO;
import com.example.domain.PostVO;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	private PostDAO postDAO;
	
	@Override
	public List<PostVO> postingList(){
		return postDAO.postingList();
	}


	@Override
	public void addPost(PostVO vo) {
		System.out.println("디비 바로앞"+vo);
		postDAO.addPost(vo);		
	}

	@Override
	public List<PostVO> postList(int userNo){
		return postDAO.postList(userNo);
	}

	@Override
	public void updatePost(PostVO vo) {
		postDAO.updatePost(vo);
	}

	@Override
	public void deletePost(PostVO vo) {
		postDAO.deletePost(vo);
	}

	@Override
    public List<PostVO> friendPosts(int relatedUserNo){
        return postDAO.friendPosts(relatedUserNo);
    }

}
