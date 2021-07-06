package com.springproject.market.command;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BDaoMyPageCart;

public class BCommandMyPageCartAllDelete implements BCommand { // 2021.07.06 조혜지 - 장바구니 전체 상품 삭제하는 command

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub

// 		**********수훈님과 연동 시 변경하기***************
//		String cId = Share.userId;	
		String cId = "jenny78";	
		
		BDaoMyPageCart dao = sqlSession.getMapper(BDaoMyPageCart.class);
		dao.cartAllDeleteDao(cId);
		
	}

}
