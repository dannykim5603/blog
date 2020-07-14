<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/jsp/part/head.jspf"%>

<style>
.login-form-box {
	color : white;
	display : flex;
	justify-content : center;
	margin-top:15%;
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

<div class="login-form-box con">
	<form action="doLogin" method="POST" class="join-form form1" onsubmit="submitJoinForm(this); return false;">
		<div class="form-row">
			<div class="label">loginId</div>
			<div class="input">
				<input name="loginId" type="text" placeholder=" login ID " />
			</div>
		</div>
		<div class="form-row">
			<div class="label">loginPw</div>
			<div class="input">
				<input name="loginPw" type="password" placeholder=" login PW "></input>
			</div>
		</div>
		<div class="form-row">
			<div class="label"></div>
			<div class="input last-box">
				<input class="submit-box" style="width: 50%; border-radius: 15px;"type="submit" value="로그인" /> 
					<a style="border: 3px solid #444958;" class="cancel" href="home">취소</a>
			</div>
		</div>
	</form>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/member/join.js "></script>




<%@ include file="/jsp/part/foot.jspf"%>