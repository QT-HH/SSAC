package com.ssac.team.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssac.team.service.TeamService;

import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "http://i4d102.p.ssafy.io:8080")
@RequestMapping("/team")
@Api("SSAC")
public class TeamController {
	@Autowired
	private TeamService teamService;
	
	@GetMapping("/list")
	public ResponseEntity<?> getListTeam() throws Exception {
		try {
			System.out.println("list 진입");
			return new ResponseEntity<>(teamService.listTeam(), HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
}
