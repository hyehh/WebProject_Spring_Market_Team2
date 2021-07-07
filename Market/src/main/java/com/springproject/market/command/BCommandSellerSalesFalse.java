package com.springproject.market.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.controller.BControllerSeller;
import com.springproject.market.dao.BDaoSellerProduct;
import com.springproject.market.dto.BDtoSellerProduct;

public class BCommandSellerSalesFalse implements BCommand {

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();

		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		
		BDaoSellerProduct dao = new BDaoSellerProduct();
		
		ArrayList<BDtoSellerProduct> dtos1 = null;

		int salesCount = dao.sales();
		int salesTure = dao.salesTrue();
		int salesFalse = dao.salesFalse();

		String strPg = request.getParameter("pg"); // list.jsp?pg=?

		int rowSize = 10; // 한페이지에 보여줄 글의 수
		int pg = 1; // 페이지 , list.jsp로 넘어온 경우 , 초기값 =1

		if (strPg != null) { // list.jsp?pg=2
			pg = Integer.parseInt(strPg); // .저장
		}
		System.out.println("jsp" + strPg);
		int total = dao.salesFalse(); // 총 게시물 수
		int allPage = (int) Math.ceil(total / (double) rowSize); // 페이지수
//		int totalPage = total/rowSize + (total%rowSize==0?0:1);
		int block = 10; // 한페이지에 보여줄 범위 << [1] [2] [3] [4] [5] [6] [7] [8] [9] [10] >>

		// (2 * 10) - ( 10 - 1) = 20 - 9 = 11
		BControllerSeller.from = (pg * rowSize) - (rowSize - 1) - 1; // (1*10)-(10-1)=10-9=1 //from
		BControllerSeller.to = 10; // (1*10) = 10 //to

		/* ArrayList<BDto> list = dao.list(from,to); */

		System.out.println("전체 페이지수 : " + allPage);
		System.out.println("현제 페이지 : " + strPg);
		System.out.println("ceil:" + Math.ceil(total / rowSize));
//						((1-1) / 10 * 10) + 1
		int fromPage = ((pg - 1) / block * block) + 1; // 보여줄 페이지의 시작
		int toPage = ((pg - 1) / block * block) + block; // 보여줄 페이지의 끝
		if (toPage > allPage) { // 예) 20>17
			toPage = allPage;
		}

		System.out.println("페이지시작 : " + fromPage + " / 페이지 끝 :" + toPage);

		ArrayList<Integer> pageCount = new ArrayList<Integer>();

		for (int i = fromPage; i <= toPage; i++) {
			pageCount.add(i);
		}

		dtos1 = dao.salesFalseList(BControllerSeller.from, BControllerSeller.to);

		request.setAttribute("list", dtos1);
		request.setAttribute("SALESCOUNT", salesCount);
		request.setAttribute("SALESTURE", salesTure);
		request.setAttribute("SALESFALSE", salesFalse);

		// 페이징 변수들
		request.setAttribute("PG", pg); // 페이지넘버
		request.setAttribute("BLOCK", block); // 범위
		request.setAttribute("FROMPAGE", fromPage); // DB리스트 개수의 10개만큼 짤라서 보여줄 숫자. (총 21개면 3페이지 의 첫 페이지)
		request.setAttribute("TOPAGE", toPage); // DB리스트 개수의 10개만큼 짤라서 보여줄 숫자. (총 21개면 3페이지 의 끝 페이지)
		request.setAttribute("pageCount", pageCount); // jps forEach로 넘기기 위한 리스트
		request.setAttribute("ALLPAGE", allPage); // 총 페이지 갯수

	}

}
