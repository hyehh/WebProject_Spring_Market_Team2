package com.springproject.market.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BDaosignupCustomer;
import com.springproject.market.dao.IDaosignupCustomer;

public class BCommandSignupCustomer implements BCommand {

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String cId = request.getParameter("cId");
		String cPw = request.getParameter("cPw");
		String cName = request.getParameter("cName");
		String cTel = request.getParameter("cTel");
		
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
		String cEmail = (request.getParameter("cEmail"));
		String cPostalCode = (request.getParameter("cPostalCode"));
		String cAddress1 = request.getParameter("cAddress1");
		String cAddress2 = request.getParameter("cAddress2");
		
//		BDaosignupCustomer dao = new BDaosignupCustomer();
		IDaosignupCustomer dao = sqlSession.getMapper(IDaosignupCustomer.class);
		dao.writeCustomer(cId, cPw, cName, cBirth, cTel, cAddress1, cAddress2, cEmail, cPostalCode);
	}


	}


