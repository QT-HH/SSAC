package com.ssac.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssac.team.dto.Team;

@Mapper
public interface TeamMapper {
	public List<Team> selectTeam() throws Exception;
}
