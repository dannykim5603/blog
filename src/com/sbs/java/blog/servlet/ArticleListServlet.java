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

import org.omg.CORBA.Request;

import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.util.DBUtil;

@WebServlet("/s/article/list")
public class ArticleListServlet extends HttpServlet {
	private List<Article> getArticles(int cateItemId, int pageNum){

		String url = "jdbc:mysql://site32.iu.gy:3306/site32?serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true";
		String user = "site32";
		String password = "sbs123414";
		String driverName = "com.mysql.cj.jdbc.Driver";

		Connection connection = null;
		int pageNumber = (pageNum * 5) - 5; 
		
		String sql = "";
				
		sql += String.format("SELECT *");
		sql += String.format(" FROM article");
		sql += String.format(" WHERE displayStatus = 1 AND");
		sql += String.format(" cateItemId = %s",cateItemId);
		sql += String.format(" ORDER BY id DESC");
		sql += String.format(" LIMIT %s,5",pageNumber);

		List<Article> articles = new ArrayList<>();
		

		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, user, password);
			
			List<Map<String,Object>>rows = DBUtil.selectRows(connection, sql);
			
			for (Map<String, Object> row : rows) {
				articles.add(new Article(row));
			}
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
		return articles;
	}
	
	private int getCount(int cateItemId, int page){

		String url = "jdbc:mysql://site32.iu.gy:3306/site32?serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true";
		String user = "site32";
		String password = "sbs123414";
		String driverName = "com.mysql.cj.jdbc.Driver";

		Connection connection = null;
				
		String sql = "";
				
		sql += String.format("SELECT COUNT(*)");
		sql += String.format(" FROM article");
		sql += String.format(" WHERE cateItemId = %s",cateItemId);
		
		int totalCount = 0;
		
		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, user, password);
			
			Map<String,Object> row = DBUtil.selectRow(connection, sql);
			System.out.println(row);
			row.value()
			
//			totalCount = Integer.parseInt(row.toString());
			
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
		return totalCount;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		int cateItemId = Integer.parseInt(request.getParameter("cateItemId"));
		int pageNum = Integer.parseInt(request.getParameter("page"));
		List<Article> articles = getArticles(cateItemId,pageNum);
		request.setAttribute("articles", articles);
		int totalCount = getCount(cateItemId,pageNum);
		request.setAttribute("totalCount", totalCount);
		request.getRequestDispatcher("/jsp/home/articleList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
