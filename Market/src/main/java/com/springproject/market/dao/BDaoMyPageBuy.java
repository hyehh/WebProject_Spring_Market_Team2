package com.springproject.market.dao;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.springproject.market.dto.BDtoMyPageBuy;

public interface BDaoMyPageBuy {

	// 2021.07.06 조혜지 - 장바구니에서 선택 상품 주문 버튼 클릭 시 선택한 상품만 DB에서 불러오는 method
	public BDtoMyPageBuy customerBuyListDao(String strcId, int inwId);
	
	// 2021.07.06 조혜지 - 장바구니 전체 구매를 하기 위해 장바구니 목록 DB에서 불러오는 method
	public ArrayList<BDtoMyPageBuy> allBuyListDao(String strcId);
	
	// 2021.07.06 조혜지 - 주문자 정보 불러오기 위해 DB에 있는 정보 불러오는 method
	public BDtoMyPageBuy buyerInfoDao(String strcId);
	
	// 2021.07.06 조혜지 - 선택 상품 주문 선택 후, 결제하기 버튼 클릭시 BnS와 Receiver DB에 insert하는 method
	public void insertDao(String cId, String bNumber, String bRecName, String bRecPostalCode, String bRecAddress1, String bRecAddress2, String bRecTel, String bRecContent, HttpSession session);
	
	// 2021.07.06 조혜지 - 전체 상품 주문 선택 후, 결제하기 버튼 클릭시 BnS와 Receiver DB에 insert하는 method
	public void allInsertDao(String cId, String bNumber, String bRecName, String bRecPostalCode, String bRecAddress1, String bRecAddress2, String bRecTel, String bRecContent, HttpSession session);
	
	// 2021.07.06 조혜지 - 결제 완료 시 주문 번호 불러오는 method
	public String bNumberDao(String strcId);
	
	// 2021.07.06 조혜지 - 장바구니에서 구매한 제품의 pCode 구하는 method
	public ArrayList<BDtoMyPageBuy> pCodeListDao(String bNumber);
	
	// 2021.07.06 조혜지 - 장바구니에서 구매한 제품 삭제하는 method
	public void buyConfirmCartDeleteDao(String cId, HttpSession session);
	
	// 2021.07.06 조혜지 - 결제 완료 시 주문한 상품 리스트 불러오는 method
	public ArrayList<BDtoMyPageBuy> buyConfirmListDao(String strcId, String strbNumber);
	
	// 2021.07.06 조혜지 - 결제 완료 시 배송 정보 불러오는 method
	public BDtoMyPageBuy deliveryinfoDao(String strcId, String strbNumber);
	
	// 2021.07.06 조혜지 - 구매 취소 버튼 클릭 시 취소일자 업데이트하는 method
	public void cancelDayDao(String strbNumber);
	
	// 2021.07.06 조혜지 - 주문목록/배송조회 주문한 상품 리스트 불러오는 method
	public ArrayList<BDtoMyPageBuy> orderListDao(String strcId, int from, int to);
	
	// 2021.07.06 조혜지 - 주문목록/배송조회 페이지 분할을 위해 몇 줄인지 세는 method
	public int orderDao(String strcId);
	
	// 2021.07.06 조혜지 - 주문 취소한 상품 리스트 불러오는 method
	public ArrayList<BDtoMyPageBuy> cancelListDao(String strcId, int from, int to);
	
	// 2021.07.06 조혜지 - 주문취소목록 페이지 분할을 위해 몇 줄인지 세는 method
	public int cancelDao(String strcId);

}
