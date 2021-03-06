package com.sbs.java.blog.controller;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sbs.java.blog.dto.CateItem;
import com.sbs.java.blog.dto.Member;
import com.sbs.java.blog.service.ArticleService;
import com.sbs.java.blog.service.MailService;
import com.sbs.java.blog.service.MemberService;
import com.sbs.java.blog.util.Util;

// Controller
public abstract class Controller {
	protected Connection dbConn;
	protected String actionMethodName;
	protected HttpServletRequest req;
	protected HttpServletResponse resp;
	protected HttpSession session;
	
	protected ArticleService articleService;
	protected MemberService memberService;
	protected MailService mailService;
	
	public Controller(Connection dbConn, String actionMethodName, HttpServletRequest req, HttpServletResponse resp) {
		this.dbConn = dbConn;
		this.actionMethodName = actionMethodName;
		this.req=req;
		this.resp=resp;
		articleService = new ArticleService(dbConn);
		memberService = new MemberService(dbConn);
		this.session = req.getSession();
		
		String gmailId = (String)session.getAttribute("gmailId");
		String gmailPw = (String)session.getAttribute("gmailPw");
		mailService = new MailService(gmailId, gmailPw, gmailId, "DANNY");
	}
	
	public abstract String getControllerName();

	public void beforeAction() {
		//액션 전 실행 
		// 이 메서드는 모든 게시물 컨트롤러의 모든 액션이 실행되기 전에 실행된다.
		//필요 없다면 지워도 된다.
		List<CateItem> cateItems = articleService.getCateItemsForPrint();
		req.setAttribute("cateItems", cateItems);
		
		int loginedMemberId = -1;
		boolean isLogined = false;
		Member loginedMember = null;
		
		if (session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int)session.getAttribute("loginedMemberId");
			isLogined = true;
			loginedMember = memberService.getMemberById(loginedMemberId);
		}
		
		req.setAttribute("loginedMemberId", loginedMemberId);
		req.setAttribute("loginedMember", loginedMember);
		req.setAttribute("isLogined", isLogined);
		
		String currentUrl = req.getRequestURI();

		if (req.getQueryString() != null) {
			currentUrl += "?" + req.getQueryString();
		}

		String urlEncodedCurrentUrl = Util.getUrlEncoded(currentUrl);

		// 현재 접속된 페이지와 관련된 유용한 정보 담기
		req.setAttribute("currentUrl", currentUrl);
		req.setAttribute("urlEncodedCurrentUrl", urlEncodedCurrentUrl);
		req.setAttribute("urlEncodedAfterLoginRedirectUrl", urlEncodedCurrentUrl);

		// 로그인 페이지에서 로그인 페이지로 이동하는 버튼을 또 누른 경우
		// 기존 afterLoginRedirectUrl 정보를 유지시키기 위한 로직
		if (currentUrl.contains("/s/member/login")) {
			System.out.println("currentUrl : " + currentUrl);
			String urlEncodedOldAfterLoginRedirectUrl = Util.getString(req, "afterLoginRedirectUrl", "");
			urlEncodedOldAfterLoginRedirectUrl = Util.getUrlEncoded(urlEncodedOldAfterLoginRedirectUrl);
			req.setAttribute("urlEncodedAfterLoginRedirectUrl", urlEncodedOldAfterLoginRedirectUrl);
		}
		
		req.setAttribute("urlEncodedAfterLogoutRedirectUrl", urlEncodedCurrentUrl);
	}
	
	public void afterAction() {
		//액션 후 실행
	}
	
	public abstract String doAction() ;

	public String executeAction() {
		beforeAction();
		String doGuardRs = doGuard();
		
		if (doGuardRs != null) {
			return doGuardRs;
		}
		String rs = doAction();
		afterAction();
	
		return rs;
	}
	private String doGuard() {
		boolean isLogined = (boolean) req.getAttribute("isLogined");
		//로그인에 관련된 가드 시작
		boolean needToLogin = false;
		
		String controllerName = getControllerName();
		
		switch (controllerName) {
		case "member":
			switch(actionMethodName) {
			case "doLogout":
				needToLogin = true;
				break;
			}
			break;
		case "article":
			switch (actionMethodName) {
			case "write":
			case "doWrite":
			case "modify":
			case "doModify":
			case "doDelete":
			case "writeArticleReply":
			case "ReplyDelete":
			case "ReplyModify":
				needToLogin = true;
				break;
			}
			break;
		case "home":
			switch (actionMethodName) {
			case "aboutMe":
				needToLogin = true;
				break;
			}
		}
		
		String urlEncodedAfterLoginRedirectUrl = (String)req.getAttribute("urlEncodedAfterLoginRedirectUrl");
		
		if (needToLogin && isLogined == false ) {
			return "html:<script> alert('로그인 후 이용해 주세요.'); location.href = '../member/login?afterLoginRedirectUrl=" + urlEncodedAfterLoginRedirectUrl + "';</script>";
		}
		//로그인에 관련된 가드 끝
		
		//로그아웃에 관련된 가드 시작
		
		boolean needToLogout = false;
		
		switch (controllerName) {
		case "member":
			switch (actionMethodName) {
			case "login":
			case "join":
				needToLogout = true;
				break;
			}
			break;
		}
		if (needToLogout && isLogined) {
			return "html:<script> alert('로그아웃 후 이용해주세요.'); history.back();</script>";
		}
		//로그아웃에 관련된 가드 끝
		
		return null;
	}
		
	
}