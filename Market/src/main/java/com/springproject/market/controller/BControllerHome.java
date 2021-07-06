package com.springproject.market.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springproject.market.command.BCommand;
import com.springproject.market.command.BCommandHomeProduct;
import com.springproject.market.command.BCommandHomeProductQ;
import com.springproject.market.dao.BDaoHome;
import com.springproject.market.dao.BDaoHomeBest;
import com.springproject.market.dao.BDaoHomeCategory;
import com.springproject.market.dao.BDaoHomeMain;
import com.springproject.market.util.Share;

@Controller
public class BControllerHome {
	
	@Autowired
	private SqlSession sqlSession;
	
	BCommand command = null;

	@RequestMapping("/main")
	public String main(Model model, HttpSession session) {
		System.out.println("main()");
		
		BDaoHomeMain dao = sqlSession.getMapper(BDaoHomeMain.class);
		model.addAttribute("main_best", dao.mainBest());
		model.addAttribute("main_new", dao.mainNew());
		model.addAttribute("main_rand", dao.mainRand());
		
		return "main";
	}
	
	@RequestMapping("/best")
	public String best(Model model, HttpSession session) {
		System.out.println("best()");
		
		BDaoHomeBest dao = sqlSession.getMapper(BDaoHomeBest.class);
		model.addAttribute("best_kitchen", dao.kitchen());
		model.addAttribute("best_washing", dao.washing());
		model.addAttribute("best_cleaning", dao.cleaning());
		model.addAttribute("best_interior", dao.interior());
		model.addAttribute("best_can", dao.can());
		model.addAttribute("best_frozen", dao.frozen());
		model.addAttribute("best_beverage", dao.beverage());
		
		return "best";
	}
	
	@RequestMapping("/new")
	public String newlist(Model model, HttpSession session) {
		System.out.println("new()");
		
		BDaoHome dao = sqlSession.getMapper(BDaoHome.class);
		model.addAttribute("new_list", dao.newList());
		
		return "new";
	}
	
	@RequestMapping("/deadline")
	public String deadline(Model model, HttpSession session) {
		System.out.println("deadline()");
		
		BDaoHome dao = sqlSession.getMapper(BDaoHome.class);
		model.addAttribute("deadline_list", dao.deadlineList());
		
		return "deadline";
	}
	
	@RequestMapping("/category")
	public String category(HttpServletRequest request, Model model, HttpSession session) {
		System.out.println("category()");
		
		BDaoHomeCategory dao = sqlSession.getMapper(BDaoHomeCategory.class);
		model.addAttribute("category_list", dao.categoryList(request.getParameter("pCategory")));
		
		return "category";
	}
	
	@RequestMapping("/search")
	public String search(HttpServletRequest request, Model model, HttpSession session) {
		System.out.println("search()");
		
		BDaoHome dao = sqlSession.getMapper(BDaoHome.class);
		model.addAttribute("search_list", dao.searchList(request.getParameter("pName")));
		
		return "search";
	}
	
	@RequestMapping("/product")
	public String product(HttpServletRequest request, Model model, HttpSession session) {
		System.out.println("product()");
		
		model.addAttribute("request", request);
		command = new BCommandHomeProduct();
		command.execute(session, model, sqlSession);
		
		return "product";
	}
	
	@RequestMapping("/register_q_view")
	public String register_q_view(HttpServletRequest request, Model model, HttpSession session) {
		System.out.println("register_q_view()");
		
		String pCode = request.getParameter("pCode");
		model.addAttribute("pCode", pCode);
		
		return "register_q_view";
	}
	
	@RequestMapping("/register_q")
	public String register_q(HttpServletRequest request, Model model, HttpSession session) {
		System.out.println("register_q()");
		
		BDaoHome dao = sqlSession.getMapper(BDaoHome.class);
		
		dao.registerQ(request.getParameter("pCode"), request.getParameter("cId"), request.getParameter("qTitle"), request.getParameter("qContent"));
		Share.pCode = request.getParameter("pCode");
		
		model.addAttribute("request", request);
		command = new BCommandHomeProductQ();
		command.execute(session, model, sqlSession);
		
		return "product";
	}

}