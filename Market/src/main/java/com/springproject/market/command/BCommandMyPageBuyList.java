package com.springproject.market.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BDaoMyPageBuy;
import com.springproject.market.dto.BDtoMyPageBuy;
import com.springproject.market.util.Share;

public class BCommandMyPageBuyList implements BCommand { // 2021.07.06 조혜지 - 장바구니에서 선택한 상품만 가져오는 command

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String[] selectedProduct = request.getParameterValues("selectedProduct");
		int wId = 0;
		ArrayList<BDtoMyPageBuy> dtos = new ArrayList<BDtoMyPageBuy>();
		
		for(int i=0; i<selectedProduct.length; i++) {
			wId = Integer.parseInt(selectedProduct[i]);
			
			String cId = Share.userId;	
			
			BDaoMyPageBuy dao = sqlSession.getMapper(BDaoMyPageBuy.class);
			BDtoMyPageBuy dto = dao.customerBuyListDao(cId, wId);
			dtos.add(dto);
			
			session.setAttribute("BUY", dtos);
			System.out.println(wId);
			
		}
		
		int size = dtos.size();
		System.out.println(size);
		session.setAttribute("size", size);

	}

}
