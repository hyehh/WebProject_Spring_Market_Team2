package com.springproject.market.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.springproject.market.command.BCommand;
import com.springproject.market.command.BCommandHomeProductQ;
import com.springproject.market.command.BCommandMyPageAllBuy;
import com.springproject.market.command.BCommandMyPageAllBuyList;
import com.springproject.market.command.BCommandMyPageBuy;
import com.springproject.market.command.BCommandMyPageBuyConfirmCartDelete;
import com.springproject.market.command.BCommandMyPageBuyConfirmList;
import com.springproject.market.command.BCommandMyPageBuyList;
import com.springproject.market.command.BCommandMyPageBuyer;
import com.springproject.market.command.BCommandMyPageCancel;
import com.springproject.market.command.BCommandMyPageCancelList;
import com.springproject.market.command.BCommandMyPageCartAllDelete;
import com.springproject.market.command.BCommandMyPageCartInsert;
import com.springproject.market.command.BCommandMyPageCartList;
import com.springproject.market.command.BCommandMyPageCartUpdate;
import com.springproject.market.command.BCommandMyPageCartCheck;
import com.springproject.market.command.BCommandMyPageCartDelete;
import com.springproject.market.command.BCommandMyPageCustomerInfoUpdate;
import com.springproject.market.command.BCommandMyPageCustomerPwUpdate;
import com.springproject.market.command.BCommandMyPageCustomerSignOut;
import com.springproject.market.command.BCommandMyPageDeliveryInfo;
import com.springproject.market.command.BCommandMyPageOrderList;
import com.springproject.market.command.BCommandMyPageOrderListCancel;
import com.springproject.market.command.BCommandMyPagePCode;
import com.springproject.market.command.BCommandMyPageReviewBNum;
import com.springproject.market.command.BCommandMyPageReviewDelete;
import com.springproject.market.command.BCommandMyPageReviewDeleteList;
import com.springproject.market.command.BCommandMyPageReviewRegistration;
import com.springproject.market.command.BCommandMyPageReviewRegistrationList;
import com.springproject.market.command.BCommandMyPageSellerInfo;
import com.springproject.market.command.BCommandMyPageSellerInfoUpdate;
import com.springproject.market.command.BCommandMyPageSellerPwUpdate;
import com.springproject.market.command.BCommandMyPageSellerSignOut;
import com.springproject.market.dao.BDaoMyPageCustomerInfo;
import com.springproject.market.command.BCommandMyPageCustomerInfo;

@Controller
public class BControllerMyPage {// 2021.07.05 조혜지 - controller 추가
	
	//private JdbcTemplate template;
	BCommand command = null;
	public static int from;
	public static int to;
	private BCommand commandMyPageAllBuy = null;
	private BCommand commandMyPageAllBuyList = null;
	private BCommand commandMyPageBuy = null;
	private BCommand commandMyPageBuyConfirmCartDelete = null;
	private BCommand commandMyPageBuyConfirmList = null;
	private BCommand commandMyPageBuyer = null;
	private BCommand commandMyPageBuyList = null;
	private BCommand commandMyPageCancel = null;
	private BCommand commandMyPageCancelList = null;
	private BCommand commandMyPageCartAllDelete = null;
	private BCommand commandMyPageCartCheck = null;
	private BCommand commandMyPageCartDelete = null;
	private BCommand commandMyPageCartInsert = null;
	private BCommand commandMyPageCartList = null;
	private BCommand commandMyPageCartUpdate = null;
	private BCommand commandMyPageCustomerInfo = null;
	private BCommand commandMyPageCustomerInfoUpdate = null;
	private BCommand commandMyPageCustomerPwUpdate = null;
	private BCommand commandMyPageCustomerSignOut = null;
	private BCommand commandMyPageDeliveryInfo = null;
	private BCommand commandMyPageOrderList = null;
	private BCommand commandMyPageOrderListCancel = null;
	private BCommand commandMyPagePCode = null;
	private BCommand commandMyPageReviewBNum = null;
	private BCommand commandMyPageReviewDelete = null;
	private BCommand commandMyPageReviewDeleteList = null;
	private BCommand commandMyPageReviewRegistration = null;
	private BCommand commandMyPageReviewRegistrationList = null;
	private BCommand commandMyPageSellerInfo = null;
	private BCommand commandMyPageSellerInfoUpdate = null;
	private BCommand commandMyPageSellerPwUpdate = null;
	private BCommand commandMyPageSellerSignOut = null;
	
