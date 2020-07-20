package com.sbs.java.blog.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.dto.ArticleReply;
import com.sbs.java.blog.dto.CateItem;
import com.sbs.java.blog.dto.Member;
import com.sbs.java.blog.util.DBUtil;
import com.sbs.java.blog.util.SecSql;

// Dao
public class ArticleDao extends Dao{
	private Connection dbConn;

	public ArticleDao(Connection dbConn) {
		this.dbConn = dbConn;
	}

	public List<Article> getArticles(int page, int itemsInAPage,int cateItemId, String searchKeywordType, String searchKeyword) {
		SecSql sql = new SecSql();
		int limitFrom = (page - 1) * itemsInAPage;

		sql.append("SELECT *, '김대니' AS extra__ ");
		sql.append("FROM article");
		sql.append("WHERE displayStatus = 1");
		if (cateItemId != 0) {
			sql.append("AND cateItemId = ?", cateItemId);
		}
		if (searchKeywordType.equals("title") && searchKeyword.length() > 0) {
			sql.append("AND title LIKE CONCAT('%', ?, '%')", searchKeyword);
		}
		sql.append("ORDER BY id DESC ");
		sql.append("LIMIT ?, ? ", limitFrom, itemsInAPage);

		List<Map<String, Object>> rows = DBUtil.selectRows(dbConn, sql);
		List<Article> articles = new ArrayList<>();

		for (Map<String, Object> row : rows) {
			articles.add(new Article(row));
		}

		return articles;
	}

	public Article detail(int num) {
		SecSql secSql = new SecSql();
		
		secSql.append("SELECT *, '김동연' AS extra__writer ");
		secSql.append("FROM article ");
		secSql.append("WHERE 1 ");
		secSql.append("AND id = ? ", num );
		secSql.append("AND displayStatus = 1 ");

		return new Article(DBUtil.selectRow(dbConn,secSql));
	}
	
	public int delete(int num) {
		SecSql secSql = new SecSql();

		secSql.append("DELETE FROM article ");
		secSql.append("WHERE id = ? ",num);
		
		return DBUtil.delete(dbConn,secSql);
	}

	public void deleteReply(int id) {
		SecSql secSql = new SecSql();

		secSql.append("DELETE FROM `articleReply` ");
		secSql.append("WHERE id = ? ",id);
		
		DBUtil.delete(dbConn,secSql);
	}

	public void deleteBoardBycode(int id) {
		SecSql secSql = new SecSql();
		
		secSql.append("DELETE FROM `board` ");
		secSql.append("WHERE id = ? ;",id);
		DBUtil.delete(dbConn,secSql);
	}

	public int getArticlesCount(int cateItemId, String searchKeywordType, String searchKeyword) {
		SecSql secSql = new SecSql();

		secSql.append("SELECT COUNT(*) AS cnt ");
		secSql.append("FROM article ");
		secSql.append("WHERE displayStatus = 1 ");
		
		if (cateItemId != 0) {
			secSql.append("AND cateItemId = ? ", cateItemId);
		}

		if (searchKeywordType.equals("title") && searchKeyword.length() > 0) {
			secSql.append("AND title LIKE CONCAT('%', ?, '%')", searchKeyword);
		}

		int count = DBUtil.selectRowIntValue(dbConn, secSql);
		
		return count;
	}

	public String getBoardName(int cateItemId) {
		SecSql secSql = new SecSql();

		secSql.append("SELECT name ");
		secSql.append("FROM cateItem ");
		secSql.append("WHERE id = ?;",cateItemId);
		
		String name = DBUtil.selectRowStringValue(dbConn, secSql);
		
		return name;
	}

	public List<CateItem> getCateItemsForPrint() {
		SecSql secSql = new SecSql();

		secSql.append("SELECT * ");
		secSql.append("FROM cateItem ");
		secSql.append("WHERE 1 ");
		secSql.append("ORDER BY id ASC ");

		List<Map<String, Object>> rows = DBUtil.selectRows(dbConn, secSql);
		List<CateItem> cateItems = new ArrayList<>();

		for (Map<String, Object> row : rows) {
			cateItems.add(new CateItem(row));
		}

		return cateItems;
	}

	public CateItem getCateItem(int cateItemId) {
		SecSql secSql = new SecSql();

		secSql.append("SELECT * ");
		secSql.append("FROM cateItem ");
		secSql.append("WHERE 1 ");
		secSql.append("AND id = ? ", cateItemId);

		return new CateItem(DBUtil.selectRow(dbConn, secSql));
	}

	public int doWrite(int displayStatus, int cateItemId, String title, String body, int loginedMemberId) {
		SecSql secSql = new SecSql();
		
		secSql.append("INSERT INTO article ");
		secSql.append("SET regDate = NOW() ");
		secSql.append(", updateDate = NOW() ");
		secSql.append(", displayStatus = ? ",displayStatus);
		secSql.append(", cateItemId = ? ",cateItemId);
		secSql.append(", title = ? ",title);
		secSql.append(", body = ? ",body);
		secSql.append(", memberId = ? ",loginedMemberId);
		
		return DBUtil.insert(dbConn, secSql);
	}

	public void increaseHit(int id) {
		SecSql sql = SecSql.from("UPDATE article");
		sql.append(" SET hit = hit + 1");
		sql.append(" WHERE id = ?", id);

		DBUtil.update(dbConn, sql);
	}

	public int modify(String title, String body, int displayStatus, int cateItemId, int id) {
		SecSql secSql = new SecSql();
		
		secSql.append("UPDATE article ");
		secSql.append("SET updateDate = NOW() ");
		secSql.append(", title = ? ",title);
		secSql.append(", body = ? ",body);
		secSql.append(", cateItemId = ? ",cateItemId);
		secSql.append(", displayStatus = ? ",displayStatus);
		secSql.append("WHERE id = ? ",id);
		
		return DBUtil.update(dbConn,secSql);
	}

	public Article getArticleById(int id) {
		SecSql secSql = new SecSql();
		
		secSql.append("SELECT * FROM article ");
		secSql.append("WHERE id = ?",id);
		
		Article article = new Article(DBUtil.selectRow(dbConn, secSql));
		return article;
	}
	
	public void writeArticleReply(String articleReply, Member member, int articleId) {
		SecSql secSql = new SecSql();
		
		secSql.append("INSERT INTO articleReply");
		secSql.append(" SET regDate = NOW()");
		secSql.append(", updateDate = NOW()");
		secSql.append(", body = ?",articleReply);
		secSql.append(", nickname = ?", member.getNickname());
		secSql.append(", memberId = ?", member.getId());
		secSql.append(", articleId = ?",articleId);
		
		DBUtil.insert(dbConn, secSql);
	}

	public List<ArticleReply> getArticleReplyByArticleId(int articleId) {
		SecSql secSql = new SecSql();
		
		secSql.append("SELECT * FROM articleReply");
		secSql.append(" WHERE articleId = ?",articleId);
		
		List<Map<String,Object>> rows = DBUtil.selectRows(dbConn, secSql);
		List<ArticleReply> articleReplies = new ArrayList<>();
		
		for (Map<String,Object> row : rows ) {
			articleReplies.add(new ArticleReply(row));
		}
		
		return articleReplies;
	}

	public ArticleReply getArticleReplyById(int replyId) {
		SecSql secSql = new SecSql();
		
		secSql.append("SELECT * FROM articleReply");
		secSql.append(" WHERE id = ?",replyId);
		
		ArticleReply articleReply = new ArticleReply(DBUtil.selectRow(dbConn, secSql));
		
		return articleReply;
	}
}