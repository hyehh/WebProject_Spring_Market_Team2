package com.springproject.market.dao;

import java.util.ArrayList;

import com.springproject.market.dto.BDtoSellerProduct;

public interface IDaoSellerProduct {
	public void deleteList(String pDelete, String pCode);

	public void deleteRegister(String pCode);

	public ArrayList<BDtoSellerProduct> productList(int start, int end);

	public int sales();

	public int salesTrue();

	public int salesFalse();

	public ArrayList<BDtoSellerProduct> searchProduct(String search, String searchtxt, int start, int end);

	public ArrayList<BDtoSellerProduct> salesTureList(int start, int end);

	public ArrayList<BDtoSellerProduct> salesFalseList(int start, int end);

	public BDtoSellerProduct selectProduct(String selecCode);

	public int selectProductBuyCount(String selecCode);

	public void productModify(String pCategory, String pName, String pPriceDC, String pProductEA, String pStatus,
			String pCode);

	public void upDateRegister(String pCode);

	public int getSearchCount(String search, String searchtxt);
}
