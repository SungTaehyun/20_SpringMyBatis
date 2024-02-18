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

import com.feb.jdbc.service.NoticeService;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService noticeService;

	@PostMapping("/notice.do")
	public ModelAndView join(@RequestParam HashMap<String, String> params, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		int result = noticeService.writer(params);
		mv.setViewName("");
		mv.addObject("resultCode", result);
		if (result == 1) {
			mv.addObject("resultMsg", "회원가입 성공");
		} else {
			mv.addObject("resultMsg", "회원가입 실패");
		}
		return mv;
	}
}
