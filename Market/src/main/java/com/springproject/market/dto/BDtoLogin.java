package com.springproject.market.dto;

public class BDtoLogin {
	
	String userId ;
	String userPw;
	
	public BDtoLogin() {
		// TODO Auto-generated constructor stub
	}

	public BDtoLogin(String userId, String userPw) {
		super();
		this.userId = userId;
		this.userPw = userPw;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	
	
	

}
