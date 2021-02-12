package com.websocket.chat.controller;

import com.websocket.chat.model.ChatMessage;
import com.websocket.chat.model.ChatRoom;
import com.websocket.chat.model.ChatUser;
import com.websocket.chat.service.ChatService;

import io.swagger.annotations.ApiOperation;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/chat")
public class ChatRoomController {
	@Autowired
	private ChatService chatService;

	@ApiOperation(value = "신경안써도됨", notes = "입력 : userid")
	@GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        try {
			return chatService.getChatRoomByRoomId(roomId);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return null;
    }
	
	@ApiOperation(value = "1. 채팅방 목록조회", notes = "입력 : userid")
    @GetMapping("/roomList")
    public ResponseEntity<?> room(@RequestParam String userid) throws Exception {
        // 채팅방 목록
    	// 입력 : userid
    	List<ChatRoom> rooms = chatService.getChatRoom(userid);
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }
	
	@ApiOperation(value = "2. 채팅방 생성(채팅할 사람 초대포함)", notes = "입력 : 채팅방이름(name), 채팅맴버 아이디 배열(ids), 채팅맴버 닉네임 배열(nicknames)")
    @PostMapping("/roomCreate")
    public ResponseEntity<?> createRoom(@RequestBody String js) throws Exception {
    	// 채팅방 만들기
    	// 입력 : 채팅방이름(name), 초대맴버리스트(ids)
    	JSONParser jsonParse = new JSONParser();
		JSONObject jsonObj = null;
		try {
			jsonObj = (JSONObject) jsonParse.parse(js);
			ChatRoom room = new ChatRoom((String) jsonObj.get("name"));
			if(chatService.createChatRoom(room) > 0) {
				List<String> ids = (List<String>) jsonObj.get("ids");
				List<String> nicknames = (List<String>) jsonObj.get("nicknames");
				for(int i=0; i<ids.size(); i++) {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("roomId", room.getRoomId());
					map.put("userId", ids.get(i));
					map.put("userName", nicknames.get(i));
					chatService.createChatUser(map);
				}
				return new ResponseEntity<>("success", HttpStatus.OK);
			}			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("fail", HttpStatus.NO_CONTENT);
    }
	
	@ApiOperation(value = "3. 채팅방입장(첫입장 이후 채팅내역, 참여인원 목록 불러오기)", notes = "입력 : userid, 채팅방번호(roomId)")
    @GetMapping("/roomEnter")
    public ResponseEntity<?> enterChatRoom(@RequestParam String roomId, @RequestParam String userid) throws Exception {
    	// 채팅 내역 불러오기
    	// 입력 : 채팅방번호(roomId), userid
    	HashMap<String, String> map = new HashMap<String, String>();
    	map.put("roomId", roomId);
    	map.put("userid", userid);
    	String time = chatService.getEnterTime(map);
    	
		// 첫입장이 아니면 첫입장 이후 메세지 다 불러오기
		HashMap<String, String> temp = new HashMap<String, String>();
		temp.put("roomId", roomId);
		temp.put("regtime", time);
		List<ChatMessage> messages = chatService.getChatMessage(temp);
		List<ChatUser> users = chatService.getChatUser(roomId);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("messages", messages);
		result.put("users", users);
		return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
    }
    
	@ApiOperation(value = "4. 채팅방 초대(채팅방 생성하고 나중에 초대)", notes = "입력 : 채팅방번호(roomId), 초대한 사람 아이디 배열(ids), 닉네임 배열(nicknames)")
    @PostMapping("/roomInvite")
    public ResponseEntity<?> inviteRoom(@RequestBody String js) throws Exception {
    	// 채팅방에 친구 초대
    	// 입력 : 채팅방번호(roomId), 초대한 사람 리스트(ids)
    	JSONParser jsonParse = new JSONParser();
		JSONObject jsonObj = null;
		try {
			jsonObj = (JSONObject) jsonParse.parse(js);
			String roomId = (String) jsonObj.get("roomId");
			List<String> ids = (List<String>) jsonObj.get("ids");
			List<String> nicknames = (List<String>) jsonObj.get("nicknames");
			for(int i=0; i<ids.size(); i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("roomId", roomId);
				map.put("userId", ids.get(i));
				map.put("userName", nicknames.get(i));
				chatService.createChatUser(map);
			}
			return new ResponseEntity<>("success", HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("fail", HttpStatus.NO_CONTENT);
    }
    	
	@ApiOperation(value = "5. 채팅방 나가기", notes = "입력 : userid, 채팅방번호(roomId)")
	@DeleteMapping("/roomExit")
	public ResponseEntity<?> exitChatRoom(@RequestBody String js) throws Exception {
		JSONParser jsonParse = new JSONParser();
		JSONObject jsonObj = null;
		try {
			jsonObj = (JSONObject) jsonParse.parse(js);
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("roomId", (String) jsonObj.get("roomId"));
			map.put("userId", (String) jsonObj.get("userid"));
			if(chatService.outChatRoom(map) > 0)
				return new ResponseEntity<>("success", HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("fail", HttpStatus.NO_CONTENT);
	}
}
