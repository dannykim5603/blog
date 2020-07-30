package com.sbs.java.blog.dto;

import java.util.Map;

public class CateItem extends Dto{
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CateItem(Map<String, Object> row) {
		super(row);
		
		this.name = (String) row.get("name");
	}

	@Override
	public String toString() {
		return String.format("CateItem [name=%s, getId()=%s, getRegDate()=%s, getUpdateDate()=%s, getExtra()=%s]", name,
				getId(), getRegDate(), getUpdateDate(), getExtra());
	}

}
