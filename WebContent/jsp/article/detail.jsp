<%@page import="com.sbs.java.blog.dto.Article"%>
<%@page import="com.sbs.java.blog.dto.ArticleReply"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/jsp/part/head.jspf"%>
<%@ include file="/jsp/part/toastUiEditor.jspf"%>

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

.article-box>.util-butt-box {
	display: flex;
	justify-content: flex-end;
}

.article-box>.util-butt-box>button {
	margin: 10px;
}
</style>

<!-- detail -->
<div class="con article-box" style="background-color: white">

	<h1>${article.title}</h1>
	<h2>작성자 : ${memberNickname}</h2>
	<h3>조회수 : ${article.hit}</h3>
	<h3>작성일 : ${article.regDate}</h3>
	<br>
	<script type="text/x-template">	${article.bodyForXTemplate} </script>
	<div class="toast-editor toast-editor-viewer"></div>

	<!-- articleReply -->
	<!--  teacher's  v -->
<script>
function WriteReplyList__showTop() {
	var top = $('.article-replies-list-box').offset().top;
	$(window).scrollTop(top);
}

function WriteReplyList__showDetail() {
	WriteReplyList__showTop();
	var $tr = $('.article-replies-list-box > table > tbody > tr[data-id="'
			+ param.generatedArticleReplyId + '"]');
	$tr.addClass('high');
	setTimeout(function() {
		$tr.removeClass('high');
	}, 1000);
}
</script>
<br>
<style>
	.article-replies-list-box>table>tbody>tr.high {
		background-color: #dfdfdf;
	}

	.article-replies-list-box>table>tbody>tr {
		transition: background-color 1s;
	}
</style>
<div>
	<div class="con article-replies-list-box table-box">
		<h3>댓글</h3>
			<table>
				<colgroup>
					<col width="100">
					<col width="200">
					<col width="800">
					<col width="120">
				</colgroup>
				<thead>
					<tr>
						<th>번호</th>
						<th>날짜</th>
						<th>내용</th>
						<th>비고</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${articleReplies}" var="articleReply">
						<tr>
							<td class="text-align-center">${articleReply.id}</td>
							<td class="text-align-center">${articleReply.regDate}</td>
							<td class="padding-left-10 padding-right-10 text-align-center"><script
									type="text/x-template">${articleReply.bodyForXTemplate}</script>
								<div class="toast-editor toast-editor-viewer"></div></td>
							<td class="text-align-center">
							<div class="util-butt-box">
							<input type="hidden" name="replyId" value="${articleReply.id}">
								<button class="reply-modify" type="button" onclick="location.href='../article/replyModify?id=${articleReply.id}'">수정</button>
								<button class="reply-delete" type="button" onclick="location.href='../article/replyDelete?id=${articleReply.id}'">삭제</button>
							</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<!-- My version -->
		<!-- 
	<div class="articleReply-box" style="margin-top: 20px">
		<div class="reply-box">
			<c:choose>
				<c:when test="${not empty articleReplies}">
					<c:forEach items="${articleRelpies}" var="articleReply">
						<c:out value="${articleReply.id}" />.  <c:out
							value="${articleReply.nickname}" />
						<c:out value="${articleReply.body}" />
						<button class="reply-modify" type="button"
							onclick="cmUpdateOpen(${comment})">수정</button>
						<button class="reply-delete" type="button" onclick="">삭제</button>
						<br>
					</c:forEach>
				</c:when>
				<c:when test="${empty articleReplies}">
					<c:out value="댓글이 없습니다." />
				</c:when>
			</c:choose>
		</div>
		-->
		<form action="writeArticleReply" method="POST"
			class="articleReplyForm" style="margin-top: 20px;">
			<input type="hidden" name="id" value="${article.id}" />
			<div class="form-row">
				<div class="label" style="margin-right: 30px;">댓글</div>
				<div class="input">
					<textarea name="body" rows="3px" cols="100%"></textarea>
				</div>
			</div>
			<div class="form-row">
				<div class="label"></div>
				<div class="input submit-box">
					<input class="reply-submit" style="margin-left: 10px" type="submit" value="작성" />
					<button  type="button" class="cancel" value="cancel" onclick="location.href='detail?id=${param.id}'">취소</button>
				</div>
			</div>
		</form>
	</div>

	<!-- article Util area -->
	<div class="util-butt-box">
		<input type="hidden" name="id" value="${article.id}" />
		<button type="button" class="modify-button" value="modify"
			onclick="location.href='../article/modify?id=${param.id}'">수정
		</button>
		<button type="button" class="delete-button" value="delete"
			onclick="location.href='../article/delete?id=${param.id}'">삭제
		</button>
	</div>
</div>

<%@ include file="/jsp/part/foot.jspf"%>