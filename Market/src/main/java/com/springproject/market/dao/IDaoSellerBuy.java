package com.springproject.market.dao;

import java.util.ArrayList;

import com.springproject.market.dto.BDtoSellerBuy;

public interface IDaoSellerBuy {
	public ArrayList<BDtoSellerBuy> buyList(int start, int end);
	
	public int buy();
	
	public int buyTrue();
	
	public int buyCancel();
	
	public ArrayList<BDtoSellerBuy> buyTrueList(int start, int end);
	
	public ArrayList<BDtoSellerBuy> buyCancelList(int start, int end);
	
	public ArrayList<BDtoSellerBuy> buySearch(String search, String searchtxt, int start, int end);
	
	public int searchBuyCount(String search, String searchtxt);
	
	public BDtoSellerBuy selectBuy(String selecCode);
	
	public ArrayList<BDtoSellerBuy> selectBuyPageList(String getName);
	
	public void session();
}
