package com.feb.jdbc.dao.impl;

import org.springframework.jdbc.core.support.JdbcDaoSupport;//인터페이스 구현체

import com.feb.jdbc.dao.LoginDao;
import com.feb.jdbc.entity.Member;
import com.feb.jdbc.entity.MemberRowMapper;

public class LoginDaoImpl extends JdbcDaoSupport implements LoginDao{
	
	@Override
	public Member login(String memberId) {
		String sql = "select * from " 
				+ "lecture.member where member_id = ?";
		Member member = getJdbcTemplate().queryForObject(sql, new Object[] {memberId} , new MemberRowMapper());//밑줄은 구시대 기술이라 최신 기술로 바꿔라는 것.
		return member;
	}
}
