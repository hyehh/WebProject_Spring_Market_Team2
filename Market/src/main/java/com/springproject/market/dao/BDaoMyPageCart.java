package com.springproject.market.dao;

import java.util.ArrayList;

import com.springproject.market.dto.BDtoMyPageCart;

public interface BDaoMyPageCart {

	// 2021.07.26 조혜지 - 장바구니에 같은 제품이 존재하는지 여부 체크하는 method
	public int cartCheckDao(String cId, int pCode, int cart);
	
	// 2021.07.06 조혜지 - 장바구니 목록 DB에서 불러오는 method
	public ArrayList<BDtoMyPageCart> customerCartListDao(String strcId, int from, int to);
	
	// 2021.07.06 조혜지 - 장바구니 페이지 분할을 위해 몇 줄인지 세는 method
	public String cartDao(String strcId);
	
	// 2021.07.06 조혜지 - 장바구니 버튼 클릭시 DB에 insert하는 method
	public void cartInsertDao(int wQuantity, String cId, int pCode);
	
	// 2021.07.06 조혜지 - 장바구니 버튼 클릭시 이미 있는 제품인 경우 DB에 수량 update하는 method
	public void cartUpdateDao(int wQuantity, String cId, int pCode);
	
	// 2021.07.06 조혜지 - 장바구니 전체 상품 삭제하는 method
	public void cartAllDeleteDao(String cId);
	
	// 2021.07.06 조혜지 - 장바구니 선택 상품 삭제하는 method
	public void cartDeleteDao(String cId, int wId);
	
}