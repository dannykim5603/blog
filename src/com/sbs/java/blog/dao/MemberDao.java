package com.sbs.java.blog.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.dto.CateItem;
import com.sbs.java.blog.util.DBUtil;

// Dao
public class MemberDao extends Dao{
	private Connection dbConn;

	public MemberDao(Connection dbConn) {
		this.dbConn = dbConn;
	}

	public int join(String loginId, String loginPw, String email, String name, String nickname) {
		String sql = "";
		
		sql += String.format("INSERT INTO member");
		sql += String.format(" SET regDate = NOW()");
		sql += String.format(", loginId = '%s'",loginId);
		sql += String.format(", loginPw = '%s'",loginPw);
		sql += String.format(", email = '%s'",email);
		sql += String.format(", name = '%s'", name);
		sql += String.format(", nickname = '%s'",nickname);
		
		
		return DBUtil.insert(dbConn, sql);
	}

	public Boolean checkPw(String pw1, String pw2) {
		System.out.println(pw1+"<-pw1 pw2->"+pw2);
		if (pw1.equals(pw2)) {
			return true;
		}
		return false;
	}

	public Boolean checkId(String loginId) {
		String sql = "";
		
		sql += String.format("SELECT * FROM member");
		sql += String.format("WHERE id ='%s",loginId);
		
		return null;
	}

	
}