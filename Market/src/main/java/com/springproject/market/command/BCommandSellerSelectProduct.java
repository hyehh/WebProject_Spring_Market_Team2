package com.springproject.market.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BDaoSellerProduct;
import com.springproject.market.dto.BDtoSellerProduct;
import com.springproject.market.util.Share;

public class BCommandSellerSelectProduct implements BCommand {
	Share cv = new Share();
	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();

		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String pCode = request.getParameter("pCode");
		System.out.println("커맨드 /" + pCode);
		BDaoSellerProduct dao = new BDaoSellerProduct();
		BDtoSellerProduct dto = dao.selectProduct(pCode);
		ArrayList<String> category = new ArrayList<String>();
		category.add("주방용품");
		category.add("세탁용품");
		category.add("청소용품");
		category.add("인테리어소품");
		category.add("통조림");
		category.add("냉동식품");
		category.add("식음료");

		// 파일이 업로드되있는 상태라면 (이름+경로) - 경로를 해서 파일 이름만 추출해서 request로 전송
		if (dto.getpFilePath() != null) {
			String filePath = dto.getpFilePath();
			String fileName = filePath.substring(cv.Product.length() + 1);
			model.addAttribute("fileName", fileName);
		}

		model.addAttribute("selectProduct", dto);
		model.addAttribute("Category", category);
		

	}

}
