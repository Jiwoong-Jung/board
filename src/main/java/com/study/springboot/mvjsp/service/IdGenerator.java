package com.study.springboot.mvjsp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IdGenerator {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Transactional
	public int generateNextId(String sequenceName) {

		String sql = "select next_value from id_sequence " +
				     "where sequence_name = ? for update";
		int id = this.jdbcTemplate.queryForObject(sql, 
				(rs, n)-> rs.getInt(1), sequenceName);
		id++;
		sql = "update id_sequence set next_value = ? " +
				  "where sequence_name = ?";
		this.jdbcTemplate.update(sql, id, sequenceName);
		return id;
	}

}
