package com.springproject.market.command;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BDaoMyPageBuy;
import com.springproject.market.dto.BDtoMyPageBuy;

public class BCommandMyPagePCode implements BCommand { // 2021.07.06 조혜지 - 장바구니에서 구매한 제품의 pCode 구하는 command

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub
		
		String bNumber = (String)session.getAttribute("bNumber");
		
		BDaoMyPageBuy dao = sqlSession.getMapper(BDaoMyPageBuy.class);
		
		ArrayList<BDtoMyPageBuy> dtos = dao.pCodeListDao(bNumber);
		
		session.setAttribute("pCode", dtos);
		
		int psize = dtos.size();
		session.setAttribute("psize", psize);
	}

}
