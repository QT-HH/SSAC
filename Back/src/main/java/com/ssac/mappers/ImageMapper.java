package com.ssac.mappers;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImageMapper {
	public int insertLogo(HashMap<String, Object> map) throws Exception;
}
