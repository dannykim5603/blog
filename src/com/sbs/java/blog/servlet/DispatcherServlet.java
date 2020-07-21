package com.sbs.java.blog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.app.App;
import com.sbs.java.blog.service.MailService;

public class DispatcherServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		new App(req,resp).start();
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		String gmailId = getServletConfig().getInitParameter("gmailId");
		String gmailPw = getServletConfig().getInitParameter("gmailPw");

		MailService mailService = new MailService(gmailId, gmailPw, gmailId, "관리자");
		boolean sendMailDone = mailService.send("blog.dannys@gmail.com", "안녕하세요.", "환영합니다.") == 1;

		resp.getWriter().append(String.format("발송성공 : %b", sendMailDone));
	
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