	@Autowired
	public void auto(BCommand allBuy, BCommand allBuyList, BCommand buy, BCommand buyConfirmCartDelete, BCommand buyConfirmList, 
			BCommand buyer, BCommand buyList, BCommand cancel, BCommand cancelList, BCommand cartAllDelete, BCommand cartCheck,
			BCommand cartDelete, BCommand cartInsert, BCommand cartList, BCommand cartUpdate, BCommand customerInfo, 
			BCommand customerInfoUpdate, BCommand customerPwUpdate, BCommand customerSignOut, BCommand deliveryInfo, 
			BCommand orderList, BCommand orderListCancel, BCommand pCode, BCommand reviewBNum, BCommand reviewDelete,
			BCommand reviewDeleteList, BCommand reviewRegistration, BCommand reviewRegistrationList, BCommand sellerInfo,
			BCommand sellerInfoUpdate, BCommand sellerPwUpdate, BCommand sellerSignOut) {
		this.commandMyPageAllBuy = allBuy;
		this.commandMyPageAllBuyList = allBuyList;
		this.commandMyPageBuy = buy;
		this.commandMyPageBuyConfirmCartDelete = buyConfirmCartDelete;
		this.commandMyPageBuyConfirmList = buyConfirmList;
		this.commandMyPageBuyer = buyer;
		this.commandMyPageBuyList = buyList;
		this.commandMyPageCancel = cancel;
		this.commandMyPageCancelList = cancelList;
		this.commandMyPageCartAllDelete = cartAllDelete;
		this.commandMyPageCartCheck = cartCheck;
		this.commandMyPageCartDelete = cartDelete;
		this.commandMyPageCartInsert = cartInsert;
		this.commandMyPageCartList = cartList;
		this.commandMyPageCartUpdate = cartUpdate;
		this.commandMyPageCustomerInfo = customerInfo;
		this.commandMyPageCustomerInfoUpdate = customerInfoUpdate;
		this.commandMyPageCustomerPwUpdate = customerPwUpdate;
		this.commandMyPageCustomerSignOut = customerSignOut;
		this.commandMyPageDeliveryInfo = deliveryInfo;
		this.commandMyPageOrderList = orderList;
		this.commandMyPageOrderListCancel = orderListCancel;
		this.commandMyPagePCode = pCode;
		this.commandMyPageReviewBNum = reviewBNum;
		this.commandMyPageReviewDelete = reviewDelete;
		this.commandMyPageReviewDeleteList = reviewDeleteList;
		this.commandMyPageReviewRegistration = reviewRegistration;
		this.commandMyPageReviewRegistrationList = reviewRegistrationList;
		this.commandMyPageSellerInfo = sellerInfo;
		this.commandMyPageSellerInfoUpdate = sellerInfoUpdate;
		this.commandMyPageSellerPwUpdate = sellerPwUpdate;
		this.commandMyPageSellerSignOut = sellerSignOut;
	}
	
	@Autowired
	private SqlSession sqlSession;
		
	// 고객 회원정보 변경 버튼 눌렀을 때 DB에 있는 회원 정보 불러오기
	@RequestMapping("/CustomerInfoUpdate_View")
	public String CustomerInfoUpdate_View(HttpSession session, Model model) {
		commandMyPageCustomerInfo.execute(session, model, sqlSession);
		return "CustomerInfoUpdate_View";
	}
	
