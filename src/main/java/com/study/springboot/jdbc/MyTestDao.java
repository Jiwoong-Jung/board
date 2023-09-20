package com.study.springboot.jdbc;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MyTestDao {

	@Select("select 100+1")
	int myCount();
	
	List<MyUserDTO> list();
}
