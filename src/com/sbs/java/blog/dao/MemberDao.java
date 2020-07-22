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

	public Member getMemberByIdnPw(String loginId, String loginPw) {
		SecSql secSql = new SecSql();
		
		secSql.append("SELECT * ");
		secSql.append("FROM member ");
		secSql.append(" WHERE 1");
		secSql.append(" AND loginId = ?",loginId);
		secSql.append(" AND loginPw = ?",loginPw);
		
		Member member = new Member(DBUtil.selectRow(dbConn, secSql));
//		Map<String, Object> member = DBUtil.selectRow(dbConn, secSql);
		
		return member;
	}

	public Member getMemberById(int id) {
		SecSql secSql = new SecSql();
		
		secSql.append("SELECT * FROM member ");
		secSql.append("WHERE id = ?",id);
	
		Member member = new Member (DBUtil.selectRow(dbConn, secSql));
		return member;
	}

	public Boolean checkEmail(String email) {
		SecSql sql = SecSql.from("SELECT * ");
		sql.append("FROM `member` ");
		sql.append("WHERE email = ?", email);
		Map<String,Object> row = DBUtil.selectRow(dbConn, sql);
		
		if(row.isEmpty()) {
			return false; // 없으면 false
		}
		return true; //있으면 true
	}

	public boolean nicknameCheck(String nickname) {
		SecSql sql = SecSql.from("SELECT * ");
		sql.append("FROM `member` ");
		sql.append("WHERE nickname = ?", nickname);
		Map<String,Object> row = DBUtil.selectRow(dbConn, sql);
		
		if(row.isEmpty()) {
			return false; // 없으면 false
		}
		return true; //있으면 true
	}

	public Member getMemberByEmailANDName(String email, String name) {
		SecSql sql = new SecSql();
		
		sql.append("SELECT * FROM member");
		sql.append(" WHERE email = ?",email);
		sql.append(" AND name = ?",name);
		
		Member member = new Member (DBUtil.selectRow(dbConn, sql));
		
		return member;
	}

	public Member modifyMemberInfo(String email, String nickname, String loginPw, int id) {
		SecSql sql = new SecSql();
		
		sql.append("UPDATA member");
		sql.append(" SET email = ?",email);
		sql.append(", nickname = ?",nickname);
		sql.append(", loginPw = ?",loginPw);
		sql.append("WHERE id = ?",id);
		
		Member member = new Member(DBUtil.selectRow(dbConn, sql));
		
		return member;
	}

	public String getMemberNickname(int id) {
		SecSql sql = new SecSql();
		
		sql.append("SELECT * FROM member");
		sql.append(" WHERE 1");
		sql.append(" AND id = ?",id);
		
		Member member = new Member(DBUtil.selectRow(dbConn, sql));
		
		String nickname = member.getNickname();
		
		return nickname;
	}	
}