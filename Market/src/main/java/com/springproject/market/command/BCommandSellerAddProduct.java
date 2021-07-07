package com.springproject.market.command;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import com.springproject.market.dao.IDaoSellerInsert;
import com.springproject.market.util.Share;

public class BCommandSellerAddProduct implements BCommand {

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();

		MultipartHttpServletRequest request = (MultipartHttpServletRequest) map.get("request");
		
		// 수훈님과 합칠때주석 풀기
		String sId = Share.userId;
//		String sId = "every79";
		MultipartRequest multiMain = null;


		String filePath = null;

		String pCategory = null;
		String pName = null;
		String pPrice = null;
		String pPriceDC = null;
		String ExpirationDate = null;
		String pProductEA = null;
		String pStatus = null;

		String year = null;
		String month = null;
		String day = null;
		
		String rFilePath = null;
		String saveFileName = null;
		
		
		String root_path = session.getServletContext().getRealPath("/");
		String attach_path = "resources/productSave/";
		rFilePath = root_path + attach_path;
		
		//해당 디렉토리가 없을경우 디렉토리를 생성합니다.
		File folder = new File(rFilePath);
		if (!folder.exists()) {
			try {
				folder.mkdir(); // 폴더 생성합니다.
				System.out.println("폴더가 생성되었습니다.");
			} catch (Exception e) {
				e.getStackTrace();
			}
		} else {
			System.out.println("이미 폴더가 생성되어 있습니다.");
		}

		// upload File
		MultipartFile mf = request.getFile("uploadFile");
		String originFileName = mf.getOriginalFilename(); // 원본 파일 명
		long fileSize = mf.getSize(); // 파일 사이즈
		
		// file upload check
		if(fileSize != 0) {
			saveFileName = System.currentTimeMillis() + originFileName;
			String saveFile = rFilePath + saveFileName;
			
			try {
				mf.transferTo(new File(saveFile));				
			} catch (IllegalStateException e) {
				// TODO: handle exception
				e.printStackTrace();
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
//		pCategory = multiMain.getParameter("pCategory");
//		pName = multiMain.getParameter("pName");
//		pPrice = multiMain.getParameter("pPrice");
//		pPriceDC = multiMain.getParameter("pPriceDC");
//		pProductEA = multiMain.getParameter("pProductEA");
//		pStatus = "판매중";
		
		pCategory = request.getParameter("pCategory");
		pName = request.getParameter("pName");
		pPrice = request.getParameter("pPrice");
		pPriceDC = request.getParameter("pPriceDC");
		pProductEA = request.getParameter("pProductEA");
		pStatus = "판매중";
		
		
		year = request.getParameter("date1");
		month = request.getParameter("date2");
		day = request.getParameter("date3");

		if (month.length() < 2) {
			month = "0" + month;
		}

		if (day.length() < 2) {
			day = "0" + day;
		}
		
		ExpirationDate = year + month + day;
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(ExpirationDate);
		String pExpirationDate = sdf.format(cal.getTime());
		
		IDaoSellerInsert dao = sqlSession.getMapper(IDaoSellerInsert.class);
		dao.insert(pCategory, pName, pPrice, pPriceDC, pExpirationDate, pProductEA, pStatus, saveFileName);
		int maxPcode = dao.getCode();
		System.out.println(maxPcode);
		dao.insertRegister(maxPcode, sId);
	}

}
