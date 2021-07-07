package com.springproject.market.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BDaoSellerQnA;
import com.springproject.market.dto.BDtoSellerQnA;

public class BCommandSellerQnASelect implements BCommand {

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();

		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String qnACode = request.getParameter("qnACode");
		BDaoSellerQnA dao = new BDaoSellerQnA();
		BDtoSellerQnA dto = dao.selectQnA(qnACode);
		System.out.println(qnACode);
		
		model.addAttribute("selectQnA", dto);
	}

}
