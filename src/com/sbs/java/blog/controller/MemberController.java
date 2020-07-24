package com.sbs.java.blog.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dto.Member;

public class MemberController extends Controller {
	public MemberController(Connection dbConn, String actionMethodName, HttpServletRequest req,
			HttpServletResponse resp) {
		super(dbConn, actionMethodName, req, resp);
	}

	public void beforeAction() {
		super.beforeAction();
		// 이 메서드는 아티클 게시물 컨트롤러의 모든 액션이 실행되기 전에 실행된다.
		// 필요 없다면 지워도 된다.
	}

	public String doAction() {
		switch (actionMethodName) {
		case "join":
			return actionJoin();

		case "doJoin":
			return actionDoJoin();

		case "login":
			return actionLogin();
		
		case "doLogin":
			return actionDoLogin();
			
		case "doLogout":
			return actionLogout();
		
		case "findId":
			return actionFindId();
			
		case "doFindId":
			return actionDoFindId();
		
		case "findPw":
			return actionFidnPw();
			
		case "doFindPw":
			return actionDoFindPw();
			
		case "myInfo":
			return actionMyInfo();
			
		case "modifyMyInfo":
			return actionModifyMyInfo();
			
		case "doModifyMyInfo":
			return actionDoModifyMyInfo();
		}
		return "";
	}

	private String actionDoModifyMyInfo() {
		String email = req.getParameter("email");
		String nickname = req.getParameter("nickname");
		String loginPw = req.getParameter("loginPwReal");
		int id = (int)session.getAttribute("loginedMemberId");
		Member member = memberService.getMemberById(id);
		if ( email == null ) {
			email = member.getEmail(); 
		}
		
		if (nickname == null) {
			nickname = member.getNickname();
		}
		
		memberService.modifyMemberInfo(email,nickname,loginPw,id);
		
		String title = "BLOG DANNYS UNKNOWN ";
		String body = " 회원님의 정보가 수정되었습니다. ";
		
		mailService.send(email, title, body);
		return "html:<script> alert('정보가 수정되었습니다.'); location.replace('../home/main') </script>";
	}

	private String actionModifyMyInfo() {
		return "member/modifyMyInfo.jsp";
	}

	private String actionMyInfo() {
		Member member = memberService.getMemberById((int)session.getAttribute("loginedMemberId"));
		req.setAttribute("member", member);
		return "member/myInfo.jsp";
	}

	private String actionDoFindPw() {
		String email = req.getParameter("email");
		String name = req.getParameter("name");
		String loginId = req.getParameter("loginId");
		
		Member member = memberService.getMemberByEmailANDNameANDId(email, name,loginId);
		if (member.getEmail().equals(email)) {
		String tempPw = getTempPw(10);
		req.setAttribute("tempPw", tempPw);
		
		String title = "BLOG DANNYS UNKNOWN";
		String body = "요청에 의해 임시 비밀번호를 발송했습니다.\n\n \t임시 비밀번호는 "+tempPw+"입니다.\n\n 본인의 요청이 아니었다면 비밀번호를 바꿔주세요.";
		mailService.send(email, title, body);
		
		tempPw = sha256(tempPw);
		
		memberService.setTemporaryPw(email, name, loginId,tempPw);
		return "html:<script> alert('임시 비밀번호가 이메일로 발송 되었습니다. 임시 비밀번호로 접속 후 비밀번호를 수정해 주세요.'); location.replace('../member/login') </script>";
		}
		return "html:<script> alert('입력하신 정보가 일치하는 회원이 존재하지 않습니다. 다시 시도해 주세요.'); location.replace('../member/login') </script>";
	}

	private String actionFidnPw() {
		return "member/findPw.jsp";
	}

	private String actionDoFindId() {
		String email = req.getParameter("email");
		String name = req.getParameter("name");
		
		Member member = memberService.getMemberByEmailANDName(email,name);
		
		return "html:<script> alert('찾으신 아이디는  "+member.getLoginId()+"  입니다.'); location.replace('../member/login') </script>";
	}

	private String actionFindId() {
		return "member/findId.jsp";
	}

	private String actionLogout() {
		session.removeAttribute("loginedMemberId");
		
		return "html:<script> alert('로그아웃 되었습니다.'); location.replace('../home/main') </script>";
	}

	private String actionDoLogin() {
		String loginId = req.getParameter("loginId");
		String loginPw = req.getParameter("loginPwReal");
		
		int loginedMemberId = memberService.getMemberByIdNPw(loginId, loginPw).getId();
		
//		System.out.println(member); // 로그인 멤버 정보
		if (loginedMemberId == -1 ) {
			return "html:<script> alert('아이디 혹은 비밀번호를 확인해 주세요'); location.replace('../home/main') </script>";
		}
		
		session.setAttribute("loginedMemberId", loginedMemberId);
		return  "html:<script> alert('로그인 되었습니다.'); location.replace('../home/main')</script>";
	}

	private String actionLogin() {
		return "member/login.jsp";
	}

	private String actionDoJoin() {
		String loginId = req.getParameter("loginId");
		String loginPw = req.getParameter("loginPwReal");
		String email = req.getParameter("email");
		String name = req.getParameter("name");
		String nickname = req.getParameter("nickname");

		boolean idcheck = memberService.CheckId(loginId);
		// 계정이 있으면 true 계정이 없으면 false
		if (idcheck == true) {
			return "html:<script> alert('이미 존재하는 아이디 입니다.'); location.replace('join')</script>";
		}
		
		boolean emailCheck = memberService.checkEmail(email);
		//email이 있으면 true 없으면 false
		if ( emailCheck == true ) {
			return String.format("html:<script> alert('%s(은)는 이미 사용중인 이메일 입니다.'); history.back(); </script>", email);
		}
		
		boolean nicknameCheck = memberService.nicknameCheck(nickname);
		//nickname이 있으면 true 없으면 false
		if (nicknameCheck == true) {
			return String.format("html:<script> alert('%s(은)는 이미 사용중인 닉네임 입니다.'); history.back(); </script>", nickname);
		}
		
		int memberId = memberService.join(loginId, loginPw, email, name, nickname);
		String title = "BLOG DANNYS UNKOWN";
		String body = name+"님의 회원가입을 환영합니다.\n 아이디 : "+ loginId +"\n Email :" +email +" \n 의 정보로 가입되었습니다. \n 다시한번 회원님의 가입을 환영합니다. ";
		
		mailService.send(email, title, body);
		
		return "html:<script> alert('" + nickname + " 회원님 " + memberId
				+ "번째 회원이 되신 것을 환영합니다.'); location.replace('../home/main')</script>";
	}

	private String actionJoin() {
		return "member/join.jsp";
	}
	private String getTempPw(int length) {
		int index = 0;
		char[] charArr = new char[] {
				 '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 
				 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 
				 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 
				 'w', 'x', 'y', 'z' 
		};
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			index = (int) (charArr.length*Math.random());
			sb.append(charArr[index]);
		}
		
		return sb.toString();
	}
	private String sha256(String str) {
		String SHA = "";
		try {
			MessageDigest sh = MessageDigest.getInstance("SHA-256");
			sh.update(str.getBytes());
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length;i++) {
				sb.append(Integer.toString((byteData[i]&0xff)+0x100,16).substring(1));
			}
			SHA = sb.toString();
		}
		catch(NoSuchAlgorithmException e){
			e.printStackTrace();
			SHA = null;
		}
		return SHA;
	}

	@Override
	public String getControllerName() {
		return "member";
	}
}