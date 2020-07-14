package com.sbs.java.blog.dao;

import java.sql.Connection;
import java.util.Map;

import com.sbs.java.blog.dto.Member;
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
		secSql.append(", updateDate = NOW()");
		secSql.append(", loginId = ?",loginId);
		secSql.append(", loginPw = ?",loginPw);
		secSql.append(", email = ?",email);
		secSql.append(", name = ?", name);
		secSql.append(", nickname = ?",nickname);
		
		
		return DBUtil.insert(dbConn, secSql);
	}

	public Boolean checkId(String loginId) {
		SecSql secSql = new SecSql();
		
		secSql.append("SELECT * FROM member");
		secSql.append(" WHERE loginId = ?" ,loginId);
		
		Map<String,Object> row = DBUtil.selectRow(dbConn, secSql);
		// 계정이 없으면 false
		if (row.isEmpty()) {
			return false; 
		}
		//계정이 있으면 true
		return true;
	}

	public Map<String, Object> getMemberByIdnPw(String loginId, String loginPw) {
		SecSql secSql = new SecSql();
		
		secSql.append("SELECT * ");
		secSql.append("FROM member ");
		secSql.append(" WHERE 1");
		secSql.append(" AND loginId = ?",loginId);
		secSql.append(" AND loginPw = ?",loginPw);
		
		Map<String, Object> member = DBUtil.selectRow(dbConn, secSql);
		
		return DBUtil.selectRow(dbConn, secSql);
	}

	public Member getMemberById(int id) {
		SecSql secSql = new SecSql();
		
		secSql.append("SELECT * FROM member ");
		secSql.append("WHERE id = ?",id);
	
		Member member = new Member (DBUtil.selectRow(dbConn, secSql));
		return member;
	}	
}