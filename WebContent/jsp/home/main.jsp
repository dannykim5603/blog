<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/jsp/part/head.jspf"%>

<!대문>
<div class="index visible-on-md-up con">
	<p class="text-align-center">
		welcome to <span> UNKNOWN </span> &mdash; enjoy your time &mdash;
	</p>
</div>

<! 모바일 대문>
<div class="mobile-index visible-on-sm-down con flex">
	<p class="text-align-center">
		welcome to <span> UNKNOWN </span> &mdash; enjoy your time &mdash;
	</p>
</div>

<! 프로필>
<div class="profile-box con flex visible-on-md-up">
	<div class="profile-img flex flex-ai-c">
		<img src="${pageContext.request.contextPath}/resource/img/profile.png" alt="" width="120">
	</div>
	<div class="profile-txt">
		<h1>DANNY KIM</h1>
		<p>
			이름 : 김동연<br> 나이 : 29(27)<br> E-mail :
			dannykim5603@gmail.com<br> Phone : 010-2209-5603
		</p>
	</div>
</div>

<div class="con">
	<h1>메인</h1>
</div>
<%@ include file="/jsp/part/foot.jspf"%>

