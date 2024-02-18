package com.feb.jdbc.dao;

import com.feb.jdbc.entity.Member;

public interface LoginDao {//인터페이스 만들었으면  그다음 구현체(LoginDaoImpl)를 만들어 주어야 한다.
	
	public Member login(String memberId);

}
