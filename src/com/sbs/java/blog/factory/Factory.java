package com.sbs.java.blog.factory;

import java.util.Scanner;

import com.sbs.java.blog.controller.Session;
import com.sbs.java.blog.dao.ArticleDao;
import com.sbs.java.blog.dao.MemberDao;
import com.sbs.java.blog.db.DBConnection;
import com.sbs.java.blog.service.ArticleService;
import com.sbs.java.blog.service.BuildService;
import com.sbs.java.blog.service.MemberService;

public class Factory {
	private static Session session;
	private static DBConnection dbConnection;
	private static BuildService buildService;
	private static ArticleService articleService;
	private static ArticleDao articleDao;
	private static MemberService memberService;
	private static MemberDao memberDao;
	private static Scanner scanner;

	public static DBConnection getDBConnection() {
		if (dbConnection == null) {
			dbConnection = new DBConnection();
		}

		return dbConnection;
	}

	public static Session getSession() {
		if (session == null) {
			session = new Session();
		}

		return session;
	}

	public static Scanner getScanner() {
		if (scanner == null) {
			scanner = new Scanner(System.in);
		}

		return scanner;
	}

	public static ArticleService getArticleService() {
		if (articleService == null) {
			articleService = new ArticleService();
		}

		return articleService;
	}

	public static ArticleDao getArticleDao() {
		if (articleDao == null) {
			articleDao = new ArticleDao();
		}

		return articleDao;
	}

	public static MemberService getMemberService() {
		if (memberService == null) {
			memberService = new MemberService();
		}
		return memberService;
	}

	public static MemberDao getMemberDao() {
		if (memberDao == null) {
			memberDao = new MemberDao();
		}

		return memberDao;
	}

	public static BuildService getBuildService() {
		if (buildService == null) {
			buildService = new BuildService();
		}

		return buildService;
	}
}