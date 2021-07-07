package com.springproject.market.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springproject.market.dao.BDaoHome;
import com.springproject.market.dto.BDtoProduct;
import com.springproject.market.dto.BDtoQnA;
import com.springproject.market.dto.BDtoReview;
import com.springproject.market.util.Share;

public class BCommandHomeProductQ implements BCommand {

	@Override
	public void execute(HttpSession session, Model model, SqlSession sqlSession) {
		// TODO Auto-generated method stub
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String pCode = Share.pCode;
		
		
		BDaoHome dao = sqlSession.getMapper(BDaoHome.class);
		
		model.addAttribute("product_view", dao.productView(pCode));
		
//		ArrayList<BDtoReview> dtoReview = dao.reviewList(pCode);
//		request.setAttribute("review_list", dtoReview);
		
		String review_strPg = request.getParameter("review_pg"); //list.jsp?pg=?
		int review_rowSize = 3; //한페이지에 보여줄 글의 수
		int review_pg = 1; //페이지 , list.jsp로 넘어온 경우 , 초기값 =1
		
		if(review_strPg != null){ //list.jsp?pg=2
			review_pg = Integer.parseInt(review_strPg); //.저장
		}
		
		int review_total = dao.reivewListCount(pCode); //총 게시물 수
		// 여기서 total은 dao에서 뽑아낼 리스트의 개수가 몇개인지 count 확인해야해요
		int review_allPage = (int) Math.ceil(review_total/(double)review_rowSize); //페이지수
		// int totalPage = total/rowSize + (total%rowSize==0?0:1);
		int review_block = 10; //한페이지에 보여줄  범위 << [1] [2] [3] [4] [5] [6] [7] [8] [9] [10] >>
		int review_from = (review_pg * review_rowSize) - (review_rowSize-1) - 1; //(1*10)-(10-1)=10-9=1 //from
		int review_to = 3; // 10개씩 자름 // 만일 1~5개씩 보이고싶다면 to 변수에 5라고 적으면 됨.
		int review_fromPage = ((review_pg-1)/review_block * review_block)+1;		//보여줄 페이지의 시작
		int review_toPage = ((review_pg-1)/review_block * review_block) + review_block;	//보여줄 페이지의 끝
		if(review_toPage> review_allPage){ // 예) 20>17
			review_toPage = review_allPage;
		}
		ArrayList<Integer> review_pageCount = new ArrayList<Integer>();	// DB에 있는 내용 10단위씩 짤라서 나온 수량 리스트에 저장.
		for (int i = review_fromPage; i <= review_toPage; i++) {
			review_pageCount.add(i);
		}
		
		model.addAttribute("review_PG", review_pg);
		model.addAttribute("review_BLOCK", review_block);
		model.addAttribute("review_FROMPAGE", review_fromPage);
		model.addAttribute("review_TOPAGE", review_toPage);
		model.addAttribute("review_pageCount", review_pageCount);
		model.addAttribute("review_ALLPAGE", review_allPage);
		model.addAttribute("review_list", dao.reviewList(pCode, review_from, review_to));
		
		
		
		
		
		
		
		model.addAttribute("review_info", dao.reviewStar(pCode));
		
//		ArrayList<BDtoQnA> dtoQnA = dao.qnaList(pCode);
//		request.setAttribute("QnA_list", dtoQnA);
		
		String qna_strPg = request.getParameter("qna_pg"); //list.jsp?pg=?
		int qna_rowSize = 5; //한페이지에 보여줄 글의 수
		int qna_pg = 1; //페이지 , list.jsp로 넘어온 경우 , 초기값 =1
		
		if(qna_strPg != null){ //list.jsp?pg=2
			qna_pg = Integer.parseInt(qna_strPg); //.저장
		}
		
		int qna_total = dao.qnaListCount(pCode); //총 게시물 수
		// 여기서 total은 dao에서 뽑아낼 리스트의 개수가 몇개인지 count 확인해야해요
		int qna_allPage = (int) Math.ceil(qna_total/(double)qna_rowSize); //페이지수
		// int totalPage = total/rowSize + (total%rowSize==0?0:1);
		int qna_block = 10; //한페이지에 보여줄  범위 << [1] [2] [3] [4] [5] [6] [7] [8] [9] [10] >>
		int qna_from = (qna_pg * qna_rowSize) - (qna_rowSize-1) - 1; //(1*10)-(10-1)=10-9=1 //from
		int qna_to = 5; // 10개씩 자름 // 만일 1~5개씩 보이고싶다면 to 변수에 5라고 적으면 됨.
		int qna_fromPage = ((qna_pg-1)/qna_block * qna_block) + 1;		//보여줄 페이지의 시작
		int qna_toPage = ((qna_pg-1)/qna_block * qna_block) + qna_block;	//보여줄 페이지의 끝
		if(qna_toPage> qna_allPage){ // 예) 20>17
			qna_toPage = qna_allPage;
		}
		ArrayList<Integer> qna_pageCount = new ArrayList<Integer>();	// DB에 있는 내용 10단위씩 짤라서 나온 수량 리스트에 저장.
		for (int i = qna_fromPage; i <= qna_toPage; i++) {
			qna_pageCount.add(i);
		}
		
		model.addAttribute("QnA_PG", qna_pg);
		model.addAttribute("QnA_BLOCK", qna_block);
		model.addAttribute("QnA_FROMPAGE", qna_fromPage);
		model.addAttribute("QnA_TOPAGE", qna_toPage);
		model.addAttribute("QnA_pageCount", qna_pageCount);
		model.addAttribute("QnA_ALLPAGE", qna_allPage);
		model.addAttribute("QnA_list", dao.qnaList(pCode, qna_from, qna_to));
		
//		수훈님 로그인 끝나면 이걸로 열기
//		model.addAttribute("QnA_login", Share.userId);
		
//		로그인 여부에 따라 페이지 이동
		model.addAttribute("QnA_login", "jenny78");
//		model.addAttribute("QnA_login", null);

	}

}
