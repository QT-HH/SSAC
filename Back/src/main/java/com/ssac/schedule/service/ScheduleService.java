package com.ssac.schedule.service;

import java.util.List;

import com.ssac.schedule.dto.Schedule;

public interface ScheduleService {

	List<Schedule> listSchedule() throws Exception;

}