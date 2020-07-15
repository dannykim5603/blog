<%@page import="com.sbs.java.blog.dto.Article"%>
<%@page import="com.sbs.java.blog.dto.ArticleReply"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/jsp/part/head.jspf"%>

<%
	List<ArticleReply> replies = (List<ArticleReply>) request.getAttribute("articleReplies");
Article article = (Article) request.getAttribute("article");
%>
<!-- 하이라이트 라이브러리 추가, 토스트 UI 에디터에서 사용됨 -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/highlight.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/styles/default.min.css">

<!-- 하이라이트 라이브러리, 언어 -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/css.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/javascript.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/xml.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/php.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/php-template.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/sql.min.js"></script>

<!-- 토스트 UI 에디터, 자바스크립트 코어 -->
<script
	src="https://uicdn.toast.com/editor/latest/toastui-editor-viewer.min.js"></script>

<!-- 토스트 UI 에디터, 신택스 하이라이트 플러그인 추가 -->
<script
	src="https://uicdn.toast.com/editor-plugin-code-syntax-highlight/latest/toastui-editor-plugin-code-syntax-highlight-all.min.js"></script>

<!-- 토스트 UI 에디터, CSS 코어 -->
<link rel="stylesheet"
	href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />

<style>
.article-box {
	border: 2px solid #444958;
	border-radius: 0px 80px;
	margin-top: 20px;
	padding: 30px;
}

.article-box>.article-table {
	border: 2px solid #444958;
}

.article-box>.modify-butt-box {
	display: flex;
	justify-content: flex-end;
}

.article-box>.articleReplyForm {
	
}

.article-box>.articleReply-box>.articleReplyForm>.reply-submit {
	height: 50px;
}
.article-box> .util-butt-box {
	display:flex;
	justify-content:flex-end;
}
.article-box> .util-butt-box > button{
	margin : 10px;
}
</style>
<!-- 상세 -->
<div class="con article-box" style="background-color: white">

	<h1><%=article.getTitle()%></h1>
	<h2><%=article.getRegDate()%></h2>
	<h3>
		조회수 :
		<%=article.getHit()%></h3>
	<script type="text/x-template" id="origin1"><%=article.getBody()%></script>
	<div id="viewer1"></div>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resource/js/article/detail.js "></script>
	<!-- 댓글 -->
	<div class="articleReply-box">
		<div class="reply-box">
			<%
				if (replies != null) {
			%>
			<%
				for (ArticleReply articleReplies : replies) {
			%>
			<%=articleReplies.getId()%>. <%=articleReplies.getNickname()%> <%=articleReplies.getBody()%><br>
			<%
				}
			}
			%>
		</div>
		<form action="writeArticleReply" method="POST" class="articleReplyForm">
			<input type="hidden" name="id" value=<%=article.getId()%> />
			<div class="form-row">
				<div class="label" style="margin-right: 30px;">댓글</div>
				<div class="input">
					<textarea name="body" rows="3px" cols="100%"></textarea>
				</div>
			</div>
			<div class="form-row">
			<div class ="label"></div>
				<div class="input submit-box">
					<input class="reply-submit" style="margin-left: 10px" type="submit" value="작성" />
					<a style="border: 3px solid #444958;background-color:#444958; color:white; border-radius: 15px;" class="cancel" href="detail?id=${param.id}">취소</a>
				</div>
			</div>
		</form>
	</div>
	<div class="util-butt-box">
		<button type="button" class="modify-button" value="modify"
			onclick="location.href='../article/modify?id=${param.id}'">수정
		</button>
		<button type="button" class="delete-button" value="delete"
			onclick="location.href='../article/delete?id=${param.id}'">삭제
		</button>
	</div>
</div>

<%@ include file="/jsp/part/foot.jspf"%>

