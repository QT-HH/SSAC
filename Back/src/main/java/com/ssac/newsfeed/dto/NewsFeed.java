package com.ssac.newsfeed.dto;

import lombok.Data;

@Data
public class NewsFeed {
	private int no;
	private String id;
	private String content;
	private String regtime;
	private String imagename;
	
	public NewsFeed(String id, String content, String imagename) {
		this.id = id;
		this.content = content;
		this.imagename = imagename;
	}
}
