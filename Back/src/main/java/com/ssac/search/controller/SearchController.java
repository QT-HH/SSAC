package com.ssac.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssac.team.service.TeamService;
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
	
	@ApiOperation(value = "친구, 팀 검색", notes = "입력 : ")
	@GetMapping("/search")
	public ResponseEntity<?> getScheduleList() throws Exception {
		
		return null;
	}
}
