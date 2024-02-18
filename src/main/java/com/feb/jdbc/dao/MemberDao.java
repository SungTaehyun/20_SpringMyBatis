package com.feb.jdbc.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.feb.jdbc.entity.Member;
//인터페이스(작업지시서)
@Repository 
public interface MemberDao {// 단축키는 interface이름에 대고 컨트롤 + t
	
	public HashMap<String, Object> findMember(String memberId);
	
	public Member findMember2(String memberId);
	
	public ArrayList<Member> memberList(HashMap<String, Object> params);

	public int join(HashMap<String, String> params);
	
	public int checkId(HashMap<String, String> params);
	
	public HashMap<String, Object> getMemberById(HashMap<String, String> params);
	
	// 사용자ID와 이메일로 사용자 찾기(파라미터 값 2개 지정)
	public int findMember(String memberId, String email);

	public int updatePasswd(String passwd, String email, String memberId);
	
	public int deleteMember(String memberId);
	
}
