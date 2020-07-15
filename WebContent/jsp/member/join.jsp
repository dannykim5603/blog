<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/jsp/part/head.jspf"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>
<style>
.join-form-box {
	color : white;
	display : flex;
	justify-content : center;
}
.form1 {
	width:40%;
	margin-top : 100px;
}
.form1 > .form-row {
	width : 100%;
}
.form1 > .form-row > .label{
	padding-top : 20px;
	padding-bottom : 20px;
}
.form1 > .form-row > .input > input {
	width : 100%;
	margin-left : 20px;
	height : 25px;
}
	
.last-box > a {
	background-color : white;
	color:black;
	font-weight : thin;
	border-radius : 15px;
	padding : 0px 40px;
	
}

</style>

<div class="join-form-box con">
	<form action="doJoin" method="POST" class="join-form form1" onsubmit="submitJoinForm(this); return false;">
	<input type="hidden" name="loginPwReal">
		<div class="form-row">
			<div class="label">loginId</div>
			<div class="input">
				<input name="loginId" type="text" placeholder=" login ID " />
			</div>
		</div>
		<div class="form-row">
			<div class="label">e-mail</div>
			<div class="input">
				<input name="email" type="email" placeholder="E-mail " />
			</div>
		</div>
		<div class="form-row">
			<div class="label">name</div>
			<div class="input">
				<input name="name" type="text" placeholder=" NAME "></input>
			</div>
		</div>
		<div class="form-row">
			<div class="label">nickname</div>
			<div class="input">
				<input name="nickname" type="text" placeholder=" NICKNAME " />
			</div>
		</div>
		<div class="form-row">
			<div class="label">loginPw</div>
			<div class="input">
				<input name="loginPw" type="password" placeholder=" login PW "></input>
			</div>
		</div>
		<div class="form-row">
			<div class="label">loginPwConfirm</div>
			<div class="input">
				<input name="loginPwC" type="<passwo></passwo>rd" placeholder=" PW Confirm "></input>
			</div>
		</div>
		<div class="form-row">
			<div class="label"></div>
			<div class="input last-box">
				<input class="submit-box" style="width: 50%; border-radius: 15px;"type="submit" value="회원가입" /> 
					<a style="border: 3px solid #444958;" class="cancel" href="home">취소</a>
			</div>
		</div>
	</form>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/member/join.js "></script>

<%@ include file="/jsp/part/foot.jspf"%>