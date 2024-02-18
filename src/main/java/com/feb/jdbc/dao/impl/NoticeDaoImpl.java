package com.feb.jdbc.dao.impl;

import java.util.HashMap;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.feb.jdbc.dao.MemberDao;
import com.feb.jdbc.dao.NoticeDao;

public class NoticeDaoImpl  extends JdbcDaoSupport implements NoticeDao {
	
	public HashMap<String, Object> findNotice(String noticeId){
		return null;
	
	}

	@Override
	public int writer(HashMap<String, String> params) {
		String sql = "INSERT INTO lecture.notice "
				+ "(author, content, wrt_dtm) "
				+ "VALUES('" + params.get("author")
				+ "', '" + params.get("cotent")
				+ "', '" + params.get("wrt_dtm")+"')";
		return getJdbcTemplate().update(sql);
	}
	
}
