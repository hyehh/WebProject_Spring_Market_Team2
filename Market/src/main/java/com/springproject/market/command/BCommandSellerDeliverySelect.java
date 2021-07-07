package com.springproject.market.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BDaoSellerDelivery;
import com.springproject.market.dto.BDtoSellerDelivery;

public class BCommandSellerDeliverySelect implements BCommand {

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();

		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String bNumber = request.getParameter("bNumber");
		BDaoSellerDelivery dao = new BDaoSellerDelivery();
		
		
		BDtoSellerDelivery dto = dao.selectOrderPage(bNumber);
		
		
		
//		String cName = dao.setCName(bNumber); 
		ArrayList<BDtoSellerDelivery> dtos = dao.selectDPageList(bNumber);
		
		model.addAttribute("selectOrderPage", dto);
		model.addAttribute("OrderPageList", dtos);
	}

}
