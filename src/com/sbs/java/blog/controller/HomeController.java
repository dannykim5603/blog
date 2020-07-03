package com.sbs.java.blog.controller;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController extends Controller {
	public HomeController(Connection dbConn, String actionMethodName, HttpServletRequest req,
			HttpServletResponse resp) {
		super( dbConn, actionMethodName, req, resp);
	}

	@Override
	public String doAction() {
			switch (actionMethodName) {
			case "main":
				return main(req,resp);
			
			case "aboutMe":
				return aboutMe(req,resp);
			}
		return "";
			
	}

	private String aboutMe(HttpServletRequest req, HttpServletResponse resp) {
		return "home/aboutMe.jsp";
	}

	private String main(HttpServletRequest req, HttpServletResponse resp) {
		return "home/main.jsp";
	}

}
