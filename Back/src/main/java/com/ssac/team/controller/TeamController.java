package com.ssac.team.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssac.team.dto.Team;
import com.ssac.team.service.TeamService;

import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/team")
@Api(value="SSAC")
public class TeamController {
	@Autowired
	private TeamService teamService;
	
	@GetMapping("/list")
	public ResponseEntity<?> getListTeam() throws Exception {
		try {
			List<Team> list = teamService.listTeam();
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
