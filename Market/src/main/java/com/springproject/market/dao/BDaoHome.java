package com.springproject.market.dao;

import java.util.ArrayList;

import com.springproject.market.dto.BDtoProduct;
import com.springproject.market.dto.BDtoProductList;
import com.springproject.market.dto.BDtoQnA;
import com.springproject.market.dto.BDtoReview;

public interface BDaoHome {
	
	// 제품 상세페이지
	public BDtoProduct productView(String strCode);
	
	// 문의 등록 - 이미지 없음
	public void registerQ(String pCode, String cId, String qTitle, String qContent);
	
	// 문의 등록 - 이미지 등록
//	public void registerQ(int pCode, String cId, String qTitle, String qContent, String qFilePath);
	
	// 문의 list 보여주기
	public ArrayList<BDtoQnA> qnaList(String strCode, int from, int to);
	
	// 전체 문의의 갯수
	public int qnaListCount(String strCode);
	
	// 리뷰 list 보여주기
	public ArrayList<BDtoReview> reviewList(String strCode, int from, int to);
	
	// 전체 리뷰 갯수
	public int reivewListCount(String strCode);
	
	// 제품 평균 별점 불러오기
	public BDtoReview reviewStar(String strCode);
	
	// 마감임박 상품 리스트
	public ArrayList<BDtoProductList> deadlineList();
	
	// new 상품 리스트
	public ArrayList<BDtoProductList> newList();
	
	// 검색
	public ArrayList<BDtoProductList> searchList(String searchName);
	
}
