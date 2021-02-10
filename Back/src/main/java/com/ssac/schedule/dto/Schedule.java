package com.ssac.schedule.dto;

import lombok.Data;

@Data
public class Schedule {
	private int no;
	private String team1;
	private String team2;
	private int event_no;
	private String start;
	private int result1;
	private int result2;
	private boolean done;
}
