package com.sbs.java.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController extends Controller {
	

	@Override
	public String doAction(String actionMethodName, HttpServletRequest req, HttpServletResponse resp) {
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