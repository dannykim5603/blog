<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/jsp/part/head.jspf"%>
<! 프로필>
<div class="profile-box visible-on-md-up con flex" style ="background-color:white">
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

<! 모바일 프로필>
<div class="mobile-profile-box visible-on-sm-down con flex" style ="background-color:white">
	<div class="profile-img flex flex-ai-c">
		<img src="${pageContext.request.contextPath}/resource/img/profile.png" alt="" width="120" height="120">
	</div>
	<div class="profile-txt">
		<h1>DANNY KIM</h1>
		<p>
			이름 : 김동연<br> 나이 : 29(27)<br> E-mail :<br>
			dannykim5603@gmail.com<br> Phone : 010-2209-5603
		</p>
	</div>
</div>

<!  >
<div>
	


</div>

<%@ include file="/jsp/part/foot.jspf"%>

