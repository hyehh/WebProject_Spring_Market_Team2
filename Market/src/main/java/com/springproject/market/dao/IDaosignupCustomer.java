package com.springproject.market.dao;

public interface IDaosignupCustomer {

	public void writeCustomer(String cId, String cPw, String cName, String cBirth, String cTel, String cAddress1, String cAddress2, String cEmail, String cPostalCode);
}
