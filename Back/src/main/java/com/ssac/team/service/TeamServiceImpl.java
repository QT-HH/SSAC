package com.ssac.team.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssac.mappers.TeamMapper;
import com.ssac.team.dto.Team;

@Service
public class TeamServiceImpl implements TeamService {
	@Autowired
	private TeamMapper mapper;
	
	@Override
	public List<Team> listTeam() throws Exception {
		return mapper.selectTeam();
	}
}
