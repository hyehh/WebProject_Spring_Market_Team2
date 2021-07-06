package com.springproject.market.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BDaoMyPageSellerInfo;
import com.springproject.market.util.Share;

public class BCommandMyPageSellerSignOut implements BCommand { // 2021.07.05 조혜지 - 판매자 회원 탈퇴 command

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String[] sSignOutReason = request.getParameterValues("sSignOutReason");
		String sSignOutContent =  request.getParameter("sSignOutContent");
		
		String ReasonList = "";
		for(String s : sSignOutReason) {
			ReasonList += " - " + s;
		}
		
// 		**********수훈님과 연동 시 변경하기***************
//		String sId = Share.userId;
		String sId = "every79";
		
		BDaoMyPageSellerInfo dao = sqlSession.getMapper(BDaoMyPageSellerInfo.class);
		dao.sellerSignOutDao(ReasonList, sSignOutContent, sId);

	}

}
