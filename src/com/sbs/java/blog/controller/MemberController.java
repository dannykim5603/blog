package com.sbs.java.blog.controller;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xpath.internal.operations.Bool;

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

		}
		return "";
	}

	private String actionLogin(HttpServletRequest req, HttpServletResponse resp) {
		return "html:<h1>hi</h1>";
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
					+ "번째 회원이 되신 것을 환영합니다.'); location.replace('login')</script>";
		}
		return "html:<script> alert('이미 존재하는 아이디 입니다.'); location.replace('join')</script>";
	}

	private String actionJoin(HttpServletRequest req, HttpServletResponse resp) {
		return "member/join.jsp";
	}

}
