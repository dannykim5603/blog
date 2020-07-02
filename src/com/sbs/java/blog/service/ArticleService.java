package com.sbs.java.blog.service;

import java.sql.Connection;
import java.util.List;

import com.sbs.java.blog.dao.ArticleDao;
import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.dto.ArticleReply;
import com.sbs.java.blog.dto.Board;

public class ArticleService {
	private ArticleDao articleDao;

	public ArticleService(Connection dbConn) {
		articleDao = new ArticleDao(dbConn);
	}

	public List<Article> getArticlesByBoardCode(String code) {
		return articleDao.getArticlesByBoardCode(code);
	}

	public List<Board> getBoards() {
		return articleDao.getBoards();
	}

	public int makeBoard(String name, String code) {
		Board oldBoard = articleDao.getBoardByCode(code);

		if (oldBoard != null) {
			return -1;
		}

		Board board = new Board(name, code);
		return articleDao.saveBoard(board);
	}

	public Board getBoard(int id) {
		return articleDao.getBoard(id);
	}
//
//	public int write(int boardId, int memberId, String title, String body) {
//		Article article = new Article(boardId, memberId, title, body);
//		return articleDao.save(article);
//	}

	public List<Article> getArticles(int page,int itemsInAPage, int cateItemId) {
		return articleDao.getArticles(page,itemsInAPage,cateItemId);
	}

	public void makeBoardIfNotExists(String name, String code) {
		Board board = articleDao.getBoardByCode(code);
		
		if ( board == null ) {
			makeBoard(name, code);
		}
	}

	public Board getBoardByCode(String boardCode) {
		return articleDao.getBoardByCode(boardCode);
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

	public int writeArticleReply(int articleId, int memberId, String body) {
		ArticleReply articleReply = new ArticleReply(articleId, memberId, body);
		return articleDao.saveReply(articleReply);
	}

	public List<ArticleReply> getArticleReplyByArticleId(int id) {
		return articleDao.getArticleReplyByArticleId(id);
	}

	public ArticleReply getReplyById(int id) {
		return articleDao.getArticleReplyById(id);
	}

	public void deleteReply(int id) {
		articleDao.deleteReply(id);
	}

	public void deleteBoard(int id) {
		articleDao.deleteBoardBycode(id);
	}

	public int getArticlesCount(int cateItemId) {
		return articleDao.getArticlesCount(cateItemId);
	}

	public String getBoardName(int cateItemId) {
		return articleDao.getBoardName(cateItemId);
	}
}