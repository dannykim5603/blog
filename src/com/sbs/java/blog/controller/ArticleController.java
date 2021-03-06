package com.sbs.java.blog.controller;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.dto.ArticleReply;
import com.sbs.java.blog.dto.CateItem;
import com.sbs.java.blog.dto.Member;
import com.sbs.java.blog.util.Util;

public class ArticleController extends Controller {
	public ArticleController(Connection dbConn, String actionMethodName, HttpServletRequest req,
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
		case "list":
			return actionList();

		case "detail":
			return actionDetail();

		case "write":
			return actionWrtie();

		case "doWrite":
			return actionDoWrite();

		case "modify":
			return actionModify();

		case "doModify":
			return actionDoModify();

		case "delete":
			return actionDelete();

		case "writeArticleReply":
			return actionWriteArticleReply();

		case "replyDelete":
			return actionReplyDelete();

		case "replyModify":
			return actionReplyModify();

		case "doReplyModify":
			return actionDoReplyModify();
		}
		return "";
	}

	private String actionDoReplyModify() {
		int replyId = Util.getInt(req, "id");
		String body = Util.getString(req, "body");
		articleService.modifyReply(replyId, body);
		return "html:<script> alert('댓글이 수정되었습니다.'); window.close();) </script>";
	}

	private String actionReplyModify() {
		int loginedMemberId = (int) req.getAttribute("loginedMemberId");
		int replyId = Util.getInt(req, "id");
		ArticleReply reply = articleService.getArticleReplyByReplyId(replyId);
		int replyMemeberId = Integer.parseInt(reply.getMemberId());
		if (loginedMemberId == replyMemeberId) {
			req.setAttribute("articleReply", reply);
			return "articleReply/replyModify.jsp";
		}
		return "html:<script> alert('권한이 없습니다.'); location.replace(history.back());</script>";
	}

	private String actionReplyDelete() {
		int loginedMemberId = (int) req.getAttribute("loginedMemberId");
		int replyId = Util.getInt(req, "id");
		ArticleReply reply = articleService.getArticleReplyByReplyId(replyId);
		int replyMemeberId = Integer.parseInt(reply.getMemberId());

		if (loginedMemberId == replyMemeberId) {
			articleService.deleteReply(replyId);
			return "html:<script> alert('댓글이 삭제되었습니다.'); location.replace(history.back())</script>";
		}
		return "html:<script> alert('권한이 없습니다.'); location.replace('history.back()')</script>";
	}

	private String actionWriteArticleReply() {
		int id = Util.getInt(req, "id");

		Article article = articleService.getArticleById(id);

		int articleId = article.getId();

		String articleReply = req.getParameter("body");

		int memberId = (int) session.getAttribute("loginedMemberId");

		Member member = memberService.getMemberById(memberId);

		articleService.doWriteArticleReply(articleReply, member, articleId);

		return "html:<script> alert('댓글이 등록되었습니다.'); location.replace('detail?id=" + article.getId() + "')</script>";

	}

	private String actionDelete() {
		int id = Util.getInt(req, "id");
		articleService.delete(id);
		return "html:<script> alert('" + id + "번 게시물이 삭제되었습니다.'); location.replace('list')</script>";
	}

	private String actionDoModify() {
		String title = req.getParameter("title");
		String body = req.getParameter("body");
		int displayStatus = Integer.parseInt(req.getParameter("displayStatus"));
		int cateItemId = Util.getInt(req, "cateItemId");
		int id = Util.getInt(req, "id");

		articleService.doModify(title, body, displayStatus, cateItemId, id);
		return "html:<script> alert('" + id + "번 게시물이 수정되었습니다.'); location.replace('list')</script>";
	}

	private String actionModify() {
		if (Util.empty(req, "id")) {
			return "html:id를 입력해주세요.";
		}
		if (Util.isNum(req, "id") == false) {
			return "html:id를 숫자로 입력해 주세요.";
		}
		int loginedMemberId = (int) req.getAttribute("loginedMemberId");
		int id = Util.getInt(req, "id");
		Article article = articleService.detail(id, loginedMemberId);
		System.out.println(article);
		req.setAttribute("article", article);
		return "article/modify.jsp";
	}

	private String actionDoWrite() {
		String title = req.getParameter("title");
		String body = req.getParameter("body");
		int displayStatus = Integer.parseInt(req.getParameter("displayStatus"));
		int cateItemId = Util.getInt(req, "cateItemId");

		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

		int id = articleService.doWrite(displayStatus, cateItemId, title, body, loginedMemberId);

		return "html:<script> alert('" + id + "번 게시물이 생성되었습니다.'); location.replace('list')</script>";
	}

	private String actionWrtie() {
		return "article/write.jsp";
	}

	private String actionDetail() {
		if (Util.empty(req, "id")) {
			return "html:id를 입력해주세요.";
		}
		if (Util.isNum(req, "id") == false) {
			return "html:id를 숫자로 입력해 주세요.";
		}

		int id = Util.getInt(req, "id");
		articleService.increaseHit(id);

		int loginedMemberId = (int) req.getAttribute("loginedMemberId");
		Article article = articleService.detail(id, loginedMemberId);
		req.setAttribute("article", article);

		int memberId = article.getMemberId();
		String memberNickname = memberService.getMemberById(memberId).getNickname();
		req.setAttribute("memberNickname", memberNickname);

		List<ArticleReply> articleReplies = articleService.getArticleReplyByArticleId(id);
		req.setAttribute("articleReplies", articleReplies);

		return "article/detail.jsp";
	}

	private String actionList() {

//		long startTime = System.nanoTime();

		int page = 1;

		if (!Util.empty(req, "page") && Util.isNum(req, "page")) {
			page = Util.getInt(req, "page");
		}

		int cateItemId = 0;

		if (!Util.empty(req, "cateItemId") && Util.isNum(req, "cateItemId")) {
			cateItemId = Util.getInt(req, "cateItemId");
		}

		String cateItemName = "전체";

		if (cateItemId != 0) {
			CateItem cateItem = articleService.getCateItem(cateItemId);
			cateItemName = cateItem.getName();
		}
		req.setAttribute("cateItemName", cateItemName);

		String searchKeywordType = "";

		if (!Util.empty(req, "searchKeywordType")) {
			searchKeywordType = Util.getString(req, "searchKeywordType");
		}

		String searchKeyword = "";

		if (!Util.empty(req, "searchKeyword")) {
			searchKeyword = Util.getString(req, "searchKeyword");
		}

		int itemsInAPage = 10;
		int totalCount = articleService.getArticlesCount(cateItemId, searchKeywordType, searchKeyword);
		int totalPage = (int) Math.ceil(totalCount / (double) itemsInAPage);

		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("cPage", page);
		req.setAttribute("memberService", memberService);
		List<Article> articles = articleService.getArticles(page, itemsInAPage, cateItemId, searchKeywordType,
				searchKeyword);

		req.setAttribute("articles", articles);

//		long endTime= System.nanoTime();
//		long estimatedTime = endTime - startTime;
//		//nano seconds to seconds
//		double seconds = estimatedTime / 1000000000.0;
//		System.out.println("seconds : " + seconds);
//		
		return "article/list.jsp";
	}

	@Override
	public String getControllerName() {
		return "article";

	}
}