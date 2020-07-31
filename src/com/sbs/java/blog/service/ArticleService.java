package com.sbs.java.blog.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sbs.java.blog.dao.ArticleDao;
import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.dto.ArticleReply;
import com.sbs.java.blog.dto.CateItem;
import com.sbs.java.blog.dto.Member;
import com.sbs.java.blog.util.Util;

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

	public Article detail(int num, int loginedMemberId) {
		Article article = articleDao.detail(num);
		
//		updateArticleExtraDataForPrint(article,loginedMemberId);
		
		return article;
	}


//	private void updateArticleExtraDataForPrint(Article article, int loginedMemberId) {
//		boolean deleteAvailable = Util.isSuccess(getCheckRsDeleteAvailable(article,loginedMemberId));
//		article.getExtra().put("deleteAvailable", deleteAvailable);
//		
//		boolean modifyAvailable = Util.isSuccess(getCheckRsModifyAvailable(article,loginedMemberId));
//		article.getExtra().put("modifyAvailable", modifyAvailable);
//		
//	}

	private Map<String, Object> getCheckRsModifyAvailable(Article article, int actorId) {
		return getReplyCheckRsDeleteAvailable(article, actorId);
	}
	
	
	private Map<String, Object> getReplyCheckRsDeleteAvailable(Article article, int actorId) {
		Map<String, Object> rs = new HashMap<>();

		if (article == null) {
			rs.put("resultCode", "F-1");
			rs.put("msg", "존재하지 않는 게시물 입니다.");

			return rs;
		}

		if (article.getMemberId() != actorId) {
			rs.put("resultCode", "F-2");
			rs.put("msg", "권한이 없습니다.");

			return rs;
		}

		rs.put("resultCode", "S-1");
		rs.put("msg", "작업이 가능합니다.");

		return rs;
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

	public void modifyReply(int replyId, String body) {
		articleDao.modifyReply(replyId,body);
	}

	public boolean isReplyDeletable(int loginedMemberId, int replyId) {
		return articleDao.isReplyDeletable(loginedMemberId,replyId);
	}

	public ArticleReply getArticleReplyByReplyId(int replyId) {
		return articleDao.getArticleReplyByReplyId(replyId);
	}

}