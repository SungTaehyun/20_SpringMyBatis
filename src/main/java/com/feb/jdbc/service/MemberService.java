package com.feb.jdbc.service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.feb.jdbc.dao.MemberDao;
import com.feb.jdbc.dto.EmailDto;
import com.feb.jdbc.entity.Member;
import com.feb.jdbc.util.EmailUtil;
import com.feb.jdbc.util.Sha512Encoder;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

@Service
public class MemberService { //MemberService는 어디서 빈을 만들어서 쓰는가? applicationContent-bean에서)
		
		@Autowired
		private MemberDao memberDao;

		private EmailUtil emailUtil;
		public void setEmailUtil(EmailUtil emailUtil) {
			this.emailUtil = emailUtil;
		}

		public HashMap<String, Object> findMember(String memberId) {
			return memberDao.findMember(memberId);
		}
		
		public Member findMember2(String memberId) {
			return memberDao.findMember2(memberId);
		}
		
		public int join(HashMap<String, String> params) {
			Sha512Encoder encoder = Sha512Encoder.getInstance();
			String passwd = params.get("passwd");
			String encodeTxt = encoder.getSecurePassword(passwd);
			params.put("passwd", encodeTxt);
			
			return memberDao.join(params);
		}
		
		public ArrayList<Member> memberList(HashMap<String, Object> params) {
			return memberDao.memberList(params);
		}
		
		public int deleteMember(HashMap<String, String> params) {
			return memberDao.deleteMember(params.get("memberId"));
		}

	
		public boolean findPasswd(HashMap<String,String> params) {
			
			System.out.println("memberId : : " + params.get("memberId"));
			System.out.println("member : : " + params.get("email"));
			int result = memberDao.findMember(params.get("memberId"),params.get("email"));
			
			System.out.println("result ="+result);
			if(result ==1) {
				// 랜덤한 문자열 생
				String  uuid = UUID.randomUUID().toString();
				System.out.println("newPw1 :" + uuid);
				
				// 필요없는 문자 제거
				String newPw = uuid.replaceAll("-", "");
				System.out.println("newPw 2: " +newPw);
				
				//암호화 
				String encodePw = Sha512Encoder.getInstance().getSecurePassword(newPw);
				System.out.println("newPw 3:" + encodePw);
				
				int updateResult = memberDao.updatePasswd(newPw,params.get("memberId"), params.get("email"));
				
				EmailDto emailDto = new EmailDto();
				emailDto.setFrom("whdudgms321@naver.com");
				emailDto.setReceiver("whdudgms123@naver.com");
				emailDto.setSubject("임시 비밀번호를 전송해드립니다.");
				// 이메일로 먼저 비번 알려주고 바꿔야 데이터 수정은 가장 마지막에 해야한다 
				emailDto.setText("dkdkdkdk");
				
				emailUtil.sendMail(emailDto);
				try {
					// 이메일 발송 실패 시 예외처리 
					emailUtil.sendMail(emailDto);
				}catch (Exception e){
					e.printStackTrace();
				}
				
				//to-do 임시 비밀번호로 업데이트
			//사용자 테이블에 비밀번호 컬럼 수정하는 메서드 작성.
				// interface > impl > service 
				// update lecture.member set passwd =  ?
				// where memberId = ? and email = ? 
				
				
				return updateResult ==1;
				
			}else {
				//ID/email 맞는 사용자는 무조건 1명이어야 함.
				return false;
			}    

		}
	}

