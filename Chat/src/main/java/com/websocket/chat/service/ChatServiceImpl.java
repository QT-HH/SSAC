package com.websocket.chat.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websocket.chat.mappers.ChatMapper;
import com.websocket.chat.model.ChatMessage;
import com.websocket.chat.model.ChatRoom;

@Service
public class ChatServiceImpl implements ChatService {
	@Autowired
	private ChatMapper mapper;
	
	@Override
	public int createChatRoom(ChatRoom chatroom) throws Exception {
		return mapper.insertChatRoom(chatroom);
	}
	
	@Override
	public int createChatUser(HashMap<String, String> map) throws Exception {
		return mapper.insertChatUser(map);
	}
	
	@Override
	public List<String> getChatUser(String roomId) throws Exception {
		return mapper.selectChatUserByRoomId(roomId);
	}
	
	@Override
	public int outChatRoom(HashMap<String, String> map) throws Exception {
		return mapper.deleteChatUser(map);
	}
	
	@Override
	public List<ChatRoom> getChatRoom(String userId) throws Exception {
		return mapper.selectChatRoom(userId);
	}
	
	@Override
	public int chatting(ChatMessage chatmessage) throws Exception {
		return mapper.insertChatMessage(chatmessage);
	}
	
	@Override
	public List<ChatMessage> getChatMessage(HashMap<String, String> map) throws Exception {
		return mapper.selectChatMessage(map);
	}
	
	@Override
	public String getEnterTime(HashMap<String, String> map) throws Exception {
		return mapper.selectEnter(map);
	}
	
	@Override
	public ChatRoom getChatRoomByRoomId(String roomId) throws Exception {
		return mapper.selectChatRoomByRoomId(roomId);
	}
}
