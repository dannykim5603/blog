package com.sbs.java.blog.service;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dao.MemberDao;

public class MemberService extends Service {
	private MemberDao memberDao;

	public MemberService(Connection dbConn) {
		memberDao = new MemberDao(dbConn);
	}

	public int Join(String loginId, String loginPw, String email, String name, String nickname) {

		return memberDao.join(loginId,loginPw,email,name,nickname);
	}

	public Boolean CheckPwConfirm(String pw1, String pw2) {
		return memberDao.checkPw(pw1,pw2);
	}

	public Boolean CheckId(String loginId) {
		return memberDao.checkId(loginId);
	}
}
