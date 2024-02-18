package com.feb.jdbc.dao;

import org.springframework.stereotype.Repository;

import com.feb.jdbc.entity.Member;
//인터페이스(작업지시서)

@Repository
public interface LoginDao {//인터페이스 만들었으면  그다음 구현체(LoginDaoImpl)를 만들어 주어야 한다.
	
	//로그인 
	public Member login(String memberId);//Member는 타입

}
