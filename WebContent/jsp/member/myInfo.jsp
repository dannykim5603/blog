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
	display: flex;
	justify-content: center;
}

.form1 {
	width: 40%;
	margin-top: 100px;
}

.form1>.form-row {
	width: 100%;
}

.form1>.form-row>.label {
	padding-top: 20px;
	padding-bottom: 20px;
}

.form1>.form-row>.input>input {
	width: 100%;
	margin-left: 20px;
	height: 25px;
}

.last-box>a {
	background-color: white;
	color: black;
	font-weight: thin;
	border-radius: 15px;
	padding: 0px 40px;
}
</style>

<div class="join-form-box con">

	<div class="form-row">
		<div class="label">ID</div>
		<div class="input">out.println(member.getId());</div>
	</div>

	<div class="form-row">
		<div class="label">이름</div>
		<div class="input">out.println(member.getName());</div>
	</div>

	<div class="form-row">
		<div class="label">NICKNAME</div>
		<div class="input">out.println(member.getNickname());</div>
	</div>

	<div class="form-row">
		<div class="label">E-MAIL</div>
		<div class="input">out.println(member.getEmail());</div>
	</div>

	<div class="form-row">
		<div class="label"></div>
		<div class="input last-box">
			<button type="button" class="modifyMyInfo-button"
				value="modifyMyInfo" onclick="location.href'../member/modifyMyInfo'">개인정보
				변경</button>
			<button type="button" class="cancel" value="cancel"
				onclick="goBack();">취소</button>
		</div>
	</div>
	
</div>

<%@ include file="/jsp/part/foot.jspf"%>