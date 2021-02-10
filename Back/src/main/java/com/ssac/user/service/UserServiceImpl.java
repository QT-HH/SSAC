package com.ssac.user.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssac.mappers.UserMapper;
import com.ssac.user.dto.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper mapper;
	
	@Override
	// 회원등록
	public int createUser(User user) throws Exception {
		return mapper.insertUser(user);
	}
	
	@Override
	// 회원찾기
	public User findUser(User user) throws Exception {
		return mapper.selectUser(user);
	}
	
	@Override
	public List<String> getFollowingList(String userid) throws Exception {
		return mapper.selectFollowing(userid);
	}
	
	@Override
	public List<String> getFollowerList(String userid) throws Exception {
		return mapper.selectFollower(userid);
	}
	
	@Override
	public int modifyUserNickname(User user) throws Exception {
		return mapper.updateUserNickname(user);
	}
	
	@Override
	public int modifyUserPassword(HashMap<String, String> map) throws Exception {
		return mapper.updateUserPassword(map);
	}
	
	@Override
	public int removeUser(User user) throws Exception {
		return mapper.deleteUser(user);
	}
	
	@Override
	public int doFollowing(HashMap<String, String> map) throws Exception {
		return mapper.insertFollowing(map);
	}
}
