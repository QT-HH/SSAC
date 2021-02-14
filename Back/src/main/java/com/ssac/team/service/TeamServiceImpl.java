package com.ssac.team.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssac.mappers.TeamMapper;
import com.ssac.team.dto.MyTeam;
import com.ssac.team.dto.Team;

@Service
public class TeamServiceImpl implements TeamService {
	@Autowired
	private TeamMapper mapper;
	
	@Override
	public List<Team> listTeam() throws Exception {
		return mapper.selectTeam();
	}
	@Override
	public Team getTeam(int no) throws Exception {
		return mapper.selectTeamByNo(no);
	}
	@Override
	public List<MyTeam> listMyTeam(String userid) throws Exception {
		return mapper.selectMyTeam(userid);
	}
	@Override
	public int writeMyTeam(MyTeam myteam) throws Exception {
		return mapper.insertMyTeam(myteam);
	}
	@Override
	public int modifyMyTeam(MyTeam myteam) throws Exception {
		return mapper.updateMyTeamName(myteam);
	}
	@Override
	public int removeMyTeam(MyTeam myteam) throws Exception {
		return mapper.deleteMyTeam(myteam);
	}
	@Override
	public int modifyTeamCount(HashMap<String, Integer> map) throws Exception {
		return mapper.updateTeamCount(map);
	}
	@Override
	public List<Team> listFootballTeam(HashMap<String, String> map) throws Exception {
		return mapper.selectFootballteamBySearch(map);
	}
	@Override
	public List<Team> listBaseballTeam(HashMap<String, String> map) throws Exception {
		return mapper.selectBaseballteamBySearch(map);
	}
	@Override
	public List<Team> listLOLTeam(HashMap<String, String> map) throws Exception {
		return mapper.selectLOLteamBySearch(map);
	}
}
