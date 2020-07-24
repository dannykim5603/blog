package com.sbs.java.blog.service;

import java.sql.Connection;
import java.util.List;

import com.sbs.java.blog.dao.ArticleDao;
import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.dto.ArticleReply;
import com.sbs.java.blog.dto.CateItem;
import com.sbs.java.blog.dto.Member;

public class ArticleService extends Service {
	private ArticleDao articleDao;

	public ArticleService(Connection dbConn) {
		articleDao = new ArticleDao(dbConn);
	}
	
	public List<Article> getArticles(int page,int itemsInAPage, int cateItemId,String searchKeywordType, String searchKeyword) {
		return articleDao.getArticles(page,itemsInAPage,cateItemId,searchKeywordType,searchKeyword);
	}

	public int delete(int num) {
		return articleDao.delete(num);
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

	public int doWrite(int displayStatus, int cateItemId, String title, String body, int loginedMemberId) {
		return articleDao.doWrite(displayStatus,cateItemId,title,body,loginedMemberId);
	}

	public void increaseHit(int id) {
		articleDao.increaseHit(id);
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}

	public int doModify(String title, String body, int displayStatus, int cateItemId,int id) {
		return articleDao.modify(title,body,displayStatus,cateItemId,id);
	}

	public void doWriteArticleReply(String articleReply, Member member, int articleId) {
		articleDao.writeArticleReply(articleReply,member,articleId);
	}

	public List<ArticleReply> getArticleReplyByArticleId(int articleId) {
		return articleDao.getArticleReplyByArticleId(articleId);
	}

	public void modifyReply(int replyId) {
		// TODO Auto-generated method stub
		
	}
}