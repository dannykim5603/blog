package com.sbs.java.blog.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.service.ArticleService;
import com.sbs.java.blog.util.Util;

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
			
		case "write":
			return actionWrtie(req,resp);
		}
		return "";
		
	}


	private String actionWrtie(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return null;
	}

	private String actionDetail(HttpServletRequest req, HttpServletResponse resp) {
		if (Util.empty(req,"id")) {
			return "plain:id를 입력해주세요.";
		}
		if (Util.isNum(req,"id") == false) {
			return "plain:id를 정수로 입력해 주세요.";
		}
		
		int id = Util.getInt(req,"id");
		Article article = articleService.detail(id);
		
		req.setAttribute("article", article);
		
		return "article/detail.jsp";
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
		
		return "article/list.jsp";
	}

		
}	