package com.websocket.chat.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ChatRoom {
	private String roomId;
	private String name;

	public ChatRoom(String name) {
		this.roomId = UUID.randomUUID().toString();
		this.name = name;
	}
}
