package com.tj.stl.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.stl.service.EmailChkService;
import com.tj.stl.service.IdChkService;
import com.tj.stl.service.LogoutService;
import com.tj.stl.service.MemberModifyService;
import com.tj.stl.service.PwChkService;
import com.tj.stl.service.Service;
import com.tj.stl.service.SigninService;
import com.tj.stl.service.SignupService;

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
			//service = new MemberModifyViewService();
			viewPage = "member/modify_view.jsp";
		}else if (com.equals("/memberModify.do")) {
			service = new MemberModifyService();
			service.execute(request, response);
			viewPage = "member/modify_view.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
	

}
