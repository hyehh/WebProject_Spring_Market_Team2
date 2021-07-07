package com.springproject.market.command;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BDaoSellerBuy;
import com.springproject.market.dao.BDaoSellerDelivery;
import com.springproject.market.dao.BDaoSellerProduct;
import com.springproject.market.dao.BDaoSellerQnA;

public class BCommandSellerStore implements BCommand {

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub
		BDaoSellerProduct daoSellerProduct = new BDaoSellerProduct();
		BDaoSellerBuy daoSellerBuy = new BDaoSellerBuy();
		BDaoSellerDelivery daoSellerDelivery = new BDaoSellerDelivery();
		BDaoSellerQnA daoSellerQnA = new BDaoSellerQnA();
		
		// 상품리스
		int salseCount = daoSellerProduct.sales();
		int salesTure = daoSellerProduct.salesTrue();
		int salesFalse = daoSellerProduct.salesFalse();
		model.addAttribute("salseCount", salseCount);
		model.addAttribute("salesTure", salesTure);
		model.addAttribute("salesFalse", salesFalse);
		
		// 주문리스트
		int buyCount = daoSellerBuy.buy();
		int buyTrue = daoSellerBuy.buyTrue();
		int buyCancel = daoSellerBuy.buyCancel();
		model.addAttribute("buyCount", buyCount);
		model.addAttribute("buyTrue", buyTrue);
		model.addAttribute("buyCancel", buyCancel);
		
		
		// 배송리스트
		int deliveryAll = daoSellerDelivery.deliveryAll();
		int deliveryEnd = daoSellerDelivery.deliveryEnd();
		int deliveryING = daoSellerDelivery.deliveryING();
		model.addAttribute("deliveryAll", deliveryAll);
		model.addAttribute("deliveryEnd", deliveryEnd);
		model.addAttribute("deliveryING", deliveryING);
		
		// 문의리스트
		int allQnACount = daoSellerQnA.allQnA();
		int QnATure = daoSellerQnA.QnATure();
		int QnAFalse = daoSellerQnA.QnAFalse();
		model.addAttribute("allQnACount", allQnACount);
		model.addAttribute("QnATure",QnATure);
		model.addAttribute("QnAFalse",QnAFalse);
	}

}
