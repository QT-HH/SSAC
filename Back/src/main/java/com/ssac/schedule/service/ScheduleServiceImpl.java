package com.ssac.schedule.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssac.mappers.ScheduleMapper;
import com.ssac.schedule.dto.Schedule;

@Service
public class ScheduleServiceImpl implements ScheduleService {
	@Autowired
	private ScheduleMapper mapper;
	
	@Override
	public List<Schedule> listSchedule() throws Exception {
		return mapper.selectSchedule();
	}
}
