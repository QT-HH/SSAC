package com.websocket.chat.service;

import java.util.HashMap;
import java.util.List;

import com.websocket.chat.model.ChatMessage;
import com.websocket.chat.model.ChatRoom;

public interface ChatService {

	int createChatRoom(ChatRoom chatroom) throws Exception;

	int createChatUser(HashMap<String, String> map) throws Exception;

	List<String> getChatUser(String roomId) throws Exception;

	int outChatRoom(HashMap<String, String> map) throws Exception;

	List<ChatRoom> getChatRoom(String userId) throws Exception;

	int chatting(ChatMessage chatmessage) throws Exception;

	List<ChatMessage> getChatMessage(HashMap<String, String> map) throws Exception;

	String getEnterTime(HashMap<String, String> map) throws Exception;

	ChatRoom getChatRoomByRoomId(String roomId) throws Exception;

}