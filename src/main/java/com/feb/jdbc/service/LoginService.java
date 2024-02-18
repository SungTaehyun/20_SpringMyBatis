package com.feb.jdbc.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.feb.jdbc.dao.LoginDao;
import com.feb.jdbc.entity.Member;
import com.feb.jdbc.util.Sha512Encoder;

@Service
public class LoginService {//xml에 있는 bean 열어서 beans 추가하기
	
	@Autowired
	private LoginDao loginDao; //객체 선언
	
	public boolean login(HashMap<String, String> params) {//dao에 있는 메서드명이랑 메서드명 통일시켜야한다.//
		String memberId = params.get("memberId");
		Member member = loginDao.login(memberId);
		if (ObjectUtils.isEmpty(member)) {
			return false;
		}
		
		String memberPw = member.getPasswd(); // DB에 있는 값
		
		Sha512Encoder encoder = Sha512Encoder.getInstance();//여기부터
		String passwd = params.get("passwd");//사용자가 입력한 값
		//사용자가 입력한 값을 암호화한 것.
		String encodeTxt = encoder.getSecurePassword(passwd);//여기까지는 암호화하는 코드
		
		System.out.println(member);
//		if(memberPw == encodeTxt) {//이런식으로 비교하면 안된다.
//			
//		}
//		if(StringUtils.pathEquals(memberPw, encodeTxt))//이런식으로 비교해야 한다.

		return StringUtils.pathEquals(memberPw, encodeTxt);
	}

}
