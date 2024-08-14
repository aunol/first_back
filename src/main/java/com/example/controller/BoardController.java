package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.domain.BoardVO;
import com.example.service.BoardService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
public class BoardController {


    @Autowired
    private BoardService boardService;
    
        ////윈도우 파일 저장 경로 지정 (컨트롤러에서 직접 지정)
        private final String imageDirectory = "/root/downloads/img/";

    /// 게시물리스트
    @GetMapping("/boardList")
    public List<BoardVO> boardList(@Param("userNo") int userNo){
        System.out.println("/boardList");
        List<BoardVO> boardList = boardService.boardList(userNo);
        System.out.println("boardList 호출" + boardList);
        return boardList;      
        }


    /// 게시물추가
    @PostMapping("/addBoard")
    public void addBoard(
            @RequestParam("title") String title,
            @RequestParam("category") String category,
            @RequestParam("content") String content,
            @RequestParam("userNo") String userNo,            
            @RequestParam(value = "image", required = false) MultipartFile image) {
    	
    	// BoardVO 객체 생성
 	   BoardVO vo = new BoardVO();
 	   vo.setUserNo(Integer.parseInt(userNo));
 	   vo.setTitle(title);
       vo.setContent(content);       
       vo.setCategory(category);
        
    // 이미지 파일 처리 로직
       if (image != null && !image.isEmpty()) {
           // 업로드 경로 설정
           String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
           Path filePath = Paths.get(imageDirectory + fileName);

           try {
               // 업로드 디렉터리 확인 및 생성
               File dir = new File(imageDirectory);
               if (!dir.exists()) {
                   dir.mkdirs();  // 디렉터리가 없으면 생성
               }

               // 파일 저장
               Files.write(filePath, image.getBytes());

               // 저장된 이미지 URL을 PostVO에 설정
               vo.setImageUrl(filePath.toString());

               System.out.println("업로드된 이미지: " + filePath.toString());

           } catch (IOException e) {
               System.err.println("이미지 저장 중 오류 발생: " + e.getMessage());
               // 예외 처리 로직 추가 가능 (e.g., 로그 기록, 사용자에게 오류 알림 등)
           }
       }

       // 서비스 호출 (DB에 저장)       
       boardService.addBoard(vo);
       }


    /// 게시물수정
   @PostMapping("/updateBoard")
    public void updateBoard(
            @RequestParam("title") String title,
            @RequestParam("category") String category,
            @RequestParam("content") String content,
            @RequestParam("boardNo") String boardNo,            
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam(value = "imageUrl", required = false ) String imageUrl) {
	   
	   // BoardVO 객체 생성
	   BoardVO vo = new BoardVO();
	   vo.setBoardNo(Integer.parseInt(boardNo));
	   vo.setTitle(title);
       vo.setContent(content);       
       vo.setCategory(category);
       
	   
    // 이미지 파일 처리 로직
       if (image != null && !image.isEmpty()) {
           // 업로드 경로 설정
           String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
           Path filePath = Paths.get(imageDirectory + fileName);

           try {
               // 업로드 디렉터리 확인 및 생성
               File dir = new File(imageDirectory);
               if (!dir.exists()) {
                   dir.mkdirs();  // 디렉터리가 없으면 생성
               }

               // 파일 저장
               Files.write(filePath, image.getBytes());

               // 저장된 이미지 URL을 PostVO에 설정
               vo.setImageUrl(filePath.toString());

               System.out.println("업로드된 이미지: " + filePath.toString());

           } catch (IOException e) {
               System.err.println("이미지 저장 중 오류 발생: " + e.getMessage());
               // 예외 처리 로직 추가 가능 (e.g., 로그 기록, 사용자에게 오류 알림 등)
           }
       } else {
    	   vo.setImageUrl(imageUrl);
       }
   	
       System.out.println("updatePost 호출" + vo);
	   
	   
    	System.out.println("fix Check : " + vo.toString());
        boardService.updateBoard(vo);
       }


    /// 게시물삭제
    @PostMapping("/deleteBoard")
    public void deleteBoard(@RequestBody BoardVO vo) {
    	System.out.println("fix Check : " + vo.toString());
        boardService.deleteBoard(vo);
       }


}
