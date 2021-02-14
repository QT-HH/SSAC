package com.ssac.team.service;

import java.util.HashMap;
import java.util.List;

import com.ssac.team.dto.MyTeam;
import com.ssac.team.dto.Team;

public interface TeamService {

	List<Team> listTeam() throws Exception;

	Team getTeam(int no) throws Exception;

	List<MyTeam> listMyTeam(String userid) throws Exception;

	int writeMyTeam(MyTeam myteam) throws Exception;

	int modifyMyTeam(MyTeam myteam) throws Exception;

	int removeMyTeam(MyTeam myteam) throws Exception;

	int modifyTeamCount(HashMap<String, Integer> map) throws Exception;

	List<Team> listFootballTeam(HashMap<String, String> map) throws Exception;

	List<Team> listBaseballTeam(HashMap<String, String> map) throws Exception;

	List<Team> listLOLTeam(HashMap<String, String> map) throws Exception;
}