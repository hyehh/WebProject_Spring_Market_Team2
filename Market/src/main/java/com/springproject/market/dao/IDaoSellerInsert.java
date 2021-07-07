package com.springproject.market.dao;

public interface IDaoSellerInsert {
	public void insert(String pCategory, String pName, String pPrice, String pPriceDC, String pExpirationDate, 
			String pProductEA, String pStatus, String pFilePath);
	
	public int getCode();
	
	public void insertBnS(int pCode, String sId);
	
	public void insertRegister(int pCode, String sId);
}
