package com.sbs.java.blog.dto;

import java.util.Map;

public class ArticleReply extends Dto {
	private String body;
	private String articleId;
	private String memberId;
	private String nickname;

	public ArticleReply(Map<String, Object> row) {
		super(row);
		this.articleId = (String)row.get("articleId");
		this.body = (String) row.get("body");
		this.memberId = (String) row.get("memberId");
		this.nickname = (String) row.get("nickname");
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	public String getBodyForXTemplate() {
		return body.replaceAll("(?i)script", "<!--REPLACE:script-->");
	}

	@Override
	public String toString() {
		return String.format(
				"ArticleReply [body=%s, articleId=%s, memberId=%s, nickname=%s, getId()=%s, getRegDate()=%s, getUpdateDate()=%s, getExtra()=%s]",
				body, articleId, memberId, nickname, getId(), getRegDate(), getUpdateDate(), getExtra());
	}

}