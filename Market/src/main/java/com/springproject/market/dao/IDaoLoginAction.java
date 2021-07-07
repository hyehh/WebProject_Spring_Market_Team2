package com.springproject.market.dao;

import com.springproject.market.dto.BDtoLogin;

public interface IDaoLoginAction {
	
	
	public String customerId(String loginId, String loginPw);
	public String customerPw(String loginId, String loginPw);
	
	public String sellerId(String loginId, String loginPw);
	public String sellerPw(String loginId, String loginPw);
	
}
