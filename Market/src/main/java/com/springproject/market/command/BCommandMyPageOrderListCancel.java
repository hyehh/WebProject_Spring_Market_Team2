package com.springproject.market.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BDaoMyPageBuy;
import com.springproject.market.dto.BDtoMyPageBuy;

public class BCommandMyPageOrderListCancel implements BCommand { // 2021.07.06 조혜지 - 구매 취소 버튼 클릭 시 취소일자 업데이트 & 취소 내역 리스트 보여주는 command

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
// 		**********수훈님과 연동 시 변경하기***************
//		String cId = Share.userId;	
		String cId = "jenny78";	
		String bNumber = request.getParameter("bNumber");
		
		BDaoMyPageBuy dao = sqlSession.getMapper(BDaoMyPageBuy.class);
		dao.cancelDayDao(bNumber);
		
		request.setAttribute("bNumber", bNumber);
		
		ArrayList<BDtoMyPageBuy> dtos = dao.buyConfirmListDao(cId, bNumber);
		
		request.setAttribute("BUYCONFIRM", dtos);
	}

}
