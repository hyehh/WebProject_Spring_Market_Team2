package com.springproject.market.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BDaoSellerDelivery;

public class BCommandSellerDeliveryFor implements BCommand {

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();

		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		
		BDaoSellerDelivery dao = new BDaoSellerDelivery();
		String bNumber = request.getParameter("bNumber");
		System.out.println("커맨드" + bNumber);
		dao.deliveryStart(bNumber);
	}

}
