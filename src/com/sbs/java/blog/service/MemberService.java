package com.sbs.java.blog.service;

import java.sql.Connection;
import java.util.Map;

import com.sbs.java.blog.dao.MemberDao;
import com.sbs.java.blog.dto.Member;

public class MemberService extends Service {
	private MemberDao memberDao;

	public MemberService(Connection dbConn) {
		memberDao = new MemberDao(dbConn);
	}
	
	public String getMemberNickname(int id) {
		return memberDao.getMemberNickname(id);
	}

	public int join(String loginId, String loginPw, String email, String name, String nickname) {

		return memberDao.join(loginId,loginPw,email,name,nickname);
	}

	public Boolean CheckId(String loginId) {
		return memberDao.checkId(loginId);
	}

	public Member getMemberByIdNPw(String loginId, String loginPw) {
		return memberDao.getMemberByIdnPw(loginId,loginPw);
	}

	public Member getMemberById(int id) {
		return memberDao.getMemberById(id);
	}

	public Boolean checkEmail(String email) {
		return memberDao.checkEmail(email);
	}

	public boolean nicknameCheck(String nickname) {
		return memberDao.nicknameCheck(nickname);
	}

	public Member getMemberByEmailANDName(String email, String name) {
		return memberDao.getMemberByEmailANDName(email,name);
	}

	public Member modifyMemberInfo(String email, String nickname, String loginPw, int id) {
		return memberDao.modifyMemberInfo(email,nickname,loginPw,id);
	}
}
