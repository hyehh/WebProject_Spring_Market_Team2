package com.springproject.market.dao;

import java.util.ArrayList;

import com.springproject.market.dto.BDtoMyPageReview;

public interface BDaoMyPageReview {

	// 2021.07.06 조혜지 - 리뷰 미작성 목록 불러오는 method
	public ArrayList<BDtoMyPageReview> reviewRegistrationListDao(String strcId, int from, int to);
	
	// 2021.07.06 조혜지 - 리뷰등록 페이지 분할을 위해 몇 줄인지 세는 method
	public int reviewregDao(String strcId);
	
	// 2021.07.06 조혜지 - 리뷰 등록하는 method
	public void reviewRegistrationDao(int bReviewScore, String bReviewContent, String bNumber, int pCode, String rFilePath);
	
	// 2021.07.06 조혜지 - 리뷰 작성된 목록 불러오는 method
	public ArrayList<BDtoMyPageReview> reviewDeleteListDao(String strcId, int from, int to);
	
	// 2021.07.06 조혜지 - 리뷰삭제 페이지 분할을 위해 몇 줄인지 세는 method
	public int reviewdelDao(String strcId);
	
	// 2021.07.06 조혜지 - 리뷰 삭제하는 method
	public void reviewDeleteDao(String strbNumber, int intpCode);

}
