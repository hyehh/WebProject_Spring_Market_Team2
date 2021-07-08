package com.springproject.market.command;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BDaoMyPageCart;
import com.springproject.market.util.Share;

public class BCommandMyPageCartAllDelete implements BCommand { // 2021.07.06 조혜지 - 장바구니 전체 상품 삭제하는 command

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub

		String cId = Share.userId;	
		
		BDaoMyPageCart dao = sqlSession.getMapper(BDaoMyPageCart.class);
		dao.cartAllDeleteDao(cId);
		
	}

}
