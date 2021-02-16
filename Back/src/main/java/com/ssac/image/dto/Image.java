package com.ssac.image.dto;

import lombok.Data;

@Data
public class Image {
	private int no;
	private String filename;
	private byte[] image;
	
	public Image(String filename, byte[] image) {
		this.filename = filename;
		this.image = image;
	}
}
