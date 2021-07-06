package com.springproject.market.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BDaoMyPageCustomerInfo;

public class BCommandMyPageCustomerInfoUpdate implements BCommand { // 2021.07.05 조혜지 - 고객 회원 정보 수정 버튼 클릭 시 정보 업데이트 command

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String cName = request.getParameter("cName");
		String cEmail = request.getParameter("cEmail");
		String cBirthY = request.getParameter("cBirthY");
		String cBirthM = request.getParameter("cBirthM");
		String cBirthD = request.getParameter("cBirthD");
		if(Integer.parseInt(request.getParameter("cBirthM"))<10) {
			cBirthM = "0" + cBirthM;
		}
		if(Integer.parseInt(request.getParameter("cBirthD"))<10) {
			cBirthD = "0" + cBirthD;
		}
		String cBirth = cBirthY + "-" + cBirthM + "-" + cBirthD;
		String cTel = request.getParameter("cTel");
		String cPostalCode = request.getParameter("cPostalCode");
		String cAddress1 = request.getParameter("cAddress1");
		String cAddress2 = request.getParameter("cAddress2");			

// 		**********수훈님과 연동 시 변경하기***************
//		String cId = Share.userId;	
		String cId = "jenny78";	
		
		BDaoMyPageCustomerInfo dao = sqlSession.getMapper(BDaoMyPageCustomerInfo.class);
		dao.customerInfoUpdateDao(cName, cEmail, cBirth, cTel, cPostalCode, cAddress1, cAddress2, cId);

	}

}
