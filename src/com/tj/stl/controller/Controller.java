package com.tj.stl.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.stl.service.*;

/**
 * Servlet implementation class Controller
 */
@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private int writable = 0;
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
		
		//메인
		if (com.equals("/main.do")) {
			service = new MainService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		}
		//유저관련
		if (com.equals("/signinView.do")) {
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
		}else if (com.equals("/logout.do")) { //로그아웃
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
		}else if (com.equals("/addressView.do")) { //배송지 수정
			viewPage = "member/memberAddress.jsp";
		}else if (com.equals("/levelpointView.do")) { //포인트 등급확인
			viewPage = "member/levelpoint_view.jsp";
		}else if (com.equals("/addressModify.do")) {
			service = new MemberAddressModify();
			service.execute(request, response);
			viewPage = "/addressView.do";
		}else if (com.equals("/deleteMemberView.do")) { //회원탈퇴 페이지
			viewPage = "member/deleteMember_view.jsp";
		}else if (com.equals("/delete_member.do")) { //회원탈퇴
			service = new DeleteMemberService();
			service.execute(request, response);
			viewPage = "main.do";
		}
		
		//관리자 관련 커맨드
		if (com.equals("/adminSignIn.do")) { //관리자 로그인
			service = new AdminSigninService();
			service.execute(request, response);
			viewPage = "main.do";
		}else if (com.equals("/adminSignInView.do")) {
			viewPage = "admin/adminSginIn_view.jsp";
		}else if (com.equals("/adminView.do")) {
			service = new AdminViewService();
			service.execute(request, response);
			viewPage = "admin/admin_view.jsp";
		}else if (com.equals("/adminListView.do")) {
			service = new AdminListViewService();
			service.execute(request, response);
			viewPage = "admin/adminList_view.jsp";
		}else if (com.equals("/adminSignUpView.do")) {
			viewPage = "admin/adminSignUp.jsp";
		}else if (com.equals("/adminSignUp.do")) {
			service = new AdminSignUpService();
			service.execute(request, response);
			viewPage = "admin/adminSignUp.jsp";
		}else if (com.equals("/deleteadmin.do")) {
			service = new DeleteAdminService();
			service.execute(request, response);
			viewPage = "admin/adminListView.do";
		}
		
		//게시판 관련 커맨드
		if (com.equals("/noticeBoardView.do")) { //공지사항게시판
			service = new NoticeViewService();
			service.execute(request, response);
			viewPage = "board/noticeBoard.jsp";
		}else if (com.equals("/eventBoardView.do")) {
			service = new EventViewService();
			service.execute(request, response);
			viewPage = "board/noticeBoard.jsp";
		}else if (com.equals("/writeNoticeView.do")) {
			writable = 1;
			viewPage = "board/noticeWrite.jsp";
		}else if (com.equals("/writeNotice.do")) {
			if(writable==1) {
				service = new WriteNoticeService();
				service.execute(request, response);
				writable =0;
			}
			viewPage = "/noticeBoardView.do";
		}else if (com.equals("/noticeContentView.do")) {
			service = new NoticeContentViewService();
			service.execute(request, response);
			viewPage = "board/noticeContent_view.jsp";
		}else if (com.equals("/deleteNotice.do")) {
			service = new DeleteNoticeService();
			service.execute(request, response);
			viewPage = "/noticeBoardView.do";
		}else if (com.equals("/qnaListView.do")) {  //QNA게시판
			service = new QnaListViewService();
			service.execute(request, response);
			viewPage = "QnA/qnaList_view.jsp";
		}else if (com.equals("/writeqnaView.do")) {
			writable = 1;
			viewPage = "QnA/qnaWrite_view.jsp";
		}else if (com.equals("/qnaWirte.do")) {
			if (writable == 1) {
				service = new QnAWriteService();
				service.execute(request, response);
				writable = 0;
			}
			viewPage = "qnaListView.do";
		}else if (com.equals("/qnaContentView.do")) {
			service = new QnAContentViewService();
			service.execute(request, response);
			viewPage = "QnA/qnaContent_view.jsp";
		}else if (com.equals("/replyqnaView.do")) {
			viewPage = "QnA/qnaReply_view.jsp";
		}else if (com.equals("/qnaReply.do")) {
			service = new QnAReplyService();
			service.execute(request, response);
			viewPage = "qnaListView.do";
		}else if (com.equals("/reviewBoardView.do")) {//review게시판
			service = new ReViewListService();
			service.execute(request, response);
			viewPage = "board/reviewList.jsp";
		}else if (com.equals("/writereviewView.do")) {//review게시판
			writable = 1;
			viewPage = "board/reviewWrite.jsp";
		}else if (com.equals("/reviewContentView.do")) {//review게시판
			service = new ReviewContentViewService();
			service.execute(request, response);
			viewPage = "board/reviewContent_view.jsp";
		}else if (com.equals("/writeReview.do")) {
			if (writable == 1) {
				service = new WriteReviewService();
				service.execute(request, response);
				writable = 0;
			}
			viewPage = "qnaListView.do";
		}
		
		//상품관련커맨드
		if (com.equals("/productListView.do")) { //상품관리페이지
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
		}else if (com.equals("/userProductListView.do")) {
			service = new UserProductListViewService();
			service.execute(request, response);
			viewPage = "product/product_list_view.jsp";
		}else if (com.equals("/productEnrollView.do")) {
			service = new selectedService();
			service.execute(request, response);
			viewPage = "product/product_enroll_view.jsp";
		}else if (com.equals("/productSelect.do")) {
			service = new SelectProductService();
			service.execute(request, response);
			viewPage = "product/productselect.jsp";
		}else if (com.equals("/productenroll.do")) {
			service = new ProductEnrollService();
			service.execute(request, response);
			viewPage = "userProductListView.do";
		}else if (com.equals("/userProductContentView.do")) { // 등록된 상품 상세보기
			service = new UserProductContentViewService();
			service.execute(request, response);
			viewPage = "product/userProductContent_view.jsp";
		}
		//검색관련 커맨드
		if (com.equals("/mainSearch.do")) { // 등록된 상품 상세보기
			service = new SearchProductService();
			service.execute(request, response);
			viewPage = "product/searchResult.jsp";
		}
		
		
		//주문 관련 커맨드
		if (com.equals("/cartView.do")) {
			service = new CartViewService();
			service.execute(request, response);
			viewPage = "cart/cart_view.jsp";
		}else if (com.equals("/insertCart.do")) {
			service = new InsertCartService();
			service.execute(request, response);
			viewPage = "userProductContentView.do";
		}else if (com.equals("/deleteCart.do")) {
			service = new DeleteCartService();
			service.execute(request, response);
			viewPage = "cartView.do";
		}else if (com.equals("/deleteCartAll.do")) {
			service = new DeleteCartAllService();
			service.execute(request, response);
			viewPage = "cartView.do";
		}else if (com.equals("/updateCart.do")) {
			service = new UpdateCartService();
			service.execute(request, response);
			viewPage = "cartView.do";
		}else if (com.equals("/checkout.do")) {
			service = new CheckoutService();
			service.execute(request, response);
			viewPage = "cart/checkout.jsp";
		}else if (com.equals("/orderProduct.do")) {
			service = new OrderProductService();
			service.execute(request, response);
			viewPage = "cart/orderResult.jsp";
		}else if (com.equals("/orderListView.do")) {
			service = new OrderedListService();
			service.execute(request, response);
			viewPage = "cart/orderedList.jsp";
		}
		
		System.out.println(viewPage);
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
