package com.springproject.market.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BDaoMyPageCart;
import com.springproject.market.util.Share;

public class BCommandMyPageCartUpdate implements BCommand { // 2021.07.05 조혜지 - 장바구니 버튼 클릭시 이미 있는 제품인 경우 DB에 수량 update하는 command

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String wQuantity = request.getParameter("wQuantity");

		String cId = Share.userId;	
		
		String pCode = request.getParameter("pCode");
		
		BDaoMyPageCart dao = sqlSession.getMapper(BDaoMyPageCart.class);
		dao.cartUpdateDao(Integer.parseInt(wQuantity), cId, Integer.parseInt(pCode));
						
		Share.pCode = pCode;
	}

}
