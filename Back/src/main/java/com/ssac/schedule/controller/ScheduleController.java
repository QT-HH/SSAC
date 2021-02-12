package com.ssac.schedule.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/schedule")
public class ScheduleController {
	
	@ApiOperation(value = "경기일정 조회", notes = "입력 : userid")
	@GetMapping("/schedule")
	public ResponseEntity<?> getScheduleList(@RequestParam String userid) throws Exception {
		// 경기 일정 조회
		// 입력 : userid
		// 출력 : 경기 일정
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> resultMap1 = new HashMap<>();
		resultMap1.put("no", "1");
		List<String> team = new ArrayList<String>();
		team.add("대구 FC");
		team.add("성남 FC");
		resultMap1.put("team", team);
		resultMap1.put("event_no", "1");
		resultMap1.put("match_date", "2021-02-16 13:00:00");
		list.add(resultMap1);
		Map<String, Object> resultMap2 = new HashMap<>();
		resultMap2.put("no", "2");
		team = new ArrayList<String>();
		team.add("울산 현대");
		team.add("포항 스틸러스");
		resultMap2.put("team", team);
		resultMap2.put("event_no", "1");
		resultMap2.put("match_date", "2021-02-18 15:00:00");
		list.add(resultMap2);
		Map<String, Object> resultMap3 = new HashMap<>();
		resultMap3.put("no", "3");
		team = new ArrayList<String>();
		team.add("두산 베어스");
		team.add("롯데 자이언츠");
		resultMap3.put("team", team);
		resultMap3.put("event_no", "2");
		resultMap3.put("match_date", "2021-02-17 19:00:00");
		list.add(resultMap3);
		Map<String, Object> resultMap4 = new HashMap<>();
		resultMap4.put("no", "4");
		team = new ArrayList<String>();
		team.add("삼성 라이온즈");
		team.add("키움 히어로즈");
		resultMap4.put("team", team);
		resultMap4.put("event_no", "2");
		resultMap4.put("match_date", "2021-02-19 18:00:00");
		list.add(resultMap4);
		Map<String, Object> resultMap5 = new HashMap<>();
		resultMap5.put("no", "5");
		team = new ArrayList<String>();
		team.add("DWG KIA");
		team.add("DRX");
		resultMap5.put("team", team);
		resultMap5.put("event_no", "3");
		resultMap5.put("match_date", "2021-02-22 19:00:00");
		list.add(resultMap5);
		Map<String, Object> resultMap6 = new HashMap<>();
		resultMap6.put("no", "6");
		team = new ArrayList<String>();
		team.add("T1");
		team.add("kt Rolster");
		resultMap6.put("team", team);
		resultMap6.put("event_no", "3");
		resultMap6.put("match_date", "2021-02-23 20:00:00");
		list.add(resultMap6);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}
