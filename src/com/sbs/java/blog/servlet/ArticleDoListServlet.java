package com.sbs.java.blog.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ArticleDoListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.setCharacterEncoding("text/html; charset=UTF-8");
//
//		String url = "jdbc:mysql://localhost:3306/blog?serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true";
//		String user = "sbsst";
//		String password = "sbs123414";
//		String driverName = "com.mysql.cj.jdbc.Driver";
//		
//		
//		String title = "title";
//		String body = "body";
//				
//		
//		Connection connection = null;
//		String sql = "";
//		sql += String.format("INSERT INTO article");
//		sql += String.format("SET regDate = NOW()");
//		sql += String.format(", updateDAte= NOW()");
//		sql += String.format(", title = '%s'",title);
//		sql += String.format(", body = '%s'",body);
//
//		try {
//			Class.forName(driverName);
//			connection = DriverManager.getConnection(url, user, password);
//			response.getWriter().append("연결되었습니다.ㅋㅋㅋㅋㅋ");
//		} catch (SQLException e) {
//			System.err.printf("[SQL 예외] : %s\n", e.getMessage());
//		} catch (ClassNotFoundException e) {
//			System.err.printf("[드라이버 클래스 로딩 예외] : %s\n", e.getMessage());
//		}
//		finally {
//			if (connection != null) {
//				try {
//					connection.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
//
//	public List<Map<String, Object>> selectRows(String sql) {
//			List<Map<String, Object>> rows = new ArrayList<>();
//
//			try {
//				Statement stmt = connection.createStatement();
//				ResultSet rs = stmt.executeQuery(sql);
//				ResultSetMetaData metaData = rs.getMetaData();
//				int columnSize = metaData.getColumnCount();
//
//				while (rs.next()) {
//					Map<String, Object> row = new HashMap<>();
//
//					for (int columnIndex = 0; columnIndex < columnSize; columnIndex++) {
//						String columnName = metaData.getColumnName(columnIndex + 1);
//						Object value = rs.getObject(columnName);
//
//						if (value instanceof Long) {
//							int numValue = (int) (long) value;
//							row.put(columnName, numValue);
//						} else if (value instanceof Timestamp) {
//							String dateValue = value.toString();
//							dateValue = dateValue.substring(0, dateValue.length() - 2);
//							row.put(columnName, dateValue);
//						} else {
//							row.put(columnName, value);
//						}
//					}
//
//					rows.add(row);
//				}
//			} catch (SQLException e) {
//				System.err.printf("[SQL 예외, SQL : %s] : %s\n", sql, e.getMessage());
//			}
//
//			return rows;
//		}
//
//	public List<Article> getArticles() {
//		StringBuilder sb = new StringBuilder();
//
//		sb.append(String.format("SELECT * "));
//		sb.append(String.format("FROM `article` "));
//		sb.append(String.format("WHERE 1 "));
//		sb.append(String.format("ORDER BY id DESC "));
//
//		List<Article> articles = new ArrayList<>();
//		List<Map<String, Object>> rows = selectRows(sb.toString());
//
//		for (Map<String, Object> row : rows) {
//			articles.add(new Article(row));
//		}
//
//		return articles;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
