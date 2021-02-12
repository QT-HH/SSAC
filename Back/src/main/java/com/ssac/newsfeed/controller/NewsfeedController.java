package com.ssac.newsfeed.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/newsfeed")
public class NewsfeedController {

	@ApiOperation(value = "뉴스피드 리스트 조회", notes = "입력 : userid")
	@GetMapping("/newsFeedList")
	public ResponseEntity<?> getAllNewsfeedList(@RequestParam String userid) throws Exception {
		// 뉴스피드 불러오기 (내가쓴글 + 내 팔로워가 쓴 글)
		// 입력 : userid
		// 출력 : 뉴스피드 게시글 (개수는 협의)
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> resultMap1 = new HashMap<>();
		resultMap1.put("no", "1");
		resultMap1.put("id", "aaa@aaa.com");
		resultMap1.put("content", "1번게시글입니다");
		resultMap1.put("regtime", "2021-02-09 04:41:09");
		resultMap1.put("like", "2");
		list.add(resultMap1);
		Map<String, Object> resultMap2 = new HashMap<>();
		resultMap2.put("no", "2");
		resultMap2.put("id", "aaa@aaa.com");
		resultMap2.put("content", "2번게시글입니다");
		resultMap2.put("regtime", "2021-02-09 05:41:09");
		resultMap2.put("like", "2");
		list.add(resultMap2);
		Map<String, Object> resultMap3 = new HashMap<>();
		resultMap3.put("no", "3");
		resultMap3.put("id", "aaa@aaa.com");
		resultMap3.put("content", "3번게시글입니다");
		resultMap3.put("regtime", "2021-02-09 06:41:09");
		resultMap3.put("like", "2");
		list.add(resultMap3);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "뉴스피드 작성", notes = "입력 : userid, 내용(content)")
	@PostMapping("/newsFeedWrite")
	public ResponseEntity<?> writeNewsfeed(@RequestBody String js) throws Exception {
		// 뉴스피드 작성하기
		// 입력 : userid, content
		// 출력 : 성공, 실패
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@ApiOperation(value = "뉴스피드 수정", notes = "입력 : 게시글번호(no), 변경내용(content)")
	@PatchMapping("/newsFeedUpdate")
	public ResponseEntity<?> updateNewsfeed(@RequestBody String js) throws Exception {
		// 뉴스피드 수정하기
		// 입력 : no, content
		// 출력 : 성공, 실패
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@ApiOperation(value = "뉴스피드 삭제", notes = "입력 : 게시글번호(no)")
	@DeleteMapping("/newsFeedDelete")
	public ResponseEntity<?> deleteNewsfeed(@RequestParam String no) throws Exception {
		// 뉴스피드 삭제하기
		// 입력 : no
		// 출력 : 성공, 실패
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	//////////////////// like ///////////////////////
	
	@ApiOperation(value = "뉴스피드 좋아요 리스트 조회", notes = "입력 : 게시글번호(no)")
	@GetMapping("/likeList")
	public ResponseEntity<?> getLikeList(@RequestParam String no) throws Exception {
		// 좋아요한 사람 목록 불러오기
		// 입력 : 게시글 번호
		// 출력 : 좋아요한 사람 목록
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> resultMap1 = new HashMap<>();
		resultMap1.put("no", "1");
		resultMap1.put("feed_no", no);
		resultMap1.put("like_id", "leegw215@naver.com");
		resultMap1.put("nickname", "이지원");
		resultMap1.put("regtime", "2021-02-09 14:51:09");
		list.add(resultMap1);
		Map<String, Object> resultMap2 = new HashMap<>();
		resultMap2.put("no", "12");
		resultMap2.put("feed_no", no);
		resultMap2.put("like_id", "leegw215@naver.com");
		resultMap2.put("nickname", "이지원");
		resultMap2.put("regtime", "2021-02-09 15:51:09");
		list.add(resultMap2);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "뉴스피드 좋아요 누르기", notes = "입력 : userid, nickname, 게시글번호(no)")
	@PostMapping("/likeWrite")
	public ResponseEntity<?> writeLike(@RequestBody String js) throws Exception {
		// 좋아요 누르기
		// 입력 : 게시글 번호, userid, 닉네임
		// 출력 : 성공, 실패
		return new ResponseEntity<String>("success", HttpStatus.OK); 
	}
	
	@ApiOperation(value = "뉴스피드 좋아요 취소", notes = "입력 : userid, 게시글번호(no)")
	@DeleteMapping("/likeDelete")
	public ResponseEntity<?> deleteLike(@RequestBody String js) throws Exception {
		// 좋아요 취소
		// 입력 : 게시글 번호, userid
		// 출력 : 성공, 실패
		return new ResponseEntity<String>("success", HttpStatus.OK); 
	}
}
