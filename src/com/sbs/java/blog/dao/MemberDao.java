package com.sbs.java.blog.dao;
import java.sql.Connection;
import java.util.Map;

import com.sbs.java.blog.db.DBConnection;
import com.sbs.java.blog.dto.Member;
import com.sbs.java.blog.factory.Factory;

public class MemberDao {
	private Connection dbConn;

	public MemberDao(Connection dbConn) {
		this.dbConn = dbConn;
	}

	public Member getMemberByLoginIdAndLoginPw(String loginId, String loginPw) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * "));
		sb.append(String.format("FROM `member` "));
		sb.append(String.format("WHERE 1 "));
		sb.append(String.format("AND `loginId` = '%s' ", loginId));
		sb.append(String.format("AND `loginPw` = '%s' ", loginPw));

		Map<String, Object> row = dbConnection.selectRow(sb.toString());
		
		if ( row.isEmpty() ) {
			return null;
		}
		
		return new Member(row);
	}

	public Member getMemberByLoginId(String loginId) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * "));
		sb.append(String.format("FROM `member` "));
		sb.append(String.format("WHERE 1 "));
		sb.append(String.format("AND loginId = '%s' ", loginId));

		Map<String, Object> memberRow = dbConnection.selectRow(sb.toString());

		if (memberRow.isEmpty()) {
			return null;
		}

		return new Member(memberRow);
	}

	public Member getMember(int id) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * "));
		sb.append(String.format("FROM `member` "));
		sb.append(String.format("WHERE 1 "));
		sb.append(String.format("AND `id` = '%d' ", id));

		Map<String, Object> row = dbConnection.selectRow(sb.toString());
		
		if ( row.isEmpty() ) {
			return null;
		}
		
		return new Member(row);
	}

	public int save(Member member) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("INSERT INTO member "));
		sb.append(String.format("SET regDate = '%s' ", member.getRegDate()));
		sb.append(String.format(", loginId = '%s' ", member.getLoginId()));
		sb.append(String.format(", loginPw = '%s' ", member.getLoginPw()));
		sb.append(String.format(", `name` = '%s' ", member.getName()));

		return dbConnection.insert(sb.toString());
	}
}