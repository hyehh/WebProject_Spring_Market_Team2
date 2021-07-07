package com.springproject.market.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BDaocIdCheck;
import com.springproject.market.dao.IDaocIdCheck;

public class BCommandcIdDupleCheck implements BCommand {

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String cId = request.getParameter("cId");
		// DB에서 확인한 결과
		String cIdchkResult = "" ;
		int cIdchkNum = 0;
		String hidden = request.getParameter("idDuplication");

		System.out.println(cId);
		IDaocIdCheck dao = sqlSession.getMapper(IDaocIdCheck.class);
		cIdchkResult = dao.IdcheckCustomer(cId);
		System.out.println("cIdchkResult = " + cIdchkResult);
		if(cIdchkResult == null) {
			cIdchkNum=0;
		}else {
			cIdchkNum= 1;
		}
		System.out.println("cIdchkNum = " + cIdchkNum);
//		BDaocIdCheck dao = new BDaocIdCheck();

//		int cIdchkResult = dao.IdcheckCustomer(cId, cIdchk);
		session.setAttribute("cIdchk", cIdchkNum);
		session.setAttribute("cId", cId);
		session.setAttribute("cHIDDEN", hidden);

		String cIdchkMessage;
		if(cIdchkNum == 1) {
			 cIdchkMessage = "이미 사용중인 아이디 입니다. 다른 아이디를 입력해주세요";
			 session.setAttribute("cIdchkMessage", cIdchkMessage);
		}else {
			 cIdchkMessage = "아이디를 사용할 수 있습니다. 계속해서 진행해 주세요.";
			 session.setAttribute("cIdchkMessage", cIdchkMessage);
		}
		System.out.println(session.getAttribute("cIdchkMessage"));
		System.out.println(session.getAttribute("cIdchk"));
	}

}
