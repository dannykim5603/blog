package com.sbs.java.blog.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.dto.CateItem;
import com.sbs.java.blog.util.DBUtil;

// Dao
public class ArticleDao extends Dao{
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


	public List<Article> getArticles(int page, int itemsInAPage,int cateItemId, String searchKeywordType, String searchKeyword) {
		
		String sql = "";

		int limitFrom = (page - 1) * itemsInAPage;

		sql += String.format("SELECT * ");
		sql += String.format("FROM article ");
		sql += String.format("WHERE displayStatus = 1 ");
		if (cateItemId != 0) {
			sql += String.format("AND cateItemId = %d ", cateItemId);
		}
		if (searchKeywordType.equals("title") && searchKeyword.length() > 0) {
			sql += String.format("AND title LIKE CONCAT('%%', '%s', '%%')", searchKeyword);
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
		
		sql.append(String.format("SELECT *, '김동연' AS extra__writer "));
		sql.append(String.format("FROM article "));
		sql.append(String.format("WHERE 1 "));
		sql.append(String.format("AND id = %d ", num ));
		sql.append(String.format("AND displayStatus = 1 "));
		
		return new Article(DBUtil.selectRow(dbConn,sql.toString()));
	}
	
	public void modify(int num, String title, String body) {
		StringBuilder sql = new StringBuilder();

		sql.append(String.format("UPDATE article "));
		sql.append(String.format("SET title = '" + title + "', "));
		sql.append(String.format("`body` = '" + body + "' "));
		sql.append(String.format("WHERE id = " + num + ";"));

		DBUtil.insert(dbConn,sql.toString());
	}

	public int delete(int num) {
		StringBuilder sql = new StringBuilder();

		sql.append(String.format("DELETE FROM article "));
		sql.append(String.format("WHERE id = " + num + ";"));
		
		return DBUtil.delete(dbConn,sql.toString());
	}



	public void deleteReply(int id) {
		StringBuilder sql = new StringBuilder();

		sql.append(String.format("DELETE FROM `articleReply` "));
		sql.append(String.format("WHERE id = " + id + ";"));
		
		DBUtil.delete(dbConn,sql.toString());
	}

	public void deleteBoardBycode(int id) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(String.format("DELETE FROM `board` "));
		sql.append(String.format("WHERE id = %d ;",id));
		DBUtil.delete(dbConn,sql.toString());
	}

	public int getArticlesCount(int cateItemId, String searchKeywordType, String searchKeyword) {
		String sql = "";

		sql += String.format("SELECT COUNT(*) AS cnt ");
		sql += String.format("FROM article ");
		sql += String.format("WHERE displayStatus = 1 ");
		
		if (cateItemId != 0) {
			sql += String.format("AND cateItemId = %d ", cateItemId);
		}

		if (searchKeywordType.equals("title") && searchKeyword.length() > 0) {
			sql += String.format("AND title LIKE CONCAT('%%', '%s', '%%')", searchKeyword);
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

	public List<CateItem> getCateItemsForPrint() {
		String sql = "";

		sql += String.format("SELECT * ");
		sql += String.format("FROM cateItem ");
		sql += String.format("WHERE 1 ");
		sql += String.format("ORDER BY id ASC ");

		List<Map<String, Object>> rows = DBUtil.selectRows(dbConn, sql);
		List<CateItem> cateItems = new ArrayList<>();

		for (Map<String, Object> row : rows) {
			cateItems.add(new CateItem(row));
		}

		return cateItems;
	}

	public CateItem getCateItem(int cateItemId) {
		String sql = "";

		sql += String.format("SELECT * ");
		sql += String.format("FROM cateItem ");
		sql += String.format("WHERE 1 ");
		sql += String.format("AND id = %d ", cateItemId);

		return new CateItem(DBUtil.selectRow(dbConn, sql));
	}

	public void doWrite(int displayStatus, int cateItemId, String title, String body) {
		String sql = "";
		
		sql += String.format("INSERT INTO article ");
		sql += String.format("SET regDate = NOW(), ");
		sql += String.format("updateDate = NOW(), ");
		sql += String.format("displayStatus = %d, ",displayStatus);
		sql += String.format("cateItemId = %d, ",cateItemId);
		sql += String.format("title = %s, ",title);
		sql += String.format("body = %s ",body);
		
		DBUtil.insert(dbConn, sql);
	}
}