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
@RequestMapping("/newsfeedcomment")
public class NewsfeedCommentController {
	
	@ApiOperation(value = "뉴스피드 댓글 조회", notes = "입력 : 게시글번호(no)")
	@GetMapping("/commentList")
	public ResponseEntity<?> getAllCommentList(@RequestParam String no) throws Exception {
		// 뉴스피드 댓글 불러오기
		// 입력 : 게시글 번호
		// 출력 : 뉴스피드 댓글
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> resultMap1 = new HashMap<>();
		resultMap1.put("no", "1");
		resultMap1.put("id", "leegw215@naver.com");
		resultMap1.put("nickname", "이지원");
		resultMap1.put("content", "1번 댓글입니다");
		resultMap1.put("regtime", "2021-02-09 05:41:09");
		list.add(resultMap1);
		Map<String, Object> resultMap2 = new HashMap<>();
		resultMap2.put("no", "2");
		resultMap2.put("id", "leegw215@naver.com");
		resultMap2.put("nickname", "이지원");
		resultMap2.put("content", "2번 댓글입니다");
		resultMap2.put("regtime", "2021-02-09 06:41:09");
		list.add(resultMap2);
		Map<String, Object> resultMap3 = new HashMap<>();
		resultMap3.put("no", "3");
		resultMap3.put("id", "leegw215@naver.com");
		resultMap3.put("nickname", "이지원");
		resultMap3.put("content", "3번 댓글입니다");
		resultMap3.put("regtime", "2021-02-09 07:41:09");
		list.add(resultMap3);
		return new ResponseEntity<>(list, HttpStatus.OK);		
	}
	
	@ApiOperation(value = "뉴스피드 댓글 작성", notes = "입력 : userid, 내용(comment)")
	@PostMapping("/commentWrite")
	public ResponseEntity<?> writeComment(@RequestBody String js) throws Exception {
		// 뉴스피드 댓글 작성하기
		// 입력 : userid, comment
		// 출력 : 성공, 실패
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@ApiOperation(value = "뉴스피드 댓글 수정", notes = "입력 : 댓글번호(no), 내용(comment)")
	@PatchMapping("/commentUpdate")
	public ResponseEntity<?> updateComment(@RequestBody String js) throws Exception {
		// 뉴스피드 댓글 수정하기
		// 입력 : 댓글번호, 수정내용
		// 출력 : 성공, 실패
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@ApiOperation(value = "뉴스피드 댓글 삭제", notes = "입력 : 댓글번호(no)")
	@DeleteMapping("/commentDelete")
	public ResponseEntity<?> deleteComment(@RequestParam String no) throws Exception {
		// 뉴스피드 댓글 삭제하기
		// 입력 : 댓글번호
		// 출력 : 성공, 실패
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
}