	// 고객 회원정보 변경 페이지에서 확인 눌렀을 때 DB 수정하기
	@RequestMapping("/CustomerInfoUpdate")
	public String CustomerInfoUpdate(HttpSession session, Model model, HttpServletRequest request) {
		model.addAttribute("request", request);
		commandMyPageCustomerInfoUpdate.execute(session, model, sqlSession);
		return "redirect:CustomerInfoUpdate_View";
	}
	
	// 판매자정보 변경 버튼 눌렀을 때 DB에 있는 판매자 정보 불러오기
	@RequestMapping("/SellerInfoUpdate_View")
	public String SellerInfoUpdate_View(HttpSession session, Model model) {
		commandMyPageSellerInfo.execute(session, model, sqlSession);
		return "SellerInfoUpdate_View";
	}
	
	// 판매자정보 변경 페이지에서 확인 눌렀을 때 DB 수정하기
	@RequestMapping("/SellerInfoUpdate")
	public String SellerInfoUpdate(HttpSession session, Model model, HttpServletRequest request) {
		model.addAttribute("request", request);
		commandMyPageSellerInfoUpdate.execute(session, model, sqlSession);
		return "redirect:SellerInfoUpdate_View";
	}
	
	// 고객 비밀번호 변경 창 보여주기
	@RequestMapping("/CustomerPwUpdate_View")
	public String CustomerPwUpdate_View() {
		return "CustomerPwUpdate_View";
	}
	
	// 고객 비밀번호 변경하기
	@RequestMapping("/CustomerPwUpdate")
	public String CustomerPwUpdate(HttpSession session, Model model, HttpServletRequest request) {
		model.addAttribute("request", request);
		commandMyPageCustomerPwUpdate.execute(session, model, sqlSession);
		return "CustomerPwUpdate_View";
	}
	
	// 판매자 비밀번호 변경 창 보여주기
	@RequestMapping("/SellerPwUpdate_View")
	public String SellerPwUpdate_View() {
		return "SellerPwUpdate_View";
	}
	
	// 판매자 비밀번호 변경하기
	@RequestMapping("/SellerPwUpdate")
	public String SellerPwUpdate(HttpSession session, Model model, HttpServletRequest request) {
		model.addAttribute("request", request);
		commandMyPageSellerPwUpdate.execute(session, model, sqlSession);
		return "SellerPwUpdate_View";
	}
	
	// 고객 탈퇴 창 보여주기
	@RequestMapping("/CustomerSignOut_View")
	public String CustomerSignOut_View() {
		return "CustomerSignOut_View";
	}
	
	// 고객 탈퇴하기
	@RequestMapping("/CustomerSignOut")
	public String CustomerSignOut(HttpSession session, Model model, HttpServletRequest request) {
		model.addAttribute("request", request);
		commandMyPageCustomerSignOut.execute(session, model, sqlSession);
		session.invalidate();
		return "redirect:main";
	}
	
	// 판매자 탈퇴 창 보여주기
	@RequestMapping("/SellerSignOut_View")
	public String SellerSignOut_View() {
		return "SellerSignOut_View";
	}
	
	// 판매자 탈퇴하기
	@RequestMapping("/SellerSignOut")
	public String SellerSignOut(HttpSession session, Model model, HttpServletRequest request) {
		model.addAttribute("request", request);
		commandMyPageSellerSignOut.execute(session, model, sqlSession);
		session.invalidate();
		return "redirect:main";
	}

