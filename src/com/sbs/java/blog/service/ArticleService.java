package com.sbs.java.blog.service;

import java.sql.Connection;
import java.util.List;

import com.sbs.java.blog.dao.ArticleDao;
import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.dto.CateItem;

public class ArticleService extends Service {
	private ArticleDao articleDao;

	public ArticleService(Connection dbConn) {
		articleDao = new ArticleDao(dbConn);
	}

	public List<Article> getArticles(int page,int itemsInAPage, int cateItemId,String searchKeywordType, String searchKeyword) {
		return articleDao.getArticles(page,itemsInAPage,cateItemId,searchKeywordType,searchKeyword);
	}

	public void modify(int num, String title, String body) {
		articleDao.modify(num,title,body);
	}

	public void delete(int num) {
		articleDao.delete(num);
	}

	public Article detail(int num) {
		return articleDao.detail(num);
	}


	public void deleteReply(int id) {
		articleDao.deleteReply(id);
	}

	public void deleteBoard(int id) {
		articleDao.deleteBoardBycode(id);
	}

	public int getArticlesCount(int cateItemId, String searchKeywordType, String searchKeyword) {
		return articleDao.getArticlesCount(cateItemId,searchKeywordType,searchKeyword);
	}

	public String getBoardName(int cateItemId) {
		return articleDao.getBoardName(cateItemId);
	}

	public List<CateItem> getCateItemsForPrint() {
		return articleDao.getCateItemsForPrint();
	}

	public CateItem getCateItem(int cateItemId) {
		return articleDao.getCateItem(cateItemId);
	}

	public void doWrite(int displayStatus, int cateItemId, String title, String body) {
		articleDao.doWrite(displayStatus,cateItemId,title,body);
	}
}