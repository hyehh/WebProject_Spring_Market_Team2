package com.springproject.market.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BDaoMyPageSellerInfo;
import com.springproject.market.util.Share;

public class BCommandMyPageSellerInfoUpdate implements BCommand { // 2021.07.05 조혜지 - 판매자 정보 수정 버튼 클릭 시 정보 업데이트 command

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String sName = request.getParameter("sName");
		String sEmail = request.getParameter("sEmail");
		String sBirthY = request.getParameter("sBirthY");
		String sBirthM = request.getParameter("sBirthM");
		String sBirthD = request.getParameter("sBirthD");
		if(Integer.parseInt(request.getParameter("sBirthM"))<10) {
			sBirthM = "0" + sBirthM;
		}
		if(Integer.parseInt(request.getParameter("sBirthD"))<10) {
			sBirthD = "0" + sBirthD;
		}
		String sBirth = sBirthY + "-" + sBirthM + "-" + sBirthD;
		String sTel = request.getParameter("sTel");
		String sPostalCode = request.getParameter("sPostalCode");
		String sAddress1 = request.getParameter("sAddress1");
		String sAddress2 = request.getParameter("sAddress2");		
		String sShopName = request.getParameter("sShopName");		
		String sNumber = request.getParameter("sNumber");		
		
// 		**********수훈님과 연동 시 변경하기***************
//		String sId = Share.userId;
		String sId = "every79";
		
		BDaoMyPageSellerInfo dao = sqlSession.getMapper(BDaoMyPageSellerInfo.class);
		dao.sellerInfoUpdateDao(sName, sEmail, sBirth, sTel, sPostalCode, sAddress1, sAddress2, sShopName, sNumber, sId);

	}

}
