package com.springproject.market.command;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BDaoMyPageBuy;
import com.springproject.market.dto.BDtoMyPageBuy;
import com.springproject.market.util.Share;

public class BCommandMyPageAllBuyList implements BCommand { // 2021.07.06 조혜지 - 장바구니에서 전체 상품 가져오는 command

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub
		
		String cId = Share.userId;	
		
		BDaoMyPageBuy dao = sqlSession.getMapper(BDaoMyPageBuy.class);
		ArrayList<BDtoMyPageBuy> dtos = dao.allBuyListDao(cId);
		
		session.setAttribute("CARTBUY", dtos);
		
		int asize = dtos.size();
		session.setAttribute("asize", asize);
	}

}
