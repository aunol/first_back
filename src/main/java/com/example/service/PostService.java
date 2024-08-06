package com.example.service;



import java.util.List;

import com.example.domain.PostVO;

public interface PostService {
	
	List<PostVO> postingList();
	List<PostVO> postList(int userNo);
	void addPost(PostVO vo);
	void updatePost(PostVO vo);
	void deletePost(PostVO vo);
	
	

}
