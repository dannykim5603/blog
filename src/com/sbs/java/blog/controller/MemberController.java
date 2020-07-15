package com.sbs.java.blog.controller;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dto.Member;

public class MemberController extends Controller {
	public MemberController(Connection dbConn, String actionMethodName, HttpServletRequest req,
			HttpServletResponse resp) {
		super(dbConn, actionMethodName, req, resp);
	}

	public void beforeAction() {
		super.beforeAction();
		// 이 메서드는 아티클 게시물 컨트롤러의 모든 액션이 실행되기 전에 실행된다.
		// 필요 없다면 지워도 된다.
	}

	public String doAction() {
		switch (actionMethodName) {
		case "join":
			return actionJoin(req, resp);

		case "doJoin":
			return actionDoJoin(req, resp);

		case "login":
			return actionLogin(req, resp);
		
		case "doLogin":
			return actionDoLogin(req,resp);
			
		case "doLogout":
			return actionLogout(req,resp);
		}
		return "";
	}

	private String actionLogout(HttpServletRequest req, HttpServletResponse resp) {
		
		return null;
	}

	private String actionDoLogin(HttpServletRequest req, HttpServletResponse resp) {
		String loginId = req.getParameter("loginId");
		String loginPw = req.getParameter("loginPw");
		
		Member member = memberService.getMemberByIdNPw(loginId,loginPw);
//		System.out.println(member); // 로그인 멤버 정보
		if (member != null ) {
		session.setAttribute("loginedMemberId", member.getId());
		return  "html:<script> alert('"+ member.getNickname() +"님 환영합니다.'); location.replace('../home/main')</script>";
		
		}
		return "html:<script> alert('아이디 혹은 비밀번호를 확인해 주세요'); location.replace('../home/main') </script>";
	}

	private String actionLogin(HttpServletRequest req, HttpServletResponse resp) {
		return "member/login.jsp";
	}

	private String actionDoJoin(HttpServletRequest req, HttpServletResponse resp) {
		String loginId = req.getParameter("loginId");
		String loginPw = req.getParameter("loginPwReal");
		String email = req.getParameter("email");
		String name = req.getParameter("name");
		String nickname = req.getParameter("nickname");

		Boolean idcheck = memberService.CheckId(loginId);
		// 계정이 있으면 true 계정이 없으면 false
		if (idcheck == false) {
			int memberId = memberService.Join(loginId, loginPw, email, name, nickname);
			return "html:<script> alert('" + nickname + " 회원님 " + memberId
					+ "번째 회원이 되신 것을 환영합니다.'); location.replace('../home/main')</script>";
		}
		return "html:<script> alert('이미 존재하는 아이디 입니다.'); location.replace('join')</script>";
	}

	private String actionJoin(HttpServletRequest req, HttpServletResponse resp) {
		return "member/join.jsp";
	}
}