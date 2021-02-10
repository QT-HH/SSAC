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
import com.ssac.team.dto.Team;
import com.ssac.team.service.TeamService;
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
		List<MyTeam> myteams = teamService.listMyTeam(userid);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		for(int i=0; i<myteams.size(); i++) {
			int no = myteams.get(i).getNo();
			Team team = teamService.getTeam(no);
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("no", no);
			resultMap.put("id", myteams.get(i).getId());
			resultMap.put("team_no", myteams.get(i).getTeam_no());
			resultMap.put("name", myteams.get(i).getName());
			resultMap.put("event_no", team.getEvent_no());
			resultMap.put("logo", team.getLogo());
			resultMap.put("count", team.getCount());
			list.add(resultMap);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "마이 팀 추가", notes = "입력 : userid, 팀고유번호(team_no)")
	@PostMapping("/myTeamInsert")
	public ResponseEntity<?> insertMyTeam(@RequestBody String js) throws Exception {
		// 내팀 추가
		// 입력값 : userid, 팀고유번호(team_no)
		// 출력값 : 성공, 실패
		JSONParser jsonParse = new JSONParser();
		JSONObject jsonObj = null;
		try {
			jsonObj = (JSONObject) jsonParse.parse(js);
			MyTeam myteam = new MyTeam();
			myteam.setId((String) jsonObj.get("userid"));
			myteam.setTeam_no((int) jsonObj.get("team_no"));
			myteam.setName(teamService.getTeam(myteam.getTeam_no()).getName());
			if(teamService.writeMyTeam(myteam) > 0) {
				HashMap<String, Integer> map = new HashMap<String, Integer>();
				map.put("no", myteam.getTeam_no());
				map.put("count", 1);
				if(teamService.modifyTeamCount(map) > 0)
					return new ResponseEntity<String>("success", HttpStatus.OK);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "마이 팀 이름 수정", notes = "입럭 : userid, 내팀번호(no), 바꿀이름(name)")
	@PatchMapping("/myTeamNameChange")
	public ResponseEntity<?> changeMyTeamName(@RequestBody String js) throws Exception {
		// 팀 이름 변경하기
		// 입력값 : userid, 내팀번호(no), 바꿀이름
		// 출력값 : 성공, 실패
		JSONParser jsonParse = new JSONParser();
		JSONObject jsonObj = null;
		try {
			jsonObj = (JSONObject) jsonParse.parse(js);
			MyTeam myteam = new MyTeam();
			myteam.setNo((int) jsonObj.get("no"));
			myteam.setId((String) jsonObj.get("userid"));
			myteam.setName((String) jsonObj.get("name"));
			if(teamService.modifyMyTeam(myteam) > 0)
				return new ResponseEntity<String>("success", HttpStatus.OK);				
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "마이 팀 삭제", notes = "userid, 내팀번호(no)")
	@DeleteMapping("/myTeamDelete")
	public ResponseEntity<?> deleteMyTeam(@RequestBody String js) throws Exception {
		// 나의 팀 삭제
		// 입력값 : userid, 내팀번호(no)
		// 출력값 : 성공, 실패
		JSONParser jsonParse = new JSONParser();
		JSONObject jsonObj = null;
		try {
			jsonObj = (JSONObject) jsonParse.parse(js);
			MyTeam myteam = new MyTeam();
			myteam.setId((String) jsonObj.get("userid"));
			myteam.setNo((int) jsonObj.get("no"));
			if(teamService.removeMyTeam(myteam) > 0) {
				HashMap<String, Integer> map = new HashMap<String, Integer>();
				map.put("no", myteam.getTeam_no());
				map.put("count", -1);
				if(teamService.modifyTeamCount(map) > 0)
					return new ResponseEntity<String>("success", HttpStatus.OK);
			}				
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "축구 팀 검색 및 조회", notes = "userid, 검색내용(search)")
	@GetMapping("/footballList")
	public ResponseEntity<?> getFootBallTeam(@RequestParam String userid, @RequestParam(value="search",defaultValue="") String search) throws Exception {
		// 축구팀 검색
		// 입력값 : userid, 검색내용
		// 출력값 : 나의 팀을 제외한 축구 팀 중 검색내용에 해당하는 팀
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("search", search);
		return new ResponseEntity<>(teamService.listFootballTeam(map), HttpStatus.OK);
	}
	
	@ApiOperation(value = "야구 팀 검색 및 조회", notes = "userid, 검색내용(search)")
	@GetMapping("/baseballList")
	public ResponseEntity<?> getBaseBallTeam(@RequestParam String userid, @RequestParam(value="search",defaultValue="") String search) throws Exception {
		// 야구팀 검색
		// 입력값 : userid, 검색내용
		// 출력값 : 나의 팀을 제외한 야구 팀 중 검색내용에 해당하는 팀
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("search", search);
		return new ResponseEntity<>(teamService.listBaseballTeam(map), HttpStatus.OK);
	}
	
	@ApiOperation(value = "롤 팀 검색 및 조회", notes = "userid, 검색내용(search)")
	@GetMapping("/lolList")
	public ResponseEntity<?> getLOLTeam(@RequestParam String userid, @RequestParam(value="search",defaultValue="") String search) throws Exception {
		// 롤팀 검색
		// 입력값 : userid, 검색내용
		// 출력값 : 나의 팀을 제외한 롤 팀 중 검색내용에 해당하는 팀
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("search", search);
		return new ResponseEntity<>(teamService.listLOLTeam(map), HttpStatus.OK);
	}
}
