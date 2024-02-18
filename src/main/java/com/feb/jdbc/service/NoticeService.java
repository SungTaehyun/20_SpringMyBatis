package com.feb.jdbc.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feb.jdbc.dao.NoticeDao;

@Service
public class NoticeService {
	
	@Autowired
	private NoticeDao noticeDao;
	
	
	public int writer(HashMap<String, String> params) {
		return noticeDao.writer(params);
	}
}

