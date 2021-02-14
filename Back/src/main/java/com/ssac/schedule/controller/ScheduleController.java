package com.ssac.schedule.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssac.schedule.dto.Schedule;
import com.ssac.schedule.service.ScheduleService;
import com.ssac.user.dto.User;
import com.ssac.user.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/schedule")
public class ScheduleController {
	@Autowired
	private ScheduleService scheduleService;
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "경기일정 조회", notes = "입력 없어도됨")
	@GetMapping("/schedule")
	public ResponseEntity<?> getScheduleList() throws Exception {
		// 경기 일정 조회
		// 입력 : userid
		// 출력 : 경기 일정
		List<Schedule> list = scheduleService.listSchedule();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "당일 경기 제목, 팔로우 목록 불러오기", notes = "입력 : userid")
	@GetMapping("/chatList")
	public ResponseEntity<?> getTeamList(@RequestParam String userid) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date time = new Date();
		// String today = format.format(time);
		String today = "2021-02-17";
		List<String> todayGame = scheduleService.getTodayGame(today);
		List<String> following = userService.getFollowingList(userid);
		List<User> follow = new ArrayList<User>();
		System.out.println(today);
		for(int i=0; i<following.size(); i++) {
			User friend = userService.findUser(new User(following.get(i)));
			follow.add(friend);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("game", todayGame);
		map.put("follow", follow);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
