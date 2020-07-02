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
			
		case "detail":
			return actionDetail(req,resp);
		}
		return "";
		
	}


	private String actionDetail(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		Article article = articleService.detail(id);
		
		req.setAttribute("article", article);
		
		return "article/detail";
	}

	private String actionList(HttpServletRequest req, HttpServletResponse resp) {
		int cateItemId = 0;
		if (req.getParameter("cateItemId") != null) {
		 cateItemId= Integer.parseInt(req.getParameter("cateItemId"));
		}
		
		int page = 1;
		if (req.getParameter("page") != null) {
			page= Integer.parseInt(req.getParameter("page"));
		}
		
		int itemsInAPage = 10;
		int totalCount = articleService.getArticlesCount(cateItemId);
		int totalPage = (int)Math.ceil(totalCount / (double)itemsInAPage);
		
		String boardName = articleService.getBoardName(cateItemId);
		
		req.setAttribute("boardName", boardName);
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("page", page);
		
		List<Article> articles = articleService.getArticles(page,itemsInAPage,cateItemId);
		
		req.setAttribute("articles", articles);
		
		return "article/list";
	}

		
}	