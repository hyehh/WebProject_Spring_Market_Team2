package com.springproject.market.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BDaoLoginAction;
import com.springproject.market.dao.IDaoFindPwAction;
import com.springproject.market.dao.IDaoLoginAction;
import com.springproject.market.util.Share;

public class BCommandLoginAction implements BCommand {

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		IDaoLoginAction dao = sqlSession.getMapper(IDaoLoginAction.class);

		String loginId = request.getParameter("loginId");
		String loginPw = request.getParameter("loginPw");
		String userType = request.getParameter("userType");
		
		//변수선언
		int loginChkNum = 0;	//DB에서 로그인 결과 체크시 사용할 변수 선언
		String returnJsp = "";
		
		//쿼리실행 후 id/pw
		String userId= "";
		String userPw= "";
		
		System.out.println(loginId);
		System.out.println(loginPw);
		System.out.println(userType);
		

		try {
			if(userType.equals("구매회원")) {
				userType = "customer";
				Share.UserType = userType;
				System.out.println("test");
//				BDaoLoginAction dao = new BDaoLoginAction();
				userId = dao.customerId(loginId, loginPw);
				userPw = dao.customerPw(loginId, loginPw);
				System.out.println("test123123123123");
				
				
				if(userId == null || userPw == null) {
					loginChkNum = 0;
					Share.loginResultNum = loginChkNum;
					Share.userId = "none";
					Share.userPw = "none";
					returnJsp = "loginFailMain";
					System.out.println("test22222"); 	
				}else {
					loginChkNum = 1;
					userId = dao.customerId(loginId, loginPw);
					userPw = dao.customerPw(loginId, loginPw);
					Share.loginResultNum = loginChkNum;
					Share.userId = userId;
					Share.userPw = userPw;
					returnJsp = "main";
					System.out.println("test3333333");
				}
				System.out.println("test4444");
//				session.setAttribute("loginChkResult", loginChkResult); // 1 = 해당 아이디 존재 ,0 = 존재하지 않음
				session.setAttribute("USERTYPE", userType);
				session.setAttribute("RETURNJSP", returnJsp);
				System.out.println("test555555");
				

//				System.out.println(userId);
				System.out.println(Share.userId + "\n" + Share.userPw);


			}else if(userType.equals("판매회원")) {
				userType = "seller";
				Share.UserType = userType;
				userId = dao.sellerId(loginId, loginPw);
				userPw = dao.sellerPw(loginId, loginPw);
//				BDaoLoginAction dao = new BDaoLoginAction();
				
				if( userId == null || userPw == null) {
					loginChkNum = 0;
					Share.loginResultNum = loginChkNum;
					Share.userId = "none";
					Share.userPw = "none";
					returnJsp = "loginFailMain";
				} else {
					loginChkNum = 1;
					userId = dao.sellerId(loginId, loginPw);
					userPw = dao.sellerPw(loginId, loginPw);
					Share.loginResultNum = loginChkNum;
					Share.userId = userId;
					Share.userPw = userPw;
					returnJsp = "sellerStore";
				}
				System.out.println(userId);
				System.out.println(Share.userId + "\n" + Share.userPw);

//				session.setAttribute("loginChkResult", loginChkResult);
				session.setAttribute("USERTYPE", userType);
				session.setAttribute("RETURNJSP", returnJsp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(returnJsp);
	}

}
