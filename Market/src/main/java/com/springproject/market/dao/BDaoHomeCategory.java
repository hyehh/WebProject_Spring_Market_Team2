package com.springproject.market.dao;

import java.util.ArrayList;

import com.springproject.market.dto.BDtoProductList;

public interface BDaoHomeCategory {

	public ArrayList<BDtoProductList> categoryList(String pCategory);
}
