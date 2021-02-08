package com.ssac.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.ssac.user.dto.User;
import com.ssac.user.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "http://i4d102.p.ssafy.io:8080")
@RequestMapping("/user")
@Api("SSAC")
public class UserController {
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "회원가입", notes = "입력 : userid, userpw, usernickname, userteam")
	@PostMapping("/signup")
	public ResponseEntity<String> joinUser(@RequestBody String js) throws Exception {
		JSONParser jsonParse = new JSONParser();
		JSONObject jsonObj = null;
		try {
			jsonObj = (JSONObject) jsonParse.parse(js);
			User user = new User();
			user.setId((String) jsonObj.get("userid"));
			user.setPw((String) jsonObj.get("userpw"));
			user.setNickname((String) jsonObj.get("usernickname"));
			System.out.println(user.getId());
			System.out.println(user.getNickname());
			if(userService.createUser(user) > 0)
				return new ResponseEntity<String>("success", HttpStatus.OK);
		} catch(Exception e) {
			System.out.println("JSON 파싱 실패");
		}

		return new ResponseEntity<String>("회원가입실패", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "유저 정보 조회", notes = "입력 : userid")
	@GetMapping("/userSelect")
	public ResponseEntity<?> selectUser(@RequestParam String userid) throws Exception {
		// 유저 정보 조회
		// 입력 : userid
		// 출력 : 유저 정보
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("userid", "aaa@aaa.com");
		resultMap.put("usernickname", "이지원");
		resultMap.put("point", "0");
		resultMap.put("following", "30");
		resultMap.put("follower", "30");
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}
	
	@ApiOperation(value = "유저 닉네임 수정", notes = "입력 : userid, userpw, 변경할 닉네임")
	@PatchMapping("/userNickNameChange")
	public ResponseEntity<?> changeUserNickName(@RequestBody String js) throws Exception {
		// 유저 닉네임 수정
		// 입력 : userid, userpw, 변경할 닉네임
		// 출력 : 성공, 실패
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@ApiOperation(value = "유저 비밀번호 수정", notes = "입력 : userid, userpw, 변경할 비밀번호")
	@PatchMapping("/userPasswordChange")
	public ResponseEntity<?> changeUserPassword(@RequestBody String js) throws Exception {
		// 유저 비밀번호 수정
		// 입력 : userid, userpw, 변경할 비밀번호
		// 출력 : 성공, 실패
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@ApiOperation(value = "유저 닉네임 수정", notes = "입력 : userid, userpw")
	@DeleteMapping("/userDelete")
	public ResponseEntity<?> deleteUser(@RequestParam String userid) throws Exception {
		// 유저 탈퇴
		// 입력 : userid, userpw
		// 출력 : 성공, 실패
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
}
