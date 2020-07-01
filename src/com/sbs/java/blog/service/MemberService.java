package com.sbs.java.blog.service;

import java.sql.Connection;

import com.sbs.java.blog.dao.MemberDao;
import com.sbs.java.blog.dto.Member;

public class MemberService {
	private MemberDao memberDao;

	public MemberService(Connection dbConn) {
		memberDao = new MemberDao(dbConn);
	}

	public Member getMemberByLoginIdAndLoginPw(String loginId, String loginPw) {
		return memberDao.getMemberByLoginIdAndLoginPw(loginId, loginPw);
	}

	public int join(String loginId, String loginPw, String name) {
		Member oldMember = memberDao.getMemberByLoginId(loginId);

		if (oldMember != null) {
			return -1;
		}

		Member member = new Member(loginId, loginPw, name);
		return memberDao.save(member);
	}

	public Member getMember(int id) {
		return memberDao.getMember(id);
	}

	public void makeAdminUserIfNotExists() {
		Member member = memberDao.getMemberByLoginId("admin");
		
		if (member == null) {
			join("admin", "admin", "관리자");
		}
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}
}