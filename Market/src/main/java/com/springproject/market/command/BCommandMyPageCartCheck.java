package com.springproject.market.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BDaoMyPageCart;

public class BCommandMyPageCartCheck implements BCommand { // 2021.07.05 조혜지 - 장바구니에 같은 제품이 존재하는지 여부 체크하는 command

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub	
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
// 		**********수훈님과 연동 시 변경하기***************
//		String cId = Share.userId;	
		String cId = "jenny78";	
		
		String pCode = request.getParameter("pCode");
		
		int cart = 0;
		
		BDaoMyPageCart dao = sqlSession.getMapper(BDaoMyPageCart.class);
		int check = dao.cartCheckDao(cId, Integer.parseInt(pCode), cart);
		
		session.setAttribute("check", check);

	}

}
