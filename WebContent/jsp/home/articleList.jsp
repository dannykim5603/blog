<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<%@page import="java.util.ArrayList"%>
	<%@page import="java.util.List"%>
	<%@page import="com.sbs.java.blog.dto.Article"%>
	<%@page import="java.util.HashMap"%>
	<%@page import="java.util.Map"%>

<%@ include file="/jsp/part/head.jspf"%>
<h2> 아티클 리스트 </h2>
	
	<table class="list" border= "1">
	<tr>
		<th>id</th>
		<th>regDate</th>
		<th>updateDate</th>
		<th>title</th>
		<th>body</th>
	</tr>
<%
		List<Article> articles = (List<Article>)request.getAttribute("articles");
		for (Article article : articles){
%>

		<tr>
			<td>article.getId()</td>
			<td>article.getRegDate</td>
			<td>article.getTitle</td>
			<td>article.getBody</td>
		</tr>
<%
		}
%>
</table>
<%@ include file="/jsp/part/foot.jspf"%>

