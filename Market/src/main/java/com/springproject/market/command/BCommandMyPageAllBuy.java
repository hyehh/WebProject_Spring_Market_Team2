package com.springproject.market.command;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BBuyDao;

public class BCommandMyPageAllBuy implements BCommand { // 2021.07.06 조혜지 - 전체 상품 주문 선택 시 주문서 작성/결제 창에서 주문 및 결제 정보 insert하는 command

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub
		
		// 주문번호 만들기
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		String ym = year + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		String ymd = ym + new DecimalFormat("00").format(cal.get(Calendar.DATE));

		String subNum = "";
		Random rand = new Random();
		for(int i=0; i<6; i++) {
			String ran = Integer.toString(rand.nextInt(10));
		if(!subNum.contains(ran)) {
			subNum += ran;
			}else {
				i-=1;
			}
		}
		int size = (Integer)session.getAttribute("asize");
		System.out.println(size);
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String bNumber = ymd + "-"  + subNum;
		String bRecName = request.getParameter("bRecName");
		String bRecPostalCode = request.getParameter("bRecPostalCode");
		String bRecAddress1 = request.getParameter("bRecAddress1");
		String bRecAddress2 = request.getParameter("bRecAddress2");
		String bRecTel = request.getParameter("bRecTel");
		String bRecContent = request.getParameter("bRecContent");
		
// 		**********수훈님과 연동 시 변경하기***************
//		String cId = Share.userId;	
		String cId = "jenny78";	

		System.out.println(bNumber);
		System.out.println(bRecName);
		System.out.println(bRecPostalCode);
		System.out.println(bRecAddress1);
		System.out.println(bRecAddress2);
		System.out.println(bRecTel);
		System.out.println(bRecContent);
		
		BBuyDao dao = new BBuyDao();		
		dao.allInsert(cId, bNumber, bRecName, bRecPostalCode, bRecAddress1, bRecAddress2, bRecTel, bRecContent, session);
		
	}

}