<%@page import="com.sbs.java.blog.dto.Article"%>
<%@page import="com.sbs.java.blog.dto.ArticleReply"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/jsp/part/head.jspf"%>

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

	<h1>${article.title }</h1>
	<h2>작성자 : ${memberNickname } </h2>
	<h3>
		조회수 :
		${article.hit }</h3>
	<h3>작성일 : ${article.regDate }</h3>
	<script type="text/x-template">${article.getBodyForXTemplate}</script>
	<div class="toast-editor toast-editor-viewer"></div>
	<!--  
	<script type="text/x-template">${article.bodyForXTemplate}</script>
					<div id="viewer1" class="toast-editor toast-editor-viewer"></div>
	<script type="text/x-template" id="origin1" style="display: none;">${article.getBodyForXTemplate}</script>
	<div id="viewer1"></div>
	-->
	<!-- 댓글 -->
	<div class="articleReply-box"  style="margin-top:20px">
		<div class="reply-box">
			<c:when test="${not empty replies }">
				<c:forEach items="${relpies }" var="articleReply">
					${articleReply.id }.  ${articleReply.nickname }  ${articleReply.body }
				<button class="reply-modify" type="button" onclick="cmUpdateOpen(${comment})">수정</button>
				<button class="reply-delete" type="button" onclick="">삭제</button><br>
				</c:forEach>
			</c:when>
		</div>
		<form action="writeArticleReply" method="POST" class="articleReplyForm" style="margin-top:20px;">
			<input type="hidden" name="id" value="${article.id }" />
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
		<input type="hidden" name="id" value="${article.id }"/>
		<button type="button" class="modify-button" value="modify"
			onclick="location.href='../article/modify?id=${param.id}'">수정
		</button>
		<button type="button" class="delete-button" value="delete"
			onclick="location.href='../article/delete?id=${param.id}'">삭제
		</button>
	</div>
</div>

<%@ include file="/jsp/part/foot.jspf"%>