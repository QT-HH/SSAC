package com.ssac.newsfeed.dto;

import lombok.Data;

@Data
public class NewsFeed {
	private int no;
	private String id;
	private String content;
	private String regtime;
	private String imagename;
	
	public NewsFeed(int no, String content, String imagename) {
		this.no = no;
		this.content = content;
		this.imagename = imagename;
	}
}
