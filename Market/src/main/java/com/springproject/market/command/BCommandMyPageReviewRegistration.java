package com.springproject.market.command;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.springproject.market.dao.BDaoMyPageReview;

public class BCommandMyPageReviewRegistration implements BCommand { // 2021.07.06 조혜지 - 리뷰 등록하는 command

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub
		
		Map<String, Object> map = model.asMap();
		MultipartHttpServletRequest request = (MultipartHttpServletRequest) map.get("request");
		
		String bNumber = null;
		String pCode = null;
		String rFilePath = null;
		String saveFileName = null;
		
		String root_path = session.getServletContext().getRealPath("/");
		System.out.println(root_path);
		String attach_path = "resources/reviewsave/";
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
		
		String bReviewScore = request.getParameter("bReviewScore");
		String bReviewContent = request.getParameter("bReviewContent");
		bNumber = (String)session.getAttribute("bNumber");
		pCode = (String)session.getAttribute("pCode");
		
		BDaoMyPageReview dao = sqlSession.getMapper(BDaoMyPageReview.class);
		dao.reviewRegistrationDao(saveFileName, Integer.parseInt(bReviewScore), bReviewContent, bNumber, Integer.parseInt(pCode));
		
	}

}
