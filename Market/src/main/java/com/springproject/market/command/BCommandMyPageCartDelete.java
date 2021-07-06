package com.springproject.market.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BDaoMyPageCart;
import com.springproject.market.dto.BDtoMyPageCart;

public class BCommandMyPageCartDelete implements BCommand { // 2021.07.06 조혜지 - 장바구니 선택 상품 삭제하는 command

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String[] selectedProduct = request.getParameterValues("selectedProduct");
		int wId = 0;
		ArrayList<BDtoMyPageCart> dtos = new ArrayList<BDtoMyPageCart>();
		
		for(int i=0; i<selectedProduct.length; i++) {
			wId = Integer.parseInt(selectedProduct[i]);
			
//	 		**********수훈님과 연동 시 변경하기***************
//			String cId = Share.userId;	
			String cId = "jenny78";	
			
			BDaoMyPageCart dao = sqlSession.getMapper(BDaoMyPageCart.class);
			dao.cartDeleteDao(cId, wId);
			
		}
		
	}

}
