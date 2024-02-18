package com.feb.jdbc.dao.impl;

import java.nio.charset.StandardCharsets;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.feb.jdbc.dao.MemberDao;
import com.feb.jdbc.entity.Member;
import com.feb.jdbc.entity.MemberRowMapper;
import com.feb.jdbc.util.Sha512Encoder;
//이 부분 설명가능 하도록 공부하기
public class MemberDaoImpl extends JdbcDaoSupport implements MemberDao {

	@Override
	public HashMap<String, Object> findMember(String memberId) {
		String sql = "select * from lecture.member where member_id = '" + memberId + "'";
		HashMap<String, Object> list = (HashMap<String, Object>) 
				getJdbcTemplate().queryForObject(sql, HashMap.class);
		return list;
	}
	
	public Member findMember2(String memberId) {
		String sql = "select member_idx, member_id, passwd, email, join_dtm from lecture.member where member_id = '" + memberId + "'";
		return getJdbcTemplate().queryForObject(sql, new MemberRowMapper());
	}

	@Override
	public ArrayList<Member> memberList(HashMap<String, Object> params) {
		int offset = Integer.parseInt((String) params.get("offset"));
		int limit = Integer.parseInt((String) params.get("limit"));
		String sql = "select member_idx as memberIdx, member_id as memberId, email, join_dtm as joinDtm"
				+ " from lecture.member offset " + offset + " limit " + limit;
		return (ArrayList<Member>) getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper(Member.class));
	}

	@Override
	public int join(HashMap<String, String> params) {
		Sha512Encoder encoder = Sha512Encoder.getInstance();//여기부터
		String passwd = params.get("passwd");//사용자가 입력한 값
		String encodeTxt = encoder.getSecurePassword(passwd);//여기까지는 암호화하는 코드이다.
		String sql = "INSERT INTO lecture.member "
				+ "(member_id, passwd, email, join_dtm) "
				+ "VALUES('" + params.get("memberId")
				+ "', '" + encodeTxt
				+ "', '" + params.get("email")
				+ "', to_char(now(), 'YYYYMMDDHH24MISS'))";
		
		return getJdbcTemplate().update(sql);
	}

	@Override
	public int checkId(HashMap<String, String> params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public HashMap<String, Object> getMemberById(HashMap<String, String> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int findMember(String memberId, String email) {// //여기서 select * 을 쓰면 메모리를 너무 많이쓰니까 count(1)을 쓴다.
		String sql = "select count(1) from lecture.member where member_id = ? and email = ?";//id와 email로 검색해서 일치하면 
		int result = getJdbcTemplate().queryForObject(
				sql,
				new Object[] { memberId, email},
				Integer.class);
		//밑에 코드 왜 필요없는지 공부하기
		//Member member = getJdbcTemplate().queryForObject(sql, new Object[] {memberId} , new MemberRowMapper());//밑줄은 구시대 기술이라 최신 기술로 바꿔라는 것.
		return result;
	}

	@Override
	public int updatePasswd(String passwd, String email, String memberId) {
		String sql  = "update lecture.member set passwd =  ? where member_id = ? and email = ?";
//		String sql = "update lecture.member set passwd = ? ";
//		sql += "where member_id = ? and email = ? ";
		int result = getJdbcTemplate().update(sql, new Object[] {passwd, memberId, email});
		
		return result;
	}	
	
	
}
