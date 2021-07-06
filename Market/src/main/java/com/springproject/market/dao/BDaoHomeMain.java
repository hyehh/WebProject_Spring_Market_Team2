package com.springproject.market.dao;

import java.util.ArrayList;

import com.springproject.market.dto.BDtoBest;
import com.springproject.market.dto.BDtoProductList;

public interface BDaoHomeMain {
	
	// 베스트 4개
	public ArrayList<BDtoBest> mainBest();
	
	// new 4개
	public ArrayList<BDtoProductList> mainNew();
	
	// 랜덤 20개
	public ArrayList<BDtoProductList> mainRand();
}
