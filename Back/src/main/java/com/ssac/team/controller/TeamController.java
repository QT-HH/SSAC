package com.ssac.team.controller;

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

import com.ssac.team.dto.MyTeam;
import com.ssac.team.service.TeamService;
import com.ssac.user.dto.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "http://i4d102.p.ssafy.io:8080")
@RequestMapping("/team")
public class TeamController {
	@Autowired
	private TeamService teamService;
	
	@ApiOperation(value = "전체 팀 리스트 조회", notes = "입력값 없음")
	@GetMapping("/allTeamList")
	public ResponseEntity<?> getAllTeamList() throws Exception {
		try {
			System.out.println("list 진입");
			return new ResponseEntity<>(teamService.listTeam(), HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation(value = "마이 팀 리스트 조회", notes = "입력 : userid")
	@GetMapping("/myTeamList")
	public ResponseEntity<?> getMyTeamList(@RequestParam String userid) throws Exception {
		// 내팀 정보 불러오기
		// 입력 : userid
		// 출력 : 나의 팀
//		JSONParser jsonParse = new JSONParser();
//		JSONObject jsonObj = null;
//		try {
//			jsonObj = (JSONObject) jsonParse.parse(js);
//			MyTeam myteam = new MyTeam();
//			String id = (String) jsonObj.get("userid");
//			// 짜는 중 
//			
//			return new ResponseEntity<String>("success", HttpStatus.OK);
//		} catch(Exception e) {
//			System.out.println("JSON 파싱 실패");
//		}
//
//		return new ResponseEntity<String>("회원가입실패", HttpStatus.NO_CONTENT);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> resultMap1 = new HashMap<>();
		resultMap1.put("no", "1");
		resultMap1.put("id", "aaa@aaa.com");
		resultMap1.put("team_no", "3");
		resultMap1.put("name", "대구 FC");
		resultMap1.put("event_no", "1");
		resultMap1.put("logo", "src");
		resultMap1.put("count", "1000");
		list.add(resultMap1);
		Map<String, Object> resultMap2 = new HashMap<>();
		resultMap2.put("no", "4");
		resultMap2.put("id", "aaa@aaa.com");
		resultMap2.put("team_no", "25");
		resultMap2.put("name", "삼성 라이온즈");
		resultMap2.put("event_no", "2");
		resultMap2.put("logo", "src");
		resultMap2.put("count", "2000");
		list.add(resultMap2);
		Map<String, Object> resultMap3 = new HashMap<>();
		resultMap3.put("no", "6");
		resultMap3.put("id", "aaa@aaa.com");
		resultMap3.put("team_no", "37");
		resultMap3.put("name", "T1");
		resultMap3.put("event_no", "3");
		resultMap3.put("logo", "src");
		resultMap3.put("count", "5000");
		list.add(resultMap3);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "마이 팀 추가", notes = "입력 : userid, 팀고유번호(team_no)")
	@PostMapping("/myTeamInsert")
	public ResponseEntity<?> insertMyTeam(@RequestBody String js) throws Exception {
		// 내팀 추가
		// 입력값 : userid, 팀고유번호(team_no)
		// 출력값 : 성공, 실패
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@ApiOperation(value = "마이 팀 이름 수정", notes = "입럭 : userid, 내팀번호(no), 바꿀이름")
	@PatchMapping("/myTeamNameChange")
	public ResponseEntity<?> changeMyTeamName(@RequestBody String js) throws Exception {
		// 팀 이름 변경하기
		// 입력값 : userid, 내팀번호(no), 바꿀이름
		// 출력값 : 성공, 실패
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@ApiOperation(value = "마이 팀 삭제", notes = "userid, 내팀번호(no)")
	@DeleteMapping("/myTeamDelete")
	public ResponseEntity<?> deleteMyTeam(@RequestBody String js) throws Exception {
		// 나의 팀 삭제
		// 입력값 : userid, 내팀번호(no)
		// 출력값 : 성공, 실패
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@ApiOperation(value = "축구 팀 검색 및 조회", notes = "userid, 검색내용")
	@GetMapping("/footballList")
	public ResponseEntity<?> getFootBallTeam(@RequestParam String userid, @RequestParam(value="search",defaultValue="") String search) throws Exception {
		// 축구팀 검색
		// 입력값 : userid, 검색내용
		// 출력값 : 나의 팀을 제외한 축구 팀 중 검색내용에 해당하는 팀
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> resultMap1 = new HashMap<>();
		resultMap1.put("no", "1");
		resultMap1.put("event_no", "1");
		resultMap1.put("name", "강원 FC");
		resultMap1.put("logo", "src");
		resultMap1.put("count", "1000");
		list.add(resultMap1);
		Map<String, Object> resultMap2 = new HashMap<>();
		resultMap2.put("no", "2");
		resultMap2.put("event_no", "1");
		resultMap2.put("name", "광주 FC");
		resultMap2.put("logo", "src");
		resultMap2.put("count", "2000");
		list.add(resultMap2);
		Map<String, Object> resultMap3 = new HashMap<>();
		resultMap3.put("no", "4");
		resultMap3.put("event_no", "1");
		resultMap3.put("name", "FC 서울");
		resultMap3.put("logo", "src");
		resultMap3.put("count", "5000");
		list.add(resultMap3);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "야구 팀 검색 및 조회", notes = "userid, 검색내용")
	@GetMapping("/baseballList")
	public ResponseEntity<?> getBaseBallTeam(@RequestParam String userid, @RequestParam(value="search",defaultValue="") String search) throws Exception {
		// 야구팀 검색
		// 입력값 : userid, 검색내용
		// 출력값 : 나의 팀을 제외한 야구 팀 중 검색내용에 해당하는 팀
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> resultMap1 = new HashMap<>();
		resultMap1.put("no", "23");
		resultMap1.put("event_no", "2");
		resultMap1.put("name", "두산 베어스");
		resultMap1.put("logo", "src");
		resultMap1.put("count", "3000");
		list.add(resultMap1);
		Map<String, Object> resultMap2 = new HashMap<>();
		resultMap2.put("no", "24");
		resultMap2.put("event_no", "2");
		resultMap2.put("name", "롯데 자이언츠");
		resultMap2.put("logo", "src");
		resultMap2.put("count", "5000");
		list.add(resultMap2);
		Map<String, Object> resultMap3 = new HashMap<>();
		resultMap3.put("no", "26");
		resultMap3.put("event_no", "2");
		resultMap3.put("name", "키움 히어로즈");
		resultMap3.put("logo", "src");
		resultMap3.put("count", "2000");
		list.add(resultMap3);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "롤 팀 검색 및 조회", notes = "userid, 검색내용")
	@GetMapping("/lolList")
	public ResponseEntity<?> getLOLTeam(@RequestParam String userid, @RequestParam(value="search",defaultValue="") String search) throws Exception {
		// 롤팀 검색
		// 입력값 : userid, 검색내용
		// 출력값 : 나의 팀을 제외한 롤 팀 중 검색내용에 해당하는 팀
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> resultMap1 = new HashMap<>();
		resultMap1.put("no", "33");
		resultMap1.put("event_no", "3");
		resultMap1.put("name", "DWG KIA");
		resultMap1.put("logo", "src");
		resultMap1.put("count", "5000");
		list.add(resultMap1);
		Map<String, Object> resultMap2 = new HashMap<>();
		resultMap2.put("no", "34");
		resultMap2.put("event_no", "3");
		resultMap2.put("name", "DRX");
		resultMap2.put("logo", "src");
		resultMap2.put("count", "3000");
		list.add(resultMap2);
		Map<String, Object> resultMap3 = new HashMap<>();
		resultMap3.put("no", "35");
		resultMap3.put("event_no", "3");
		resultMap3.put("name", "Gen.G Esports");
		resultMap3.put("logo", "src");
		resultMap3.put("count", "4000");
		list.add(resultMap3);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}
