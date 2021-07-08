package com.springproject.market.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springproject.market.command.BCommand;
import com.springproject.market.command.BCommandFindIdAction;
import com.springproject.market.command.BCommandFindPwAction;
import com.springproject.market.command.BCommandLoginAction;
import com.springproject.market.command.BCommandSignupCustomer;
import com.springproject.market.command.BCommandSignupSeller;
import com.springproject.market.command.BCommandcIdDupleCheck;
import com.springproject.market.command.BCommandsIdDupleCheck;
import com.springproject.market.dao.BDaocIdCheck;
import com.springproject.market.util.Share;

@Controller
public class BControllerLogin {

	//private JdbcTemplate template;
	BCommand command = null;
	private BCommand cIdDupleCheck = null;
	private BCommand sIdDupleCheck = null;
	private BCommand findIdAction = null;
	private BCommand findPwAction = null;
	private BCommand loginAction = null;
	private BCommand signupCustomer = null;
	private BCommand signupSeller = null;
	
	@Autowired
	private void auto(BCommand cIdDupleCheck, BCommand sIdDupleCheck, BCommand findIdAction, BCommand findPwAction,
				 BCommand loginAction, BCommand signupCustomer, BCommand signupSeller) {
		this.cIdDupleCheck = cIdDupleCheck;
		this.sIdDupleCheck = sIdDupleCheck;
		this.findIdAction = findIdAction;
		this.findPwAction = findPwAction;
		this.loginAction = loginAction;
		this.signupCustomer = signupCustomer;
		this.signupSeller = signupSeller;
	}
	
	@Autowired
	private SqlSession sqlSession;
	
//	===============================
	
	@RequestMapping("/login")
	public String login() {
		return "Login_View";
	}
	
	@RequestMapping("/AgreementTerms")
	public String AgreementTerms() {
		return "AgreementTerms";
	}
	
	@RequestMapping("/signupCustomer")
	public String signupCustomer() {
		return "signupCustomer";
	}
	
	@RequestMapping("/cIdCheck")
	public String cIdCheck() {
		return "cIdCheck";
	}
	
	@RequestMapping("/signupcIdDupleCheck")
	public String signupcIdDupleCheck(HttpSession session, HttpServletRequest request, Model model) {
//		BDaocIdCheck dao = sqlSession.getMapper(BDaocIdCheck.class);
		model.addAttribute("request", request);
		cIdDupleCheck.execute(session, model, sqlSession);
		
		request.setAttribute("cIdchk", session.getAttribute("cIdchk"));
		request.setAttribute("cIdchkMessage", session.getAttribute("cIdchkMessage"));
		request.setAttribute("cId", session.getAttribute("cId"));
		request.setAttribute("cHIDDEN", session.getAttribute("cHIDDEN"));
		
//		System.out.print(request.getAttribute("cHIDDEN"));
//		System.out.println("Controller Msg= " + request.getAttribute("cIdchkMessage") );
//		System.out.println("Controller cId= " + request.getAttribute("cId") );
//		System.out.println("Controller cIdchk= " + request.getAttribute("cIdchk") );
		
		return "cIdCheckAlert";
	}
	
	@RequestMapping("/signupCustomerAction")
	public String signupCustomerAction(HttpSession session, HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		signupCustomer.execute(session, model, sqlSession);
		
		return "signupComplete";
	}
	
	@RequestMapping("/signupSeller")
	public String signupSeller() {
		return "signupSeller";
	}
	
	@RequestMapping("/sIdCheck")
	public String sIdCheck() {
		return "sIdCheck";
	}
	
	@RequestMapping("/signupsIdDupleCheck")
	public String signupsIdDupleCheck(HttpSession session, HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		sIdDupleCheck.execute(session, model, sqlSession);
		
		request.setAttribute("sIdchk", session.getAttribute("sIdchk"));
		request.setAttribute("sIdchkMessage", session.getAttribute("sIdchkMessage"));
		request.setAttribute("sId", session.getAttribute("sId"));
		request.setAttribute("sHIDDEN", session.getAttribute("sHIDDEN"));
		
//		System.out.print(request.getAttribute("sHIDDEN"));
//		System.out.println("Controller Msg= " + request.getAttribute("sIdchkMessage") );
//		System.out.println("Controller sId= " + request.getAttribute("sId") );
//		System.out.println("Controller sIdchk= " + request.getAttribute("sIdchk") );
		
		return "sIdCheckAlert"; 
	}
	
	@RequestMapping("/signupSellerAction")
	public String signupSellerAction( HttpSession session, HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		signupSeller.execute(session, model, sqlSession);
		
		return "signupComplete";
	}
	
	@RequestMapping("/FindId")
	public String FindId() {
		return "FindId";
	}
	
	@RequestMapping("/findIdAction")
	public String findIdAction(HttpSession session, HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		findIdAction.execute(session, model, sqlSession);
		
		request.setAttribute("findmsg", (String)session.getAttribute("findmsg"));
		request.setAttribute("RETURNJSP", session.getAttribute("RETURNJSP"));
		session.getAttribute("RETURNJSP");
		
		String returnJsp = (String) request.getAttribute("RETURNJSP");
		Share.findId ="";
		
		return returnJsp;
	}
	
	@RequestMapping("/FindPw")
	public String FindPw() {
		return "FindPw";
	}
	
	@RequestMapping("/findPwAction")
	public String findPwAction(HttpSession session, HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		findPwAction.execute(session, model, sqlSession);
		
		request.setAttribute("findmsg", (String)session.getAttribute("findmsg"));
		request.setAttribute("RETURNJSP", session.getAttribute("RETURNJSP"));
		session.getAttribute("RETURNJSP");
		
		String returnJsp = (String) request.getAttribute("RETURNJSP");
		Share.findPw ="";
		
		return returnJsp;
		
		
	}
	
	@RequestMapping("/loginAction")
	public String loginAction(HttpSession session, HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		loginAction.execute(session, model, sqlSession);
		
		
//		System.out.println(session.getAttribute("userId"));
		
		String returnJsp = (String) session.getAttribute("RETURNJSP");
//		System.out.println(returnJsp);
		//Command에서 처리한 결과에 따라서 다른 viewPage로 연결
		return returnJsp;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session, Model model) {
		session.invalidate();
		
		Share.userId="";
		Share.userPw="";
		return "redirect:main";
	}
	
	
	
	
	
	
	
}//============
