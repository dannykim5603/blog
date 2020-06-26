package com.sbs.java.blog.dto;

import java.util.Map;

import com.sbs.java.blog.util.Util;

// DTO
public abstract class Dto {
	private int id;
	private String regDate;

	
	public Dto(Map<String, Object> row) {
		this.id = (int)row.get("id");
		this.regDate = (String)row.get("regDate");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public Dto() {
		this(0);
	}

	public Dto(int id) {
		this(id, Util.getNowDateStr());
	}

	public Dto(int id, String regDate) {
		this.id = id;
		this.regDate = regDate;
	}
}