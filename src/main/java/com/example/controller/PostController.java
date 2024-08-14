package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.domain.PostVO;
import com.example.service.PostService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
public class PostController {

	
	// 오토와이어
	@Autowired
	private PostService postService;
	
	
////윈도우 파일 저장 경로 지정 (컨트롤러에서 직접 지정)
    private final String imageDirectory = "/root/downloads/img/";
    
	
//////////////////////////////////////////////////////////////////////////////포스팅관련
	  
// 포스팅 리스트가져오기
@GetMapping("/postList")
public List<PostVO> postList(@Param("userNo") int userNo) {
System.out.println("/postList 호출");
List<PostVO> postList = postService.postList(userNo);
System.out.println("postList 호출" + postList);
return postList;

}

// 경로로 이미지 가져와서 보기
@GetMapping("/getImage")
public ResponseEntity<Resource> getImage(@RequestParam("imageUrl") String imageUrl) {


try {
Path filePath = Paths.get(imageDirectory).resolve(imageUrl).normalize();
Resource resource = new UrlResource(filePath.toUri());

if (resource.exists() && resource.isReadable()) {
// 파일의 MIME 타입을 추론
String mimeType = Files.probeContentType(filePath);

// MIME 타입이 null이면 기본값으로 설정
mimeType = (mimeType != null) ? mimeType : "application/octet-stream";

return ResponseEntity.ok()
.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
.header(HttpHeaders.CONTENT_TYPE, mimeType)
.body(resource);
} else {
return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
}
} catch (MalformedURLException e) {
return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
} catch (IOException e) {
return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
}
}


@PostMapping("/addPost")
public void addPost(
@RequestParam("title") String title,
@RequestParam("category") String category,
@RequestParam("content") String content,
@RequestParam("userNo") String userNo,
@RequestParam("userLoc") String userLoc,
@RequestParam(value = "image", required = false) MultipartFile image) {

// PostVO 객체 생성 및 데이터 설정
PostVO vo = new PostVO();
vo.setUserNo(Integer.parseInt(userNo));
vo.setTitle(title);
vo.setContent(content);
vo.setLoc(userLoc);
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
postService.addPost(vo);
}

/// 포스팅 수정
@PostMapping("/updatePost")
public void updatePost(
@RequestParam("title") String title,
@RequestParam("category") String category,
@RequestParam("content") String content,
@RequestParam("postNo") String postNo,            
@RequestParam(value = "image", required = false) MultipartFile image,
@RequestParam(value = "imageUrl", required = false ) String imageUrl) {

// PostVO 객체 생성 및 데이터 설정
PostVO vo = new PostVO();
vo.setPostNo(Integer.parseInt(postNo));
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
postService.updatePost(vo);
}

/// 포스팅 삭제
@PostMapping("/deletePost")
public void deletePost(@RequestBody PostVO vo) {
System.out.println("deletePost 호출" + vo);
postService.deletePost(vo);
}

	
	
}
