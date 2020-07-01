package com.sbs.java.blog.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.service.ArticleService;

public class ArticleController extends Controller {
	private ArticleService articleService;

	public ArticleController(Connection dbConn) {
		articleService = new ArticleService(dbConn);
	}

	public String doAction(String actionMethodName, HttpServletRequest req, HttpServletResponse resp) {
		switch (actionMethodName) {
		case "list":
			return actionList(req, resp);
			
		case "write":
			actionWrite();
		}
		return "";
		
	}

	private void actionWrite() {
		// TODO Auto-generated method stub
		
	}

	private String actionList(HttpServletRequest req, HttpServletResponse resp) {
		int cateItemId = 0;
		if (req.getParameter("cateItemId") != null) {
		 cateItemId= Integer.parseInt(req.getParameter("cateItemId"));
		}
		int page = 0;
		if (req.getParameter("page") != null) {
		 page= Integer.parseInt(req.getParameter("cateItemId"));
		}
		
		List<Article> articles = articleService.getArticles(page,cateItemId);
		
		req.setAttribute("articles", articles);
		
		return "article/list";
	}

		
}