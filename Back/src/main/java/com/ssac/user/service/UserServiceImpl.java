package com.ssac.user.service;

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
}
