package com.sbs.java.blog.dto;

import java.util.Map;

public class ArticleReply extends Dto {
	private String updateDate;
	private String body;
	private String articleId;
	private String memberId;
	private String nickname;

	public ArticleReply(Map<String, Object> row) {
		super(row);
		this.articleId = (String)row.get("articleId");
		this.updateDate = (String) row.get("updateDate");
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

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
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
		return String.format("%n게시번호 : %d 게시 날짜 : %s 업데이트 날짜 : %s%n내용 : %s%n%n 조회수 : %d%n%n 게시자 : %s%n 게시물번호: %s%n" + super.toString(),
				getId(), getRegDate(), getUpdateDate(), body,memberId,articleId);
	}

}