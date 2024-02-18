package com.feb.jdbc.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.feb.jdbc.entity.Member;
import com.feb.jdbc.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;

	@GetMapping("/deleteMember.do") //http://localhost:8080/20-mybatis/deleteMember.do?memberId=tjdxogus12345
	public ModelAndView deleteMember(@RequestParam HashMap<String, String> params) {
		String memberId = params.get("memberId");
		ModelAndView mv = new ModelAndView();
		int result = memberService.join(params);
		mv.setViewName("login");
		mv.addObject("resultCode", result);
		
		return mv;
	}
	
	
	@ResponseBody
	@RequestMapping("/findMember.do")//http://localhost:8080/20-mybatis/findMember.do?memberId=tjdxogus12345
	public String findMember(@RequestParam HashMap<String, String> params) {
		String memberId = params.get("memberId");
		Member member = memberService.findMember2(memberId);
//		HashMap<String, Object> map = memberService.findMember(memberId);
		return member.getMemberId();
	}
	
	@PostMapping("/join.do")
	public ModelAndView join(@RequestParam HashMap<String, String> params,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		int result = memberService.join(params);//성공 실패여부 따지는 콬드
		mv.setViewName("login");
		mv.addObject("resultCode", result);
		if (result == 1) {
			mv.addObject("resultMsg", "회원가입 성공");//성공이구나
		} else {
			mv.addObject("resultMsg", "회원가입 실패");//실패구나
		}
		return mv;
	}
	
	@GetMapping("/list.do")
	public ModelAndView memberList(@RequestParam HashMap<String, Object> params) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("memberList", memberService.memberList(params));
		mv.setViewName("list");
		return mv;		
	}
	
	@GetMapping("/findPw.do")
	public ModelAndView findPw(@RequestParam HashMap<String,String> params) {
		
		ModelAndView mv = new ModelAndView();
		boolean result = memberService.findPasswd(params);
	
		mv.setViewName("common/broker");
		mv.addObject("resultCode", result );
		mv.addObject("nextUri", "/loginPage.do");

		if (result) {
			mv.addObject("resultMsg", "이메일로 임시 비밀번호 발송함.");
		} else {
			mv.addObject("resultMsg", "사용자가 없습니다.");
		}
		return mv;
	}
}
//http://localhost:8080/spring-jdbc/findPw.do?memberId=jbw02003&email=jbw02003@naver.com
//위의 주소를 제대로 입력하면

//똑바로 된거 넣으면 "임시 비밀번호 발송함."

//잘못된거 넣으면  "사용자가 없어요"











