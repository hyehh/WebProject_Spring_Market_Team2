package com.springproject.market.util;

import java.util.ArrayList;

public class Pageing {
	public int pg;
	public int block;
	public int fromPage;
	public int toPage;
	public ArrayList<Integer> pageCount = new ArrayList<Integer>();
	public int allPage;
	public int from = 0;
	public int to = 0;
	
	
	public void getFrom(int count, String page) {
		String strPg = page; // list.jsp?pg=?

		int rowSize = 10; // 한페이지에 보여줄 글의 수
		pg = 1; // 페이지 , list.jsp로 넘어온 경우 , 초기값 =1

		if (strPg != null) { // list.jsp?pg=2
			pg = Integer.parseInt(strPg); // .저장
		}
		int total = count; // 총 게시물 수
		allPage = (int) Math.ceil(total / (double) rowSize); // 페이지수
//		int totalPage = total/rowSize + (total%rowSize==0?0:1);
		block = 10; // 한페이지에 보여줄 범위 << [1] [2] [3] [4] [5] [6] [7] [8] [9] [10] >>

		// (2 * 10) - ( 10 - 1) = 20 - 9 = 11
		from = (pg * rowSize) - (rowSize - 1) - 1; // (1*10)-(10-1)=10-9=1 //from
		to = 10; // (1*10) = 10 //to

//						((1-1) / 10 * 10) + 1
		fromPage = ((pg - 1) / block * block) + 1; // 보여줄 페이지의 시작
		int toPage = ((pg - 1) / block * block) + block; // 보여줄 페이지의 끝
		if (toPage > allPage) { // 예) 20>17
			toPage = allPage;
		}

		System.out.println("페이지시작 : " + fromPage + " / 페이지 끝 :" + toPage);
		System.out.println("Pageing.from :" + from);

//		ArrayList<Integer> pageCount = new ArrayList<Integer>();

		for (int i = fromPage; i <= toPage; i++) {
			pageCount.add(i);
		}
	}
}
