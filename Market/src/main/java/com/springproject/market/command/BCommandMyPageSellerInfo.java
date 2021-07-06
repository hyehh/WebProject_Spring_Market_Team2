package com.springproject.market.command;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BDaoMyPageSellerInfo;
import com.springproject.market.util.Share;

public class BCommandMyPageSellerInfo implements BCommand { // 2021.07.05 조혜지 - 판매자 정보 수정 시 db에 있는 정보 불러오는 command

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub

// 		**********수훈님과 연동 시 변경하기***************
//		String sId = Share.userId;
		String sId = "every79";
		
		BDaoMyPageSellerInfo dao = sqlSession.getMapper(BDaoMyPageSellerInfo.class);
		model.addAttribute("SELLERINFO", dao.sellerinfoDao(sId));
		
	}

}
