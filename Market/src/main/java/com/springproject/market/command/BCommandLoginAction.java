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
		String returnJsp ="";
		
		System.out.println(userType);

		try {
			if(userType.equals("구매회원")) {
				userType = "customer";
				Share.UserType = userType;
//				BDaoLoginAction dao = new BDaoLoginAction();
				String userId = dao.customerLoginAction(loginId, loginPw).getUserId();
				String userPw = dao.customerLoginAction(loginId, loginPw).getUserPw();
				
				if( userId == null) {
					loginChkNum = 0;
					Share.loginResultNum = loginChkNum;
					Share.userId = "none";
					Share.userPw = "none";
					returnJsp = "loginFailMain";
				}else {
					loginChkNum = 1;
					Share.loginResultNum = loginChkNum;
					Share.userId = userId;
					Share.userPw = userPw;
					returnJsp = "main";
				}
				
//				session.setAttribute("loginChkResult", loginChkResult); // 1 = 해당 아이디 존재 ,0 = 존재하지 않음
				session.setAttribute("USERTYPE", userType);
				session.setAttribute("RETURNJSP", returnJsp);
				

				System.out.println(userId);
				System.out.println(Share.userId + "\n" + Share.userPw);


			}else if(userType.equals("판매회원")) {
				userType = "seller";
				Share.UserType = userType;
//				BDaoLoginAction dao = new BDaoLoginAction();
				String userId = dao.sellerLoginAction(loginId, loginPw).getUserId();
				String userPw = dao.sellerLoginAction(loginId, loginPw).getUserPw();
				
				if( userId == null) {
					loginChkNum = 0;
					Share.loginResultNum = loginChkNum;
					Share.userId = "none";
					Share.userPw = "none";
					returnJsp = "loginFailMain";
				}else {
					loginChkNum = 1;
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
	}

}
