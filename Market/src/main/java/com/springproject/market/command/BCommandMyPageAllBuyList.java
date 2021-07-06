package com.springproject.market.command;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BDaoMyPageBuy;
import com.springproject.market.dto.BDtoMyPageBuy;

public class BCommandMyPageAllBuyList implements BCommand { // 2021.07.06 조혜지 - 장바구니에서 전체 상품 가져오는 command

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub
		
// 		**********수훈님과 연동 시 변경하기***************
//		String cId = Share.userId;	
		String cId = "jenny78";	
		
		BDaoMyPageBuy dao = sqlSession.getMapper(BDaoMyPageBuy.class);
		ArrayList<BDtoMyPageBuy> dtos = dao.allBuyListDao(cId);
		
		session.setAttribute("CARTBUY", dtos);
		
		int asize = dtos.size();
		session.setAttribute("asize", asize);
	}

}
