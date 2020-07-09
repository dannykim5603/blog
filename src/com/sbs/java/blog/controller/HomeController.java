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
				return main();
			
			case "aboutMe":
				return aboutMe();
			}
		return "";
			
	}

	private String aboutMe() {
		return "home/aboutMe.jsp";
	}

	private String main() {
		return "home/main.jsp";
	}

}
