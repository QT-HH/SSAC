package com.ssac.search.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
		int size = temp.size()>5?5:temp.size();
		for(int i=0; i<size; i++) {
			Map<String, String> user = new HashMap<String, String>();
			user.put("id", temp.get(i).getId());
			user.put("nickname", temp.get(i).getNickname());
			user.put("profile", temp.get(i).getProfile());
			users.add(user);
		}
		System.out.println("검색 userid : "+userid+", search : "+search);
		HashMap<String, String> hmap = new HashMap<String, String>();
		hmap.put("userid", userid);
		hmap.put("search", search);
		List<Team> teams = teamService.searchTeam(hmap);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("users", users);
		result.put("teams", teams);
		result.put("searched", true);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "팀추천", notes = "입력 : 유저이메일(userid), 설문결과(surveyAnswers)")
	@GetMapping("/recommend")
	public ResponseEntity<?> getRecommend(@RequestParam String userid, 
			@RequestParam String surveyAnswers) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("search", "");
		System.out.println("팀추천 userid : "+userid+", 설문 : "+surveyAnswers+" "+surveyAnswers);
		Random random = new Random();
		random.setSeed(System.currentTimeMillis());
		int idx = 0;
		List<Team> teams = new ArrayList<Team>();
		if(surveyAnswers.equals("축구")) teams = teamService.listFootballTeam(map);
		else if(surveyAnswers.equals("야구")) teams = teamService.listBaseballTeam(map);
		else teams = teamService.listLOLTeam(map);
		idx = random.nextInt(teams.size()-1);
		return new ResponseEntity<>(teams.get(idx), HttpStatus.OK);
	}
	
	@ApiOperation(value = "친구의 팀 조회", notes = "입력 : 유저이메일(userid)")
	@GetMapping("/friteams")
	public ResponseEntity<?> getFriendTeams(@RequestParam String userid) throws Exception {
		List<HashMap<String, Object>> teams = teamService.getFriendTeams(userid);
		List<HashMap<String, Object>> result = new ArrayList<HashMap<String,Object>>();
		System.out.println("친구팀 조회 : "+userid);
		for(int i=0; i<teams.size(); i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			int no = (int) teams.get(i).get("team_no");
			String id = (String) teams.get(i).get("id");
			Team team = teamService.getTeam(no);
			User user = userService.findUser(new User(id));
			map.put("logo", team.getLogo());
			map.put("name", team.getName());
			map.put("no", no);
			map.put("friend_name", user.getNickname());
			map.put("people", (long) teams.get(i).get("count"));
			result.add(map);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
