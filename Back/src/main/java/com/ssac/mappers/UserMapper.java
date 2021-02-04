package com.ssac.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.ssac.user.dto.User;

@Mapper
public interface UserMapper {
	public User selectUser(User user) throws Exception;
	public int insertUser(User user) throws Exception;
	public int updateUser(User user) throws Exception;
	public int deleteUser(User user) throws Exception;
}
