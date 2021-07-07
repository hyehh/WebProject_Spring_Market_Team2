package com.springproject.market.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BDaoFindIdAction;
import com.springproject.market.dao.IDaoFindIdAction;
import com.springproject.market.util.Share;

public class BCommandFindIdAction implements BCommand {

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		IDaoFindIdAction dao = sqlSession.getMapper(IDaoFindIdAction.class);
		//값받아오기
				String findName = request.getParameter("findName");
				String findEmail = request.getParameter("findEmail");
				String findUserType = request.getParameter("findUserType");

				//찾은아이디 넣을 변수
				String findmsg = "";
				String findchkResult = "";
				int findchkResultNum = 0;
				
				//리턴할 jsp명
				String returnJsp ="";

				System.out.println(findName);
				System.out.println(findEmail);
				System.out.println(findUserType);

				 
				try {

					if(findUserType.equals("구매회원")) {
						findUserType = "customer";
//						BDaoFindIdAction dao = new BDaoFindIdAction();
						findchkResult = dao.findcId(findName, findEmail);
						
						
						if(findchkResult == null) {
							findchkResultNum = 0;
							Share.findId = "none";
							returnJsp = "failFidnId";
						}else {
							findchkResultNum = 1;
							Share.findId = findchkResult;
							returnJsp = "successFindId";
						}

						session.setAttribute("findchkResultNum", findchkResultNum);
						session.setAttribute("findUserType", findUserType);
						session.setAttribute("findId", Share.findId);
						session.setAttribute("RETURNJSP", returnJsp);

						//메세지정하기
						if(findchkResultNum == 0 ) {
							findmsg = "아이디를 찾을 수 없습니다.";
							session.setAttribute("findmsg", findmsg);
						}else {
							findmsg = "회원님의 아이디는 " + session.getAttribute("findId") + " 입니다.";
							session.setAttribute("findmsg", findmsg);
						}

						System.out.println("findchkResultNum =" + findchkResultNum);
						System.out.println("findId =" + Share.findId);
						System.out.println("findmsg =" + findmsg);
						System.out.println("findmsg =" + session.getAttribute("findmsg"));
						System.out.println("RETURNJSP = "  + session.getAttribute("RETURNJSP"));


					}else if(findUserType.equals("판매회원")) {
						System.out.println(1);
						findUserType = "seller";
//						BDaoFindIdAction dao = new BDaoFindIdAction();
						findchkResult = dao.findsId(findName, findEmail);
						
						System.out.println(findchkResult);

						if(findchkResult == null) {
							findchkResultNum = 0;
							Share.findId = "none";
							returnJsp = "failFidnId";
						}else {
							findchkResultNum = 1;
							System.out.println("test3333");
							Share.findId = findchkResult;
							returnJsp = "successFindId";
						}

						session.setAttribute("findchkResultNum", findchkResultNum);
						session.setAttribute("findId", Share.findId);
						session.setAttribute("findUserType", findUserType);
						session.setAttribute("RETURNJSP", returnJsp);
						
						//메세지정하기
						if(findchkResultNum == 0 ) {
							findmsg = "아이디를 찾을 수 없습니다.";
							session.setAttribute("findmsg", findmsg);
						}else {
							findmsg = "회원님의 아이디는 " + session.getAttribute("findId") + " 입니다.";
							session.setAttribute("findmsg", findmsg);
						}
						
						System.out.println("findchkResultNum =" + findchkResultNum);
						System.out.println("findId =" + Share.findId);
						System.out.println("findmsg =" + findmsg);
						System.out.println("findmsg =" + session.getAttribute("findmsg"));
						System.out.println("RETURNJSP = "  + session.getAttribute("RETURNJSP"));


					
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
	
				System.out.println("test 11111111");
				
	}
}

