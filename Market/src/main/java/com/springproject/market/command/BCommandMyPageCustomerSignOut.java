package com.springproject.market.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BDaoMyPageCustomerInfo;

public class BCommandMyPageCustomerSignOut implements BCommand { // 2021.07.05 조혜지 - 고객 회원 탈퇴 command

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String[] cSignOutReason = request.getParameterValues("cSignOutReason");
		String cSignOutContent =  request.getParameter("cSignOutContent");

		String ReasonList = "";
		for(String s : cSignOutReason) {
			ReasonList += " - " + s;
		}
		
// 		**********수훈님과 연동 시 변경하기***************
//		String cId = Share.userId;	
		String cId = "jenny78";	
		
		BDaoMyPageCustomerInfo dao = sqlSession.getMapper(BDaoMyPageCustomerInfo.class);
		dao.customerSignOutDao(ReasonList, cSignOutContent, cId);

	}

}
