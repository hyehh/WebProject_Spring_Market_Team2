package com.springproject.market.dao;

import com.springproject.market.dto.BDtoLogin;

public interface IDaoLoginAction {
	
	public BDtoLogin customerLoginAction(String loginId,  String loginPw);
	
	public BDtoLogin sellerLoginAction(String loginId,  String loginPw);

}
