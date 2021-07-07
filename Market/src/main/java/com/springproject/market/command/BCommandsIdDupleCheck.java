package com.springproject.market.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BDaosIdCheck;
import com.springproject.market.dao.IDaosIdCheck;

public class BCommandsIdDupleCheck implements BCommand {

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String sId = request.getParameter("sId");
		String sIdchkResult = "" ;
		int sIdchkNum = 0;
		String hidden = request.getParameter("idDuplication");

		System.out.println(sId);
//		BDaosIdCheck dao = new BDaosIdCheck();
		IDaosIdCheck dao = sqlSession.getMapper(IDaosIdCheck.class);
		sIdchkResult = dao.IdcheckSeller(sId);
		
		System.out.println("sIdchkResult = " + sIdchkResult);
		if(sIdchkResult == null) {
			sIdchkNum=0;
		}else {
			sIdchkNum= 1;
		}
		System.out.println("sIdchkNum = " + sIdchkNum);

		
		session.setAttribute("sIdchk", sIdchkNum);
		session.setAttribute("sId", sId);
		session.setAttribute("sHIDDEN", hidden);


		String sIdchkMessage;
		if(sIdchkNum == 1) {
			sIdchkMessage = "이미 사용중인 아이디 입니다. 다른 아이디를 입력해주세요";
			session.setAttribute("sIdchkMessage", sIdchkMessage);
			}else {
			sIdchkMessage = "아이디를 사용할 수 있습니다. 계속해서 진행해 주세요.";
			session.setAttribute("sIdchkMessage", sIdchkMessage);
		}
		System.out.println(session.getAttribute("sIdchkMessage"));
		System.out.println(session.getAttribute("sIdchk"));
	
	
	
	}

}