	// 장바구니 중복체크하고 DB에 수량과 장바구니 일자 insert
	@RequestMapping("/CustomerCartInsert")
	public String CustomerCartInsert(HttpSession session, Model model, HttpServletRequest request) {
		model.addAttribute("request", request);
		commandMyPageCartCheck.execute(session, model, sqlSession);
		
		String checkMessage;
		if((Integer)session.getAttribute("check") >= 1) {
			checkMessage = "이미 장바구니에 있는 제품입니다. 선택하신 수량만큼 업데이트 되었습니다.";
			session.setAttribute("checkMessage", checkMessage);
			commandMyPageCartUpdate.execute(session, model, sqlSession);
		}else {
			checkMessage = "장바구니에 담았습니다.";
			session.setAttribute("checkMessage", checkMessage);
			commandMyPageCartInsert.execute(session, model, sqlSession);
		}
		request.setAttribute("check", session.getAttribute("check"));
		request.setAttribute("checkMessage", session.getAttribute("checkMessage"));
		return "CartCheckAlert";
	}
	
	// 장바구니 alert 이후 다시 pCode 불러오기
	@RequestMapping("/productAgain")
	public String productAgain(HttpSession session, Model model, HttpServletRequest request) {
		model.addAttribute("request", request);
		command = new BCommandHomeProductQ();
		command.execute(session, model, sqlSession);
		return "product";
	}
	
	// 장바구니 DB에서 불러오기 & 페이지 분할
	@RequestMapping("/CustomerCart_View")
	public String CustomerCart_View(HttpSession session, Model model, HttpServletRequest request) {
		model.addAttribute("request", request);
		commandMyPageCartList.execute(session, model, sqlSession);
		return "CustomerCart_View";
	}
	
	// 장바구니 상품 전체 삭제
	@RequestMapping("/CartAllDelete")
	public String CartAllDelete(HttpSession session, Model model) {
		commandMyPageCartAllDelete.execute(session, model, sqlSession);
		return "redirect:CustomerCart_View";
	}
	
	// 장바구니 상품 선택 삭제
	@RequestMapping("/CartDelete")
	public String CartDelete(HttpSession session, Model model, HttpServletRequest request) {
		model.addAttribute("request", request);
		commandMyPageCartDelete.execute(session, model, sqlSession);
		return "redirect:CustomerCart_View";
	}
	
	// 장바구니 선택 상품 구매하기
	@RequestMapping("/Buy_View")
	public String Buy_View(HttpSession session, Model model, HttpServletRequest request) {
		model.addAttribute("request", request);
		commandMyPageBuyList.execute(session, model, sqlSession);
		commandMyPageBuyer.execute(session, model, sqlSession);
		return "Buy_View";
	}
	
	// 장바구니 선택 상품 구매 버튼 클릭 시 주문 및 결제 정보 DB에 Insert 하기
	@RequestMapping("/Buy")
	public String Buy(HttpSession session, Model model, HttpServletRequest request) {
		model.addAttribute("request", request);
		commandMyPageBuy.execute(session, model, sqlSession);
		return "redirect:BuyConfirm_View";
	}
	
	// 주문 완료 창 보여주기
	@RequestMapping("/BuyConfirm_View")
	public String BuyConfirm_View(HttpSession session, Model model) {
		commandMyPageBuyConfirmList.execute(session, model, sqlSession);
		commandMyPageDeliveryInfo.execute(session, model, sqlSession);
		commandMyPagePCode.execute(session, model, sqlSession);
		commandMyPageBuyConfirmCartDelete.execute(session, model, sqlSession);
		return "BuyConfirm_View";
	}
	
	// 장바구니 전체 상품 구매하기 
	@RequestMapping("/AllBuy_View")
	public String AllBuy_View(HttpSession session, Model model) {
		commandMyPageAllBuyList.execute(session, model, sqlSession);
		commandMyPageBuyer.execute(session, model, sqlSession);
		return "AllBuy_View";
	}
	
	// 장바구니 전체 상품 구매 버튼 클릭 시 주문 및 결제 정보 DB에 Insert 하기
	@RequestMapping("/AllBuy")
	public String AllBuy(HttpSession session, Model model, HttpServletRequest request) {
		model.addAttribute("request", request);
		commandMyPageAllBuy.execute(session, model, sqlSession);
		return "redirect:AllBuyConfirm_View";
	}
	
