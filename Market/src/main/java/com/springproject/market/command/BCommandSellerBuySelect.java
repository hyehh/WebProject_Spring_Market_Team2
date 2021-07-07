package com.springproject.market.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BDaoSellerBuy;
import com.springproject.market.dto.BDtoSellerBuy;

public class BCommandSellerBuySelect implements BCommand {

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();

		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String bNumber = request.getParameter("bNumber");
		BDaoSellerBuy dao = new BDaoSellerBuy();
		BDtoSellerBuy dto = dao.selectBuy(bNumber);
		
		ArrayList<BDtoSellerBuy> dtos = dao.selectBuyPageList(bNumber);
		
		model.addAttribute("selectBuy", dto);
		model.addAttribute("selectBuyList", dtos);
	}

}
