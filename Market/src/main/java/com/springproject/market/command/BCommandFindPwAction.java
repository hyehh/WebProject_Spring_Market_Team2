package com.springproject.market.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BDaoFindPwAction;
import com.springproject.market.dao.IDaoFindPwAction;
import com.springproject.market.dao.IDaoFindIdAction;
import com.springproject.market.util.Share;

public class BCommandFindPwAction implements BCommand {

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		IDaoFindPwAction dao = sqlSession.getMapper(IDaoFindPwAction.class);

		String inputId = request.getParameter("inputId");
		String inputName = request.getParameter("inputName");
		String inputEmail = request.getParameter("inputEmail");
		String findUserType = request.getParameter("findUserType");

		String findmsg = "";
		String findchkResult ="";
		int findchkResultNum = 0 ;
		String returnJsp = "";

		System.out.println(inputId);
		System.out.println(inputName);
		System.out.println(inputEmail);
		System.out.println(findUserType);

try {

			if(findUserType.equals("구매회원")) {
				findUserType = "customer";
//				BDaoFindPwAction dao = new BDaoFindPwAction();
				findchkResult = dao.findcPw(inputId, inputName, inputEmail);
				System.out.println(findchkResult);
				
				if(findchkResult == null) {
					findchkResultNum = 0;
					Share.findPw = "none";
					returnJsp = "failFidnId";
				}else {
					findchkResultNum = 1;
					Share.findPw = findchkResult;
					returnJsp = "successFindId";
				}

				session.setAttribute("findchkResult", findchkResult);
				session.setAttribute("findUserType", findUserType);
				session.setAttribute("findPw", Share.findPw);
				session.setAttribute("RETURNJSP", returnJsp);
				
				//메세지정하기
				if(findchkResultNum == 0 ) {
					findmsg = "패스워드를 찾을 수 없습니다. " 
							+ "\n" + "입력정보를 확인해주세요.";
					session.setAttribute("findmsg", findmsg);
				}else {
					findmsg = "회원님의 패스워드는 " + session.getAttribute("findPw") + " 입니다.";
					session.setAttribute("findmsg", findmsg);
				}

				
				System.out.println("findchkResult =" + findchkResult);
				System.out.println("findpw =" + Share.findPw);
				System.out.println("findmsg =" + session.getAttribute(findmsg));
				System.out.println("RETURNJSP = "  + session.getAttribute("RETURNJSP"));


			}else if(findUserType.equals("판매회원")) {
				System.out.println(1);
				findUserType = "seller";
//				BDaoFindPwAction dao = new BDaoFindPwAction();
				findchkResult = dao.findsPw(inputId, inputName, inputEmail);
				System.out.println(findchkResult);


				
				if(findchkResult == null) {
					findchkResultNum = 0;
					Share.findPw = "none";
					returnJsp = "failFidnId";
				}else {
					findchkResultNum = 1;
					Share.findPw = findchkResult;
					returnJsp = "successFindId";
				}

				
				session.setAttribute("findchkResult", findchkResult);
				session.setAttribute("findPw", Share.findPw);
				session.setAttribute("findUserType", findUserType);
				session.setAttribute("RETURNJSP", returnJsp);

				//메세지정하기
				if(findchkResultNum == 0 ) {
					findmsg = "패스워드를 찾을 수 없습니다. " 
							+ "\n" + "입력정보를 확인해주세요.";
					session.setAttribute("findmsg", findmsg);
				}else {
					findmsg = "회원님의 패스워드는 " + session.getAttribute("findPw") + " 입니다.";
					session.setAttribute("findmsg", findmsg);
				}

				System.out.println("findchkResult =" + findchkResult);
				System.out.println("findpw =" + Share.findPw);
				System.out.println("findmsg =" + session.getAttribute(findmsg));
				System.out.println("RETURNJSP = "  + session.getAttribute("RETURNJSP"));


			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
