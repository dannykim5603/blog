package com.sbs.java.blog.controller;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.dto.CateItem;
import com.sbs.java.blog.util.Util;

public class ArticleController extends Controller {
	public ArticleController(Connection dbConn, String actionMethodName, HttpServletRequest req,
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
		case "list":
			return actionList(req, resp);

		case "detail":
			return actionDetail(req, resp);

		case "write":
			return actionWrtie(req, resp);
		}
		return "";

	}

	private String actionWrtie(HttpServletRequest req, HttpServletResponse resp) {
		String title = req.getParameter("title");
		String cateItemId = req.getParameter("cateItemId");
		String body = req.getParameter("body");
		
		return "article/doWrite";
	}

	private String actionDetail(HttpServletRequest req, HttpServletResponse resp) {
		if (Util.empty(req, "id")) {
			return "html:id를 입력해주세요.";
		}
		if (Util.isNum(req, "id") == false) {
			return "html:id를 정수로 입력해 주세요.";
		}

		int id = Util.getInt(req, "id");
		Article article = articleService.detail(id);

		req.setAttribute("article", article);

		return "article/detail.jsp";
	}

	private String actionList(HttpServletRequest req, HttpServletResponse resp) {
		int page = 1;

		if (!Util.empty(req, "page") && Util.isNum(req, "page")) {
			page = Util.getInt(req, "page");
		}

		int cateItemId = 0;

		if (!Util.empty(req, "cateItemId") && Util.isNum(req, "cateItemId")) {
			cateItemId = Util.getInt(req, "cateItemId");
		}
		
		String cateItemName = "전체";
		
		if ( cateItemId != 0 ) {
			CateItem cateItem = articleService.getCateItem(cateItemId);
			cateItemName = cateItem.getName();
		}
		req.setAttribute("cateItemName", cateItemName);
		

		String searchKeywordType = "";

		if (!Util.empty(req, "searchKeywordType")) {
			searchKeywordType = Util.getString(req, "searchKeywordType");
		}

		String searchKeyword = "";

		if (!Util.empty(req, "searchKeyword")) {
			searchKeyword = Util.getString(req, "searchKeyword");
		}

		int itemsInAPage = 10;
		int totalCount = articleService.getArticlesCount(cateItemId, searchKeywordType, searchKeyword);
		int totalPage = (int) Math.ceil(totalCount / (double) itemsInAPage);

		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("page", page);

		List<Article> articles = articleService.getArticles(page, itemsInAPage, cateItemId,
				searchKeywordType, searchKeyword);
		req.setAttribute("articles", articles);
		return "article/list.jsp";
	}
}