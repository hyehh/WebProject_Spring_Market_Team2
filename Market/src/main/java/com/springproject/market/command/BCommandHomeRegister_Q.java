package com.springproject.market.command;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.springproject.market.dao.BDaoHome;
import com.springproject.market.dao.BDaoMyPageReview;
import com.springproject.market.util.Share;

public class BCommandHomeRegister_Q implements BCommand {

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub
		System.out.println("command 시작!!!");
		
		Map<String, Object> map = model.asMap();
		MultipartHttpServletRequest request = (MultipartHttpServletRequest) map.get("multiRequest");
		
		System.out.println("multiRequest");
		
		String pCode = null;
		String cId = null;
		String qTitle = null;
		String qContent = null;
		String qFilePath = null;
		
		String saveFileName = null;
		
		String root_path = session.getServletContext().getRealPath("/");
		System.out.println(root_path);

		String attach_path = "resources/qnasave/";
		System.out.println(attach_path);

		qFilePath = root_path + attach_path;
		System.out.println(qFilePath);

		
		//해당 디렉토리가 없을경우 디렉토리를 생성합니다.
		File folder = new File(qFilePath);
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
			String saveFile = qFilePath + saveFileName;
			
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
		
		pCode = request.getParameter("pCode");
		cId = request.getParameter("cId");
		qTitle = request.getParameter("qTitle");
		qContent = request.getParameter("qContent");
		
		Share.pCode = request.getParameter("pCode");
		
		BDaoHome dao = sqlSession.getMapper(BDaoHome.class);
		dao.registerQ(Integer.parseInt(pCode), cId, qTitle, qContent, saveFileName);

	}

}
