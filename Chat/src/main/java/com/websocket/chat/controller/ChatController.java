package com.websocket.chat.controller;

import com.websocket.chat.model.ChatMessage;
import com.websocket.chat.service.ChatService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class ChatController {
	@Autowired
	private ChatService chatService;

    private final SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {
        try {
        	System.out.println("채팅 to server");
			chatService.chatting(message);
			System.out.println("채팅 to db");
		} catch (Exception e) {
			e.printStackTrace();
		}
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
        System.out.println("채팅 to client");
    }
}
