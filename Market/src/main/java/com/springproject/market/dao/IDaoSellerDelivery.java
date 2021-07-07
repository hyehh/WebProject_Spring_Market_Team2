package com.springproject.market.dao;

import java.util.ArrayList;

import com.springproject.market.dto.BDtoSellerDelivery;

public interface IDaoSellerDelivery {
	public ArrayList<BDtoSellerDelivery> deliveryList(int start, int end);

	public int deliveryAll();

	public int deliveryEnd();

	public int deliveryING();

	public ArrayList<BDtoSellerDelivery> deliverySearch(String search, String searchtxt, int start, int end);

	public int searchDeliveryCount(String search, String searchtxt, int start, int end);

	public ArrayList<BDtoSellerDelivery> deliveryEndList(int start, int end);

	public ArrayList<BDtoSellerDelivery> deliveryINGList(int start, int end);

	public BDtoSellerDelivery selectOrderPage(String selecCode);

	public ArrayList<BDtoSellerDelivery> selectDPageList(String getName);

	public String setCName(String selecCode);

	public void deliveryStart(String bNumber);

	public void deliveryFinish(String bNumber);

	public void session();

	public void sqlUpdates();
}
