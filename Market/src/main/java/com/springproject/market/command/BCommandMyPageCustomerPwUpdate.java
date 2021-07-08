package com.springproject.market.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BDaoMyPageCustomerInfo;
import com.springproject.market.util.Share;

public class BCommandMyPageCustomerPwUpdate implements BCommand { // 2021.07.05 조혜지 - 회원 비밀번호 변경 command

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String cPw = request.getParameter("cPw");

		String cId = Share.userId;	
		
		BDaoMyPageCustomerInfo dao = sqlSession.getMapper(BDaoMyPageCustomerInfo.class);
		dao.customerPwUpdateDao(cPw, cId);

	}

}
