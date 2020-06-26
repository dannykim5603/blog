package com.sbs.java.blog.controller;

import com.sbs.java.blog.dto.Board;
import com.sbs.java.blog.dto.Dto;
import com.sbs.java.blog.dto.Member;
import com.sbs.java.blog.dto.Article;

// Session
// 현재 사용자가 이용중인 정보
// 이 안의 정보는 사용자가 프로그램을 사용할 때 동안은 계속 유지된다.
public class Session {
	private Member loginedMember;
	private Board currentBoard;
	private Article currentArticle;

	public Member getLoginedMember() {
		return loginedMember;
	}

	public void setLoginedMember(Member loginedMember) {
		this.loginedMember = loginedMember;
	}

	public Board getCurrentBoard() {
		return currentBoard;
	}

	public void setCurrentBoard(Board currentBoard) {
		this.currentBoard = currentBoard;
	}

	public boolean isLogined() {
		return loginedMember != null;
	}

	public Article getCurrentArticle() {
		return currentArticle;
	}

	public void setCurrentArticle(Article currentArticle) {
		this.currentArticle = currentArticle;
	}

}