package com.ssac.search.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssac.team.dto.Team;
import com.ssac.team.service.TeamService;
import com.ssac.user.dto.User;
import com.ssac.user.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/search")
public class SearchController {
	@Autowired
	private UserService userService;
	@Autowired
	private TeamService teamService;
	
	@ApiOperation(value = "친구, 팀 검색", notes = "입력 : 유저이메일(userid), 검색내용(search)")
	@GetMapping("/search")
	public ResponseEntity<?> getSearchResult(@RequestParam String userid, @RequestParam String search) throws Exception {
		List<User> temp = userService.getSearchUser(search);
		List<Map<String,String>> users = new ArrayList<Map<String,String>>();
		for(int i=0; i<temp.size(); i++) {
			Map<String, String> user = new HashMap<String, String>();
			user.put("id", temp.get(i).getId());
			user.put("nickname", temp.get(i).getNickname());
			user.put("profile", temp.get(i).getProfile());
			users.add(user);
		}
		
		System.out.println("userid : "+userid);
		System.out.println("search : "+search);
		HashMap<String, String> hmap = new HashMap<String, String>();
		hmap.put("userid", userid);
		hmap.put("search", search);
		List<Team> teams = new ArrayList<Team>();
		teams.addAll(teamService.listFootballTeam(hmap));
		teams.addAll(teamService.listBaseballTeam(hmap));
		teams.addAll(teamService.listLOLTeam(hmap));
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("users", users);
		result.put("teams", teams);
		result.put("searched", true);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "팀추천", notes = "입력 : 유저이메일(userid), 설문결과(surveyAnswers)")
	@GetMapping("/recommend")
	public ResponseEntity<?> getRecommend(@RequestParam String userid, 
			@RequestParam(value="surveyAnswers") String[] surveyAnswers) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("search", "");
		return null;
	}
}
