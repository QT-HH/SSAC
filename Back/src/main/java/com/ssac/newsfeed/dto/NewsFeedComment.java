package com.ssac.newsfeed.dto;

import lombok.Data;

@Data
public class NewsFeedComment {
	private int no;
	private int feed_no;
	private String writer;
	private String comment;
	private String regtime;
	
	public NewsFeedComment(int feed_no, String writer, String comment) {
		this.feed_no = feed_no;
		this.writer = writer;
		this.comment = comment;
	}
	
	public NewsFeedComment(int no, String comment) {
		this.no = no;
		this.comment = comment;
	}
}
