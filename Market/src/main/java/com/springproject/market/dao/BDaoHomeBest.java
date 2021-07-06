package com.springproject.market.dao;

import java.util.ArrayList;

import com.springproject.market.dto.BDtoBest;

public interface BDaoHomeBest {
	
	public ArrayList<BDtoBest> kitchen();
	
	public ArrayList<BDtoBest> washing();
	
	public ArrayList<BDtoBest> cleaning();
	
	public ArrayList<BDtoBest> interior();

	public ArrayList<BDtoBest> can();
	
	public ArrayList<BDtoBest> frozen();

	public ArrayList<BDtoBest> beverage();
	
}
