package com.springproject.market.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BDaoMyPageCart;
import com.springproject.market.util.Share;

public class BCommandMyPageCartInsert implements BCommand { // 2021.07.05 조혜지 - 장바구니 추가하는 command

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String wQuantity = request.getParameter("wQuantity");
		
// 		**********수훈님과 연동 시 변경하기***************
//		String cId = Share.userId;	
		String cId = "jenny78";		

		String pCode = request.getParameter("pCode");
				
		BDaoMyPageCart dao = sqlSession.getMapper(BDaoMyPageCart.class);
		dao.cartInsertDao(Integer.parseInt(wQuantity), cId, Integer.parseInt(pCode));
				
		Share.pCode = pCode;
	}

}
