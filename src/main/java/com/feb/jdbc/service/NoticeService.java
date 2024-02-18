package com.feb.jdbc.service;

import java.util.HashMap;

import com.feb.jdbc.dao.NoticeDao;

public class NoticeService {
	
	private NoticeDao noticeDao;
	
	public NoticeService() {}
	
	public NoticeService(NoticeDao noticeDao) {
		System.out.println(noticeDao);
		this.noticeDao = noticeDao;
	}

	public int writer(HashMap<String, String> params) {
		return noticeDao.writer(params);
	}
}

