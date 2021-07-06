package com.springproject.market.dao;

import com.springproject.market.dto.BDtoMyPageCustomerInfo;

public interface BDaoMyPageCustomerInfo {

	// 2021.07.06 조혜지 - 고객 회원 정보 수정 db에 있는 정보 불러오는 method
	public BDtoMyPageCustomerInfo customerinfoDao(String strcId);
	
	// 2021.07.06 조혜지 - 고객 회원 정보 수정 버튼 클릭 시 정보 업데이트 method
	public void customerInfoUpdateDao(String cName, String cEmail, String cBirth, String cTel, String cPostalCode, String cAddress1, String cAddress2, String cId);

	// 2021.07.06 조혜지 - 고객 비밀번호 변경하는 method
	public void customerPwUpdateDao(String cPw, String cId);

	// 2021.07.06 조혜지 - 고객 회원 탈퇴하는 method
	public void customerSignOutDao(String cSignOutReason, String cSignOutContent, String cId);
	
}
