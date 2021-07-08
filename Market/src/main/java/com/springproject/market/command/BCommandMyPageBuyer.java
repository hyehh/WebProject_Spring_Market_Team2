package com.springproject.market.command;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BDaoMyPageBuy;
import com.springproject.market.dto.BDtoMyPageBuy;
import com.springproject.market.util.Share;

public class BCommandMyPageBuyer implements BCommand { // 2021.07.06 조혜지 - 주문서 작성/결제 창에서 DB에 있는 주문자 정보 불러오는 command

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub

		String cId = Share.userId;	
		
		BDaoMyPageBuy dao = sqlSession.getMapper(BDaoMyPageBuy.class);
		BDtoMyPageBuy dto = dao.buyerInfoDao(cId);
		
		model.addAttribute("BUYER", dto);
		
	}

}
