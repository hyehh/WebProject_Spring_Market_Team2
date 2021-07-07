package com.springproject.market.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BDaoSellerProduct;

public class BCommandSellerProductDelete implements BCommand {

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();

		HttpServletRequest request = (HttpServletRequest) map.get("request");

		BDaoSellerProduct dao = new BDaoSellerProduct();
		
		String pCode = request.getParameter("pCode");
		String pDelete = "1";
		
		dao.deleteList(pCode, pDelete);
		dao.deleteRegister(pCode);
	}

}