	// 주문 완료 창 보여주기
	@RequestMapping("/AllBuyConfirm_View")
	public String AllBuyConfirm_View(HttpSession session, Model model) {
		commandMyPageBuyConfirmList.execute(session, model, sqlSession);
		commandMyPageDeliveryInfo.execute(session, model, sqlSession);
		commandMyPagePCode.execute(session, model, sqlSession);
		commandMyPageBuyConfirmCartDelete.execute(session, model, sqlSession);
		return "AllBuyConfirm_View";
	}
	
	// 주문 완료 창에서 주문 취소하기
	@RequestMapping("/Cancel_View")
	public String Cancel_View(HttpSession session, Model model) {
		commandMyPageCancel.execute(session, model, sqlSession);
		commandMyPageBuyConfirmList.execute(session, model, sqlSession);
		return "Cancel_View";
	}
	
	// 리뷰 미등록 상품 목록 불러오기 & 페이지 분할
	@RequestMapping("/ReviewRegistrationList_View")
	public String ReviewRegistrationList_View(HttpSession session, Model model, HttpServletRequest request) {
		model.addAttribute("request", request);
		commandMyPageReviewRegistrationList.execute(session, model, sqlSession);
		return "ReviewRegistrationList_View";
	}
	
	// 리뷰 등록 시 주문번호 session에 저장하기
	@RequestMapping("/ReviewRegistration_View")
	public String ReviewRegistration_View(HttpSession session, Model model, HttpServletRequest request) {
		model.addAttribute("request", request);
		commandMyPageReviewBNum.execute(session, model, sqlSession);
		return "ReviewRegistration_View";
	}
	
	// 리뷰 등록하기
	@RequestMapping("/ReviewRegistration")
	public String ReviewRegistration(Model model, MultipartHttpServletRequest request) {
		HttpSession session = request.getSession();
		model.addAttribute("request", request);
		commandMyPageReviewRegistration.execute(session, model, sqlSession);
		return "redirect:ReviewRegistrationList_View";
	}
	
	// 리뷰 등록 상품 목록 불러오기 & 페이지 분할
	@RequestMapping("/ReviewDelete_View")
	public String ReviewDelete_View(HttpSession session, Model model, HttpServletRequest request) {
		model.addAttribute("request", request);
		commandMyPageReviewDeleteList.execute(session, model, sqlSession);
		return "ReviewDelete_View";
	}
	
	// 리뷰 등록 상품 삭제하기
	@RequestMapping("/ReviewDelete")
	public String ReviewDelete(HttpSession session, Model model, HttpServletRequest request) {
		model.addAttribute("request", request);
		commandMyPageReviewDelete.execute(session, model, sqlSession);
		return "redirect:ReviewDelete_View";
	}
	
	// 주문목록/배송조회 페이지에 들어갈 주문 상품 목록 불러오기 & 페이지 분할
	@RequestMapping("/OrderList_View")
	public String OrderList_View(HttpSession session, Model model, HttpServletRequest request) {
		model.addAttribute("request", request);
		commandMyPageOrderList.execute(session, model, sqlSession);
		return "OrderList_View";
	}
	
	// 주문목록/배송조회에서 주문 취소하기
	@RequestMapping("OrderListCancel_View")
	public String OrderListCancel_View(HttpSession session, Model model, HttpServletRequest request) {
		model.addAttribute("request", request);
		commandMyPageOrderListCancel.execute(session, model, sqlSession);
		return "OrderListCancel_View";
	}
	
	// 주문 취소 상품 목록 불러오기 & 페이지 분할
	@RequestMapping("OrderCancelList_View")
	public String OrderCancelList_View(HttpSession session, Model model, HttpServletRequest request) {
		model.addAttribute("request", request);
		commandMyPageCancelList.execute(session, model, sqlSession);
		return "OrderCancelList_View";
	}

}
