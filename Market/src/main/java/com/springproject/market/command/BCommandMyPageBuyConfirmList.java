package com.springproject.market.command;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BDaoMyPageBuy;
import com.springproject.market.dto.BDtoMyPageBuy;
import com.springproject.market.util.Share;

public class BCommandMyPageBuyConfirmList implements BCommand { // 2021.07.06 조혜지 - 결제 완료 시 주문한 상품 리스트 불러오는 command

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub
		
		String cId = Share.userId;	

		BDaoMyPageBuy dao = sqlSession.getMapper(BDaoMyPageBuy.class);
		String bNumber = dao.bNumberDao(cId);

		session.setAttribute("bNumber", bNumber);
		
		ArrayList<BDtoMyPageBuy> dtos = dao.buyConfirmListDao(cId, bNumber);
		
		model.addAttribute("BUYCONFIRM", dtos);
	}

}
