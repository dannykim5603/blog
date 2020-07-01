package com.sbs.java.blog.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.controller.ArticleController;
import com.sbs.java.blog.controller.Controller;
import com.sbs.java.blog.controller.MemberController;
import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.util.DBUtil;

@WebServlet("/s/*")
public class DispatcherServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		//DB커넥터 로딩 시작
		String driverName = "com.mysql.cj.jdbc.Driver";
		
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			System.err.printf("[ClassNotFoundException 예외, %s]\n",e.getMessage());
			resp.getWriter().append("DB 드라이버 클래스 로딩 실패");
			return;
		}
		
		//DB 커넥터 로딩 성공
		
		//DB 접속 시작
		String url = "jdbc:mysql://site32.iu.gy:3306/site32?serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true";
		String user = "site32";
		String password = "sbs123414";
		driverName = "com.mysql.cj.jdbc.Driver";

		Connection dbConn = null;

		try {
			Class.forName(driverName);
			dbConn = DriverManager.getConnection(url, user, password);
			
			String contextPath = req.getContextPath();
			String requestURI = req.getRequestURI();
			String actionStr = requestURI.replace(contextPath + "/s/", "");
			String[] actionStrBits = actionStr.split("/");

			String controllerName = actionStrBits[0];
			String actionMethodName = actionStrBits[1];
			System.out.println(contextPath);
			System.out.println(requestURI);
			System.out.println("actionStr:"+actionStr);
			System.out.printf("controllerName : %s\n", controllerName);
			System.out.printf("actionMethodName : %s\n", actionMethodName);

			Controller controller = null;

			switch (controllerName) {
			case "article":
				controller = new ArticleController(dbConn);
			case "member":
				controller = new MemberController(dbConn);
				
			}
			if (controller != null) {
				String viewPath = controller.doAction(actionMethodName, req, resp);
				System.out.println(viewPath);
				viewPath = "/jsp/"+ viewPath +".jsp";
				System.out.println(viewPath);
				req.getRequestDispatcher(viewPath);
				if (viewPath.equals("")) {
					resp.getWriter().append("ERROR, CODE 1");
				}
				req.getRequestDispatcher(viewPath).forward(req, resp);
			}
			else {
				resp.getWriter().append("존재하지 않는 페이지 입니다.");
			}
		
		} catch (SQLException e) {
			System.err.printf("[SQL 예외] : %s\n", e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.printf("[드라이버 클래스 로딩 예외] : %s\n", e.getMessage());
		} catch (Exception e) {
			System.err.printf("[기타 Exception 예외] : %s\n", e.getMessage());
		} finally {
			if (dbConn != null) {
				try {
					dbConn.close();
				} catch (SQLException e) {
					System.err.printf("[SQL 예외, connection 닫기] : %s\n", e.getMessage());
					resp.getWriter().append("DB연결 닫기 실패");
				}
			}
		}
		
		
		

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
