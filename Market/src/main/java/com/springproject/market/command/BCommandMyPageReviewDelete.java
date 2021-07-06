package com.springproject.market.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BReviewDao;

public class BCommandMyPageReviewDelete implements BCommand { // 2021.07.06 조혜지 - 리뷰 작성된 상품 삭제하는 command

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String bNumber = request.getParameter("bNumber");
		String pCode = request.getParameter("pCode");
		System.out.println(bNumber);
		System.out.println(Integer.parseInt(pCode));
		BReviewDao dao = new BReviewDao();
		dao.reviewDelete(bNumber, Integer.parseInt(pCode));
	}

}
