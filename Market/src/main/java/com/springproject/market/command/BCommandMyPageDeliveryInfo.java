package com.springproject.market.command;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BBuyDao;
import com.springproject.market.dto.BBuyDto;

public class BCommandMyPageDeliveryInfo implements BCommand { // 2021.07.06 조혜지 - 결제 완료 시 배송 정보 불러오는 command

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub
		
// 		**********수훈님과 연동 시 변경하기***************
//		String cId = Share.userId;	
		String cId = "jenny78";
		String bNumber = (String)session.getAttribute("bNumber");
		
		BBuyDao dao = new BBuyDao();
		BBuyDto dto = dao.deliveryinfo(cId, bNumber);
		
		model.addAttribute("DELIVERY", dto);
	}

}
