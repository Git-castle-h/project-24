package com.spring.member.interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface MemberController {
	
	ModelAndView listMember(HttpServletRequest request, HttpServletResponse response) throws Exception;
	ModelAndView addMember(HttpServletRequest request, HttpServletResponse response) throws Exception;
	ModelAndView removeMember(HttpServletRequest request, HttpServletResponse response) throws Exception;
	ModelAndView modIntro(HttpServletRequest request, HttpServletResponse response) throws Exception;
	ModelAndView modMember(HttpServletRequest request, HttpServletResponse response) throws Exception;
	ModelAndView modError(HttpServletRequest request, HttpServletResponse response) throws Exception;
	ModelAndView updateMember(HttpServletRequest request, HttpServletResponse response) throws Exception;
	ModelAndView searchMember(HttpServletRequest request, HttpServletResponse response) throws Exception;
	String idOverlapped(HttpServletRequest request, HttpServletResponse response) throws Exception;

	

}
