package com.sbs.java.blog.dto;

import java.util.Map;

public class Member extends Dto {
	private String loginId;
	private String loginPw;
	private String updateDate;
	private String name;
	private String nickname;
	private String email;

	public Member(Map<String, Object> row) {
		super(row);
		this.loginId = (String) row.get("loginId");
		this.loginPw = (String) row.get("loginPw");
		this.name = (String) row.get("name");
		this.updateDate = (String) row.get("updateDate");
		this.nickname = (String) row.get("nickname");
		this.email = (String) row.get("email");
		
	}
	@Override
	public String toString() {
		return String.format("Member [loginId=%s, loginPw=%s, name=%s, getId()=%s, getRegDate()=%s]", loginId, loginPw,
				name, getId(), getRegDate());
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getLoginPw() {
		return loginPw;
	}

	public void setLoginPw(String loginPw) {
		this.loginPw = loginPw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}