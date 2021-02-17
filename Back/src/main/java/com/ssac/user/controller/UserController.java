package com.ssac.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

import com.ssac.image.dto.Image;
import com.ssac.image.service.ImageService;
import com.ssac.team.dto.MyTeam;
import com.ssac.team.service.TeamService;
import com.ssac.user.dto.User;
import com.ssac.user.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/user")
@Api("SSAC")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private TeamService teamService;
	@Autowired
	private ImageService imageService;
	
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
			System.out.println(user.getPw());
			System.out.println(user.getNickname());
			User check = userService.findUser(new User(user.getId()));
			if(check == null) {
				// 회원가입 먼저
				if(userService.createUser(user) > 0) {
					// 마이팀 추가
					List<Integer> teams = (List<Integer>) jsonObj.get("userteam");
					System.out.println("teams size " + teams.size());
					for(int i=0; i<teams.size(); i++) {
						MyTeam myteam = new MyTeam();
						myteam.setId(user.getId());
						myteam.setTeam_no(teams.get(i));
						myteam.setName(teamService.getTeam(teams.get(i)).getName());
						// 마이팀 추가가 성공하면
						if(teamService.writeMyTeam(myteam) > 0) {
							HashMap<String, Integer> map = new HashMap<String, Integer>();
							map.put("no", myteam.getTeam_no());
							map.put("count", 1);
							// 팔로우수 증가
							if(teamService.modifyTeamCount(map) <= 0)
								return new ResponseEntity<String>("팔로우 수 증가 실패", HttpStatus.NO_CONTENT);
						} else return new ResponseEntity<String>("마이팀 추가 실패", HttpStatus.NO_CONTENT);
					}
					return new ResponseEntity<String>("success", HttpStatus.OK);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "유저 정보 조회", notes = "입력 : userid")
	@GetMapping("/userSelect")
	public ResponseEntity<?> selectUser(@RequestParam String userid) throws Exception {
		// 유저 정보 조회
		// 입력 : userid
		// 출력 : 유저 정보
		try {
			User user = userService.findUser(new User(userid));
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("userid", user.getId());
			resultMap.put("usernickname", user.getNickname());
			resultMap.put("point", user.getPoint());
//			Image image = imageService.profileFilenameToBlob(user.getProfile());
//			resultMap.put("profile", image.getBlob());
			List<String> following = userService.getFollowingList(user.getId());
			List<String> follower = userService.getFollowerList(user.getId());
			resultMap.put("following", following);
			resultMap.put("follower", follower);
			List<MyTeam> myteams = teamService.listMyTeam(user.getId());
			List<Integer> myteam = new ArrayList<Integer>();
			for(int i=0; i<myteams.size(); i++) myteam.add(myteams.get(i).getTeam_no());
			resultMap.put("myteam", myteam);
			System.out.println("유저정보 조회 : "+userid);
			return new ResponseEntity<>(resultMap, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "유저 닉네임 수정", notes = "입력 : userid, userpw, 변경할닉네임(newnickname)")
	@PatchMapping("/userNickNameChange")
	public ResponseEntity<?> changeUserNickName(@RequestBody String js) throws Exception {
		// 유저 닉네임 수정
		// 입력 : userid, userpw, 변경할 닉네임
		// 출력 : 성공, 실패
		JSONParser jsonParse = new JSONParser();
		JSONObject jsonObj = null;
		try {
			jsonObj = (JSONObject) jsonParse.parse(js);
			User check = new User();
			check.setId((String)jsonObj.get("userid"));
			User user = userService.findUser(check);
			System.out.println("유저 닉네임 수정 : "+user.getId());
			if(user.getPw().equals((String)jsonObj.get("userpw"))) {
				user.setNickname((String)jsonObj.get("newnickname"));
				if(userService.modifyUserNickname(user) > 0)
					return new ResponseEntity<String>("success", HttpStatus.OK);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "유저 비밀번호 수정", notes = "입력 : userid, userpw, 변경할 비밀번호(newpw)")
	@PatchMapping("/userPasswordChange")
	public ResponseEntity<?> changeUserPassword(@RequestBody String js) throws Exception {
		// 유저 비밀번호 수정
		// 입력 : userid, userpw, 변경할 비밀번호
		// 출력 : 성공, 실패
		JSONParser jsonParse = new JSONParser();
		JSONObject jsonObj = null;
		try {
			jsonObj = (JSONObject) jsonParse.parse(js);
			User check = new User();
			check.setId((String)jsonObj.get("userid"));
			User user = userService.findUser(check);
			System.out.println("유저 비밀번호 수정 : "+user.getId());
			if(user.getPw().equals((String)jsonObj.get("userpw"))) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("userid", user.getId());
				map.put("userpw", (String)jsonObj.get("nwepw"));
				if(userService.modifyUserPassword(map) > 0)
					return new ResponseEntity<String>("success", HttpStatus.OK);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "유저 탈퇴", notes = "입력 : userid, userpw")
	@DeleteMapping("/userDelete")
	public ResponseEntity<?> deleteUser(@RequestBody String js) throws Exception {
		// 유저 탈퇴
		// 입력 : userid, userpw
		// 출력 : 성공, 실패
		JSONParser jsonParse = new JSONParser();
		JSONObject jsonObj = null;
		try {
			jsonObj = (JSONObject) jsonParse.parse(js);
			User check = new User();
			check.setId((String)jsonObj.get("userid"));
			User user = userService.findUser(check);
			System.out.println("유저탈퇴 : "+user.getId());
			if(user.getPw().equals((String)jsonObj.get("userpw"))) {
				if(userService.removeUser(user) > 0)
					return new ResponseEntity<String>("success", HttpStatus.OK);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "팔로잉", notes = "입력 : userid, 팔로잉한 아이디(follow_id)")
	@PostMapping("/following")
	public ResponseEntity<?> following(@RequestBody String js) throws Exception {
		JSONParser jsonParse = new JSONParser();
		JSONObject jsonObj = null;
		try {
			jsonObj = (JSONObject) jsonParse.parse(js);
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("userid", (String)jsonObj.get("userid"));
			map.put("follow_id", (String)jsonObj.get("follow_id"));
			System.out.println("팔로우 : "+map.get("userid")+" "+map.get("follow_id"));
			if(userService.doFollowing(map) > 0)
				return new ResponseEntity<String>("success", HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
}
