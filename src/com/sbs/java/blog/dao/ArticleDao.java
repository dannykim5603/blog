package com.sbs.java.blog.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.dto.ArticleReply;
import com.sbs.java.blog.dto.Board;
import com.sbs.java.blog.service.page;
import com.sbs.java.blog.util.DBUtil;

// Dao
public class ArticleDao {
	private Connection dbConn;

	public ArticleDao(Connection dbConn) {
		this.dbConn = dbConn;
	}

	public int save(Article article) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("INSERT INTO article "));
		sb.append(String.format("SET regDate = '%s' ", article.getRegDate()));
		sb.append(String.format(", `title` = '%s' ", article.getTitle()));
		sb.append(String.format(", `body` = '%s' ", article.getBody()));
//		sb.append(String.format(", `memberId` = '%d' ", article.getMemberId()));
//		sb.append(String.format(", `boardId` = '%d' ", article.getBoardId()));

		return DBUtil.insert(dbConn,sb.toString());
	}

	public Board getBoard(int id) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * "));
		sb.append(String.format("FROM `board` "));
		sb.append(String.format("WHERE 1 "));
		sb.append(String.format("AND `id` = '%d' ", id));

		Map<String, Object> row = dbConn.selectRow(dbConn,sb.toString());

		if (row.isEmpty()) {
			return null;
		}

		return new Board(row);
	}

	public List<Article> getArticles(int page, int itemsInAPage,int cateItemId) {
		
		String sql = "";

		int limitFrom = (page - 1) * itemsInAPage;

		sql += String.format("SELECT * ");
		sql += String.format("FROM article ");
		sql += String.format("WHERE displayStatus = 1 ");
		if (cateItemId != 0) {
			sql += String.format("AND cateItemId = %d ", cateItemId);
		}
		sql += String.format("ORDER BY id DESC ");
		sql += String.format("LIMIT %d, %d ", limitFrom, itemsInAPage);

		List<Map<String, Object>> rows = DBUtil.selectRows(dbConn, sql);
		List<Article> articles = new ArrayList<>();

		for (Map<String, Object> row : rows) {
			articles.add(new Article(row));
		}

		return articles;
	}

	public Article detail(int num) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(String.format("SELECT *"));
		sql.append(String.format("FROM article "));
		sql.append(String.format("WHERE id = " + num + ";"));
		
		Map<String, Object> row= DBUtil.selectRow(dbConn, sql.toString());
		Article article = new Article(row);
		
		return article;
	}
	
	public void modify(int num, String title, String body) {
		StringBuilder sql = new StringBuilder();

		sql.append(String.format("UPDATE article "));
		sql.append(String.format("SET title = '" + title + "', "));
		sql.append(String.format("`body` = '" + body + "' "));
		sql.append(String.format("WHERE id = " + num + ";"));

		dbConn.insert(dbConn,sql.toString());
	}

	public void delete(int num) {
		StringBuilder sql = new StringBuilder();

		sql.append(String.format("DELETE FROM article "));
		sql.append(String.format("WHERE id = " + num + ";"));
		
		dbConn.delete(dbConn,sql.toString());
	}


	public int saveReply(ArticleReply articleReply) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("INSERT INTO articleReply "));
		sb.append(String.format("SET regDate = '%s' ", articleReply.getRegDate()));
		sb.append(String.format(", `body` = '%s' ", articleReply.getBody()));
		sb.append(String.format(", `memberId` = '%d' ", articleReply.getMemberId()));
		sb.append(String.format(", `articleId` = '%d' ", articleReply.getArticleId()));

		return dbConn.insert(dbConn,sb.toString());
	}

	public List<ArticleReply> getArticleReplyByArticleId(int id) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * "));
		sb.append(String.format("FROM `articleReply` "));
		sb.append(String.format("WHERE articleId = '%d' ",id));
		sb.append(String.format("ORDER BY id DESC "));

		List<ArticleReply> articleReplies = new ArrayList<>();
		List<Map<String, Object>> rows = dbConn.selectRows(dbConn,sb.toString());

		for (Map<String, Object> row : rows) {
			
			articleReplies.add(new ArticleReply(row));
		}
		return articleReplies;
	}

	public ArticleReply getArticleReplyById(int id) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * "));
		sb.append(String.format("FROM `articleReply` "));
		sb.append(String.format("WHERE Id = '%d' ",id));
		sb.append(String.format("ORDER BY id DESC "));

		Map<String, Object> row = dbConn.selectRow(dbConn,sb.toString());

		ArticleReply articleReply = new ArticleReply(row);

		return articleReply;
	}

	public void deleteReply(int id) {
		StringBuilder sql = new StringBuilder();

		sql.append(String.format("DELETE FROM `articleReply` "));
		sql.append(String.format("WHERE id = " + id + ";"));
		
		dbConn.delete(dbConn,sql.toString());
	}

	public void deleteBoardBycode(int id) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(String.format("DELETE FROM `board` "));
		sql.append(String.format("WHERE id = %d ;",id));
		dbConn.delete(dbConn,sql.toString());
	}

	public int getArticlesCount(int cateItemId) {
		String sql = "";

		sql += String.format("SELECT COUNT(*) AS cnt ");
		sql += String.format("FROM article ");
		sql += String.format("WHERE displayStatus = 1 ");
		if (cateItemId != 0) {
			sql += String.format("AND cateItemId = %d ", cateItemId);
		}

		int count = DBUtil.selectRowIntValue(dbConn, sql);
		
		return count;
	}

	public String getBoardName(int cateItemId) {
		String sql = "";

		sql += String.format("SELECT name ");
		sql += String.format("FROM cateItem ");
		sql += String.format("WHERE id = %d;",cateItemId);
		
		String name = DBUtil.selectRowStringValue(dbConn, sql);
		
		return name;
	}
}