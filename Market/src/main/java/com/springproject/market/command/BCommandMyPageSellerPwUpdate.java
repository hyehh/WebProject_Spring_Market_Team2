package com.springproject.market.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BDaoMyPageSellerInfo;

public class BCommandMyPageSellerPwUpdate implements BCommand { // 2021.07.05 조혜지 - 판매자 비밀번호 변경 command

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String sPw = request.getParameter("sPw");
		System.out.println(sPw);
		
// 		**********수훈님과 연동 시 변경하기***************
//		String sId = Share.userId;
		String sId = "every79";
		
		BDaoMyPageSellerInfo dao = sqlSession.getMapper(BDaoMyPageSellerInfo.class);
		dao.sellerPwUpdateDao(sPw, sId);

	}

}
