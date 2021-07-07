package com.springproject.market.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BDaoSellerProduct;

public class BCommandSellerProductModify implements BCommand {

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();

		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		BDaoSellerProduct dao = new BDaoSellerProduct();
		String pCode = request.getParameter("pCode");
		String pCategory = request.getParameter("pCategory");
		String pName = request.getParameter("pName");
		String pPriceDC = request.getParameter("pPriceDC");
		String pProductEA = request.getParameter("pProductEA");
		String pStatus = request.getParameter("pStatus");
		
		System.out.println("수정ㅋ인데 왜 안될까? 코드" + pCode + "/카테고리" + pCategory + "/이름" + pName + "/할인가격" + pPriceDC + "/수량" + pProductEA + "/여부" + pStatus);
		dao.productModify(pCode, pCategory, pName, pPriceDC, pProductEA, pStatus);
	}

}
