package com.springproject.market.command;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BDaoMyPageBuy;
import com.springproject.market.dto.BDtoMyPageBuy;
import com.springproject.market.util.Share;

public class BCommandMyPageDeliveryInfo implements BCommand { // 2021.07.06 조혜지 - 결제 완료 시 배송 정보 불러오는 command

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub
		
		String cId = Share.userId;	
		String bNumber = (String)session.getAttribute("bNumber");
		
		BDaoMyPageBuy dao = sqlSession.getMapper(BDaoMyPageBuy.class);
		BDtoMyPageBuy dto = dao.deliveryinfoDao(cId, bNumber);
		
		model.addAttribute("DELIVERY", dto);
	}

}
