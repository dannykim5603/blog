package com.sbs.java.blog.dao;

import java.sql.Connection;
import java.util.Map;

import com.sbs.java.blog.util.DBUtil;
import com.sbs.java.blog.util.SecSql;

// Dao
public class MemberDao extends Dao{
	private Connection dbConn;

	public MemberDao(Connection dbConn) {
		this.dbConn = dbConn;
	}

	public int join(String loginId, String loginPw, String email, String name, String nickname) {
		SecSql secSql = new SecSql();
		
		secSql.append("INSERT INTO member");
		secSql.append(" SET regDate = NOW()");
		secSql.append(", loginId = '%s'",loginId);
		secSql.append(", loginPw = '%s'",loginPw);
		secSql.append(", email = '%s'",email);
		secSql.append(", name = '%s'", name);
		secSql.append(", nickname = '%s'",nickname);
		
		
		return DBUtil.insert(dbConn, secSql);
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
		sql += String.format(" WHERE loginId ='%s'" ,loginId);
		
		Map<String,Object> row = DBUtil.selectRow(dbConn, sql);
		// 계정이 없으면 false
		if (row.isEmpty()) {
			return false; 
		}
		//계정이 있으면 true
		return true;
	}
}