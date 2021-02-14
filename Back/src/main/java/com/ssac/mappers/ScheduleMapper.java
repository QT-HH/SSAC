package com.ssac.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssac.schedule.dto.Schedule;

@Mapper
public interface ScheduleMapper {
	public List<Schedule> selectSchedule() throws Exception;
	public List<String> selectToday(String time) throws Exception;
}
