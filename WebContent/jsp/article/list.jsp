<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.sbs.java.blog.dto.Article"%>
<%@page import="com.sbs.java.blog.service.ArticleService"%>
<%@page import="com.sbs.java.blog.service.MemberService"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>

<%@ include file="/jsp/part/head.jspf"%>
<style>
h2 {
	color: white;
}

.table-box {
	color: #444958;
}

.table-box>table {
	width: 100%;
	border-collapse: collapse;
	color: white;
	font-size: .9rem;
}
.table-box> table th {
	background-color: #444958;
}

.table-box>table th, .table-box>table td {
	border: 2px double #444958;
	padding: 10px;
}

.article-list-box-1 td {
	text-align: center;
}
.page-box{
	padding-top:25px;
}
.page-box>ul>li>a {
	padding: 0 10px;
	color: white;
}

.page-box>ul>li:hover>a {
	color: #444958;
}

.page-box>ul>li.current>a {
	color: crimson;
}

.search-box {
	margin-top: 20px;
}
</style>
<%
	MemberService memberService = (MemberService)request.getAttribute("memberService");
	List<Article> articles = (List<Article>) request.getAttribute("articles");
	int totalPage = (int) request.getAttribute("totalPage");
	int paramPage = (int) request.getAttribute("page");
	String cateItemName = (String) request.getAttribute("cateItemName");
	String memberNickname = new String();
%>
<h2 class="con" style="text-align: center"> <%=cateItemName%> 🤗 </h2>
<h2 class="con" style="text-align: center">총 게시물 수 : ${totalCount}</h2>
<div class="article-list-box-1 con table-box">
	<table>
		<colgroup>
			<col width="25">
			<col width="120">
			<col width="120">
			<col width="900">
		</colgroup>
		<thead>
			<tr>
				<th>ID</th>
				<th>등록날짜</th>
				<th>갱신날짜</th>
				<th>제목</th>
				<th>작성자</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (Article article : articles) {
				memberNickname = (String)memberService.getMemberNickname(article.getMemberId());
			%>
			<tr>
				<td><%=article.getId()%></td>
				<td><%=article.getRegDate()%></td>
				<td><%=article.getUpdateDate()%></td>
				<td class=" text-align-left ">
					<a href="./detail?id=<%=article.getId()%>"><%=article.getTitle()%></a>
				</td>
				<td><%=memberNickname %></td>
				<td><%=article.getHit()%></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
</div>

<div class="con page-box">
	<ul class="flex flex-jc-c">
		<%
			for (int i = 1; i <= totalPage; i++) {
		%>
		<li class="<%=i == paramPage ? "current" : ""%>"><a
			href="?cateItemId=${param.cateItemId}&searchKeywordType=${param.searchKeywordType}&searchKeyword=${param.searchKeyword}&page=<%=i%>" class="block"><%=i%></a></li>
		<%
			}
		%>
	</ul>
</div>

<div class="con search-box flex flex-jc-c">

	<form action="${pageContext.request.contextPath}/s/article/list">
		<input type="hidden" name="page" value="1" />
		<input type="hidden" name="cateItemId" value="${param.cateItemId}" />
		<input type="hidden" name="searchKeywordType" value="title" /> 
		<input type="text" name="searchKeyword" value="${param.searchKeyword}" placeholder =" 검색어를 입력해 주세요."/>
		<button type="submit">검색</button>
	</form>
	
</div>


<%@ include file="/jsp/part/foot.jspf"%>

