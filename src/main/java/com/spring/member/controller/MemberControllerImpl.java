package com.spring.member.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.spring.member.interfaces.MemberController;
import com.spring.member.interfaces.MemberService;
import com.spring.member.vo.MemberVO;

public class MemberControllerImpl  extends MultiActionController implements MemberController{

	private MemberService memberService;

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@Override
	public ModelAndView listMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		List<MemberVO> memberList = memberService.listMember();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("memberList",memberList);
		return mav;
	}
	
	@Override
	public ModelAndView addMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		MemberVO memberVO = new MemberVO();
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		memberVO.setId(id);
		memberVO.setPwd(pwd);
		memberVO.setName(name);
		memberVO.setEmail(email);
		
		bind(request,memberVO);
		int result = 0;
		result = memberService.addMember(memberVO);
		ModelAndView mav = new ModelAndView("redirect:/member/listMember.do");
		return mav;
		
	}
	
	@Override
	public ModelAndView removeMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		memberService.removeMember(id);
		ModelAndView mav = new ModelAndView("redirect:/member/listMember.do");
		return mav;
	}
	
	
	@Override
	public ModelAndView modIntro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String viewName = getViewName(request);
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		MemberVO memberVO = memberService.searchMemberById(id);
		
		HttpSession session = request.getSession();
		session.setAttribute("member", memberVO);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		
		return mav;
	}
	
	@Override
	public ModelAndView modMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		
		String pwd = request.getParameter("pwd");
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		ModelAndView mav =new ModelAndView();
		if(memberVO.getPwd().equals(pwd)){
			mav.setViewName(viewName);
		}else {
			mav.setViewName("redirect:/member/modError.do");
		}
		
		
		return mav;
	}
	
	@Override
	public ModelAndView modError(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		ModelAndView mav =new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	

	@Override
	public ModelAndView updateMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		MemberVO memberVO = new MemberVO();
		memberVO.setId(id);
		memberVO.setPwd(pwd);
		memberVO.setName(name);
		memberVO.setEmail(email);
		
		memberService.updateMember(memberVO);
		
		ModelAndView mav = new ModelAndView("redirect:/member/listMember.do");
		return mav;
	}
	
	@Override
	public ModelAndView searchMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		MemberVO memberVO = new MemberVO();
		
		memberVO.setId(id);
		memberVO.setName(name);
		memberVO.setEmail(email);
		
		List<MemberVO> memberList = memberService.searchMember(memberVO);
		
		ModelAndView mav = new ModelAndView("listMember");
		mav.addObject("memberList",memberList);
		return mav;
	}

	
	public ModelAndView memberForm(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String viewName = getViewName(request);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	@Override
	public String idOverlapped(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		
		PrintWriter writer = response.getWriter();
		
		String id = request.getParameter("id");
		
		boolean result =memberService.idOverlapped(id);
		
		PrintWriter pw = response.getWriter();
		
		
		String isUsable;
		if(result == true) {
			System.out.println(result);
			isUsable="not_usable";
			
		}else {
			System.out.println(result);
			isUsable="usable";
		}
		
		try {
			pw.print(isUsable);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return isUsable;
		
	}
	


	
	
	private String getViewName(HttpServletRequest request) throws Exception{
		String contextPath = request.getContextPath();
		String uri = (String)request.getAttribute("javax.servlet.include.request_uri");
		if(uri==null||uri.trim().equals("")) {
			uri = request.getRequestURI();
		}
		
		int begin = 0;
		if(!(contextPath == null)||("".equals(contextPath))) {
			
			begin = contextPath.length();
		} 
		
		int end;
		
		if(uri.indexOf(";")!=-1) {
			end = uri.indexOf(";");
		}else if(uri.indexOf("?")!=-1) {
			end=uri.indexOf("?");
		}else {
			end =uri.length();
		}
		
		String fileName= uri.substring(begin,end);
		if(fileName.indexOf(".")!=-1) {
			fileName=fileName.substring(0,fileName.lastIndexOf("."));
		}
		if(fileName.lastIndexOf("/")!=-1) {
			fileName=fileName.substring(fileName.lastIndexOf("/"),fileName.length());
		}
		
		return fileName;
	}




	
	
}
