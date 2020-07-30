<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/jsp/part/head.jspf"%>
<%
	Member member = (Member) request.getAttribute("member");
%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>
<style>
.join-form-box {
	color: white;
	display: block;
	justify-content: center;
	border: outset #444958 3px;
	width : 40%;
	height: 80%;
}

.form1 {
	width: 500px;
	margin-top: 100px;
}

.form1>.form-row {
	width: 100%;
	display:block;
}

.form1>.form-row>.label {
	padding-top: 20px;
	padding-bottom: 20px;
	margin-left:10px;
}

.form1>.form-row>.input {
	width: 100%;
	height: 25px;
	text-align:center;
	margin-top: 20px;
	font-weight:bold;
}
.last-box {
	margin-bottom:20px;
}

.last-box> button {
	background-color: white;
	color: black;
	font-weight: thin;
	border-radius: 2px;
	padding: 0px 40px;
	
}
</style>

<div class="join-form-box form1 con">

	<div class="form-row">
		<div class="label">ID</div>
		<div class="input">${member.loginId}</div>
	</div>

	<div class="form-row">
		<div class="label">이름</div>
		<div class="input">${member.name}</div>
	</div>

	<div class="form-row">
		<div class="label">NICKNAME</div>
		<div class="input">${member.nickname}</div>
	</div>

	<div class="form-row">
		<div class="label">E-MAIL</div>
		<div class="input">${member.email}</div>
	</div>

	<div class="form-row">
		<div class="label"></div>
		<div class="input last-box">
			<button type="button" class="modifyMyInfo-button" value="modifyMyInfo" onclick="location.href='../member/modifyMyInfo'">개인정보 변경</button>
			<button type="button" class="cancel" value="cancel" onclick="goBack();">취소</button>
		</div>
	</div>
	
</div>

<%@ include file="/jsp/part/foot.jspf"%>