package com.spring.member.interfaces;

import java.util.List;

import com.spring.member.vo.MemberVO;

public interface MemberService {

	List<MemberVO> listMember() throws Exception;
	int addMember(MemberVO memberVO) throws Exception;
	int removeMember(String id) throws Exception;
	MemberVO searchMemberById(String id)throws Exception;
	List<MemberVO> searchMemberByPwd(String pwd)throws Exception;
	int updateMember(MemberVO memberVO) throws Exception;
	List<MemberVO> searchMember(MemberVO memberVO)throws Exception ;
	boolean idOverlapped(String id)throws Exception ;
}
