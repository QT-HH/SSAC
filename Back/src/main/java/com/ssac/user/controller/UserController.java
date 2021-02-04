package com.ssac.user.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssac.user.dto.User;
import com.ssac.user.service.UserService;

@RestController
@CrossOrigin(origins = "http://i4d102.p.ssafy.io:8080")
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping
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
}
