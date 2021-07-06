package com.springproject.market.dao;

import com.springproject.market.dto.BDtoMyPageSellerInfo;

public interface BDaoMyPageSellerInfo {
	
	// 2021.07.06 조혜지 - 판매자 정보 수정 db에 있는 정보 불러오는 method
	public BDtoMyPageSellerInfo sellerinfoDao(String strsId);
	
	// 2021.07.06 조혜지 - 판매자 정보 수정 버튼 클릭 시 정보 업데이트 method
	public void sellerInfoUpdateDao(String sName, String sEmail, String sBirth, String sTel, String sPostalCode, String sAddress1, String sAddress2, String sShopName, String sNumber, String sId);
	
	// 2021.07.06 조혜지 - 판매자 비밀번호 변경하는 method
	public void sellerPwUpdateDao(String sPw, String sId);

	// 2021.07.06 조혜지 -판매자 탈퇴하는 method
	public void sellerSignOutDao(String sSignOutReason, String sSignOutContent, String sId);
	
}
