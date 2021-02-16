package com.ssac.newsfeed.dto;

import lombok.Data;

@Data
public class NewsFeedLike {
	private int no;
	private int feed_no;
	private String like_id;
	private String regtime;
	
	public NewsFeedLike(int feed_no, String like_id) {
		this.feed_no = feed_no;
		this.like_id = like_id;
	}
}
