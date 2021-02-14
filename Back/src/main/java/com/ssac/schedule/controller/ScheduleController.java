package com.ssac.schedule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssac.schedule.dto.Schedule;
import com.ssac.schedule.service.ScheduleService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/schedule")
public class ScheduleController {
	@Autowired
	private ScheduleService scheduleService;
	
	@ApiOperation(value = "경기일정 조회", notes = "입력 없어도됨")
	@GetMapping("/schedule")
	public ResponseEntity<?> getScheduleList() throws Exception {
		// 경기 일정 조회
		// 입력 : userid
		// 출력 : 경기 일정
		List<Schedule> list = scheduleService.listSchedule();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}
