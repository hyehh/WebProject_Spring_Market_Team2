package com.springproject.market.command;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BBuyDao;

public class BCommandMyPageCancel implements BCommand { // 2021.07.06 조혜지 - 구매 취소 버튼 클릭 시 취소일자 업데이트하는 command

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub
		String bNumber = (String)session.getAttribute("bNumber");
		System.out.println(bNumber);
		BBuyDao dao = new BBuyDao();
		dao.cancelDay(bNumber);
	}

}
