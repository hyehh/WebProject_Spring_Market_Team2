package com.springproject.market.command;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BDaoMyPageCustomerInfo;
import com.springproject.market.dto.BDtoMyPageCustomerInfo;
import com.springproject.market.util.Share;

public class BCommandMyPageCustomerInfo implements BCommand { // 2021.07.05 조혜지 - 고객 회원 정보 수정 시 db에 있는 정보 불러오는 command

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub

		String cId = Share.userId;	
		
		BDaoMyPageCustomerInfo dao = sqlSession.getMapper(BDaoMyPageCustomerInfo.class);
		model.addAttribute("CUSTOMERINFO", dao.customerinfoDao(cId));
	}

}
