package com.springproject.market.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BDaoMyPageCart;
import com.springproject.market.util.Share;

public class BCommandMyPageCartCheck implements BCommand { // 2021.07.05 조혜지 - 장바구니에 같은 제품이 존재하는지 여부 체크하는 command

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub	
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String cId = Share.userId;	
		
		String pCode = request.getParameter("pCode");
		
		int cart = 0;
		
		BDaoMyPageCart dao = sqlSession.getMapper(BDaoMyPageCart.class);
		int check = dao.cartCheckDao(cId, Integer.parseInt(pCode));
		System.out.println("장바구니 확인 " + check);
		session.setAttribute("check", check);

	}

}
