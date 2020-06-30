package com.sbs.java.blog.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.util.DBUtil;

@WebServlet("/s/article/detail")
public class ArticleDetailServlet extends HttpServlet {
	private Article getArticle(int id){

		String url = "jdbc:mysql://site32.iu.gy:3306/site32?serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true";
		String user = "site32";
		String password = "sbs123414";
		String driverName = "com.mysql.cj.jdbc.Driver";

		Connection connection = null;
		
		String sql = "";
				
		sql += String.format("SELECT *");
		sql += String.format(" FROM article");
		sql += String.format(" WHERE id = %s",id);
		Article article = null;
		
		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, user, password);
			
		Map<String,Object>row = DBUtil.selectRow(connection, sql);
		article = new Article(row);
		
		} catch (SQLException e) {
			System.err.printf("[SQL 예외] : %s\n", e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.printf("[드라이버 클래스 로딩 예외] : %s\n", e.getMessage());
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.err.printf("[SQL 예외, connection 닫기] : %s\n", e.getMessage());
				}
			}
		}
		return article;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		Article article = getArticle(id);
		request.setAttribute("article", article);
		request.getRequestDispatcher("/jsp/home/detail.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
