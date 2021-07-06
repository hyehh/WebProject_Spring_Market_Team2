package com.springproject.market.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

public class BCommandMyPageReviewBNum implements BCommand { // 2021.07.06 조혜지 - 리뷰 등록 시 주문번호 session에 저장하기 위한 command

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String bNumber = request.getParameter("bNumber");
		String pCode = request.getParameter("pCode");

		session.setAttribute("bNumber", bNumber);
		session.setAttribute("pCode", pCode);
	}

}
