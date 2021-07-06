package com.springproject.market.command;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BBuyDao;
import com.springproject.market.dto.BBuyDto;

public class BCommandMyPageBuyConfirmList implements BCommand { // 2021.07.06 조혜지 - 결제 완료 시 주문한 상품 리스트 불러오는 command

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub
		
// 		**********수훈님과 연동 시 변경하기***************
//		String cId = Share.userId;	
		String cId = "jenny78";	

		BBuyDao dao = new BBuyDao();
		String bNumber = dao.bNumber(cId);
		System.out.println(cId);
		System.out.println(bNumber);
		System.out.println("여기 confirm");
		session.setAttribute("bNumber", bNumber);
		
		ArrayList<BBuyDto> dtos = dao.buyConfirmList(cId, bNumber);
		
		model.addAttribute("BUYCONFIRM", dtos);
	}

}
