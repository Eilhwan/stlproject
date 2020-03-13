package com.tj.stl.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.stl.service.AdminListViewService;
import com.tj.stl.service.AdminSignUpService;
import com.tj.stl.service.AdminSigninService;
import com.tj.stl.service.AdminViewService;
import com.tj.stl.service.EmailChkService;
import com.tj.stl.service.IdChkService;
import com.tj.stl.service.InsertBrandService;
import com.tj.stl.service.InsertProductService;
import com.tj.stl.service.InsertProductViewService;
import com.tj.stl.service.LogoutService;
import com.tj.stl.service.MainService;
import com.tj.stl.service.MemberModifyService;
import com.tj.stl.service.NoticeContentViewService;
import com.tj.stl.service.NoticeViewService;
import com.tj.stl.service.ProductContentViewService;
import com.tj.stl.service.ProductListViewService;
import com.tj.stl.service.PwChkService;
import com.tj.stl.service.Service;
import com.tj.stl.service.SigninService;
import com.tj.stl.service.SignupService;
import com.tj.stl.service.WriteNoticeService;

/**
 * Servlet implementation class Controller
 */
@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		actionDo(request, response);
	}
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		String viewPage = null;
		Service service = null;
		if (com.equals("/main.do")) {
			service = new MainService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		}else if (com.equals("/signinView.do")) {
			viewPage = "member/signIn_view.jsp";
		}else if (com.equals("/signIn.do")) { //로그인
			service = new SigninService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		}else if (com.equals("/signupView.do")) {
			viewPage = "member/signUp_view.jsp";
		}else if (com.equals("/signup.do")) { //회원가입
			service = new SignupService();
			service.execute(request, response);
			viewPage = "member/signIn_view.jsp";
		}else if (com.equals("/idChk.do")) {
			service = new IdChkService();
			service.execute(request, response);
			viewPage = "member/idChk.jsp";
		}else if (com.equals("/pwChk.do")) {
			service = new PwChkService();
			service.execute(request, response);
			viewPage = "member/pwChk.jsp";
		}else if (com.equals("/emailChk.do")) {
			service = new EmailChkService();
			service.execute(request, response);
			viewPage = "member/emailChk.jsp";
		}else if (com.equals("/logout.do")) {
			service = new LogoutService();
			service.execute(request, response);
			viewPage = "member/signIn_view.jsp";
		}else if (com.equals("/memberView.do")) {
			viewPage = "member/member_view.jsp";
		}else if (com.equals("/modify_view.do")) {
			viewPage = "member/modify_view.jsp";
		}else if (com.equals("/memberModify.do")) {
			service = new MemberModifyService();
			service.execute(request, response);
			viewPage = "member/modify_view.jsp";
		}else if (com.equals("/adminSignInView.do")) {
			viewPage = "admin/adminSginIn_view.jsp";
		}else if (com.equals("/adminSignIn.do")) { //관리자 로그인
			service = new AdminSigninService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		}else if (com.equals("/adminView.do")) {
			service = new AdminViewService();
			service.execute(request, response);
			viewPage = "admin/admin_view.jsp";
		}else if (com.equals("/adminListView.do")) {
			service = new AdminListViewService();
			service.execute(request, response);
			viewPage = "admin/adminList_view.jsp";
		}else if (com.equals("/noticeBoardView.do")) {
			service = new NoticeViewService();
			service.execute(request, response);
			viewPage = "board/noticeBoard.jsp";
		}else if (com.equals("/writeNoticeView.do")) {
			viewPage = "board/noticeWrite.jsp";
		}else if (com.equals("/writeNotice.do")) {
			service = new WriteNoticeService();
			service.execute(request, response);
			viewPage = "/noticeBoardView.do";
		}else if (com.equals("/adminSignUpView.do")) {
			viewPage = "admin/adminSignUp.jsp";
		}else if (com.equals("/adminSignUp.do")) {
			service = new AdminSignUpService();
			service.execute(request, response);
			viewPage = "admin/adminSignUp.jsp";
		}else if (com.equals("/noticeContentView.do")) {
			service = new NoticeContentViewService();
			service.execute(request, response);
			viewPage = "board/noticeContent_view.jsp";
		}else if (com.equals("/productListView.do")) { //상품관리페이지
			service = new ProductListViewService();
			service.execute(request, response);
			viewPage = "admin/productList_view.jsp";
		}else if (com.equals("/insertProductView.do")) { //상품등록페이지
			service = new InsertProductViewService();
			service.execute(request, response);
			viewPage = "admin/insertProduct_view.jsp";
		}else if (com.equals("/insertProduct.do")) { //상품등록페이지
			service = new InsertProductService();
			service.execute(request, response);
			viewPage = "/productListView.do";
		}else if (com.equals("/productContentView.do")) {
			service = new ProductContentViewService();
			service.execute(request, response);
			viewPage = "admin/productContent_view.jsp";
		}else if (com.equals("/insertBrandView.do")) {
			viewPage = "admin/insertBrand_view.jsp";
		}else if (com.equals("/insertBrand.do")) {
			service = new InsertBrandService();
			service.execute(request, response);
			viewPage = "admin/insertBrand_view.jsp";
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
