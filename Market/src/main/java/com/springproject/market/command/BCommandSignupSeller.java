package com.springproject.market.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BDaosignupSeller;
import com.springproject.market.dao.IDaosignupSeller;

public class BCommandSignupSeller implements BCommand {

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String sId = request.getParameter("sId");
		String sPw = request.getParameter("sPw");
		String sName = request.getParameter("sName");
		String sTel = request.getParameter("sTel");

		String sBirthY = request.getParameter("sBirthY");
		String sBirthM = request.getParameter("sBirthM");
		String sBirthD = request.getParameter("sBirthD");
		if(Integer.parseInt(request.getParameter("sBirthM"))<10) {
			sBirthM = "0" + sBirthM;
		}
		if(Integer.parseInt(request.getParameter("sBirthD"))<10) {
			sBirthD = "0" + sBirthD;
		}

		String sBirth = sBirthY + "-" + "sBirthM" + "-" + sBirthD;
		String sEmail = (request.getParameter("sEmail"));
		String sPostalCode = (request.getParameter("sPostalCode"));
		String sAddress1 = (request.getParameter("sAddress1"));
		String sAddress2 = (request.getParameter("sAddress2"));
		String sShopName = (request.getParameter("sShopName"));
		String sNumber = (request.getParameter("sNumber"));

//		BDaosignupSeller dao = new BDaosignupSeller();
		IDaosignupSeller dao = sqlSession.getMapper(IDaosignupSeller.class);
		dao.writeSeller(sId, sPw, sName, sBirth, sTel, sAddress1, sAddress2, sEmail, sShopName, sNumber, sPostalCode);
	}

}
