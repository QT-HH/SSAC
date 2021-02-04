package com.ssac.user.service;

import com.ssac.user.dto.User;

public interface UserService {
	// 회원등록
	int createUser(User user) throws Exception;
	// 회원찾기
	User findUser(User user) throws Exception;
}