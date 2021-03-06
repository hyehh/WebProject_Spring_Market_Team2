package com.springproject.market.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.springproject.market.command.BCommand;
import com.springproject.market.command.BCommandSellerStore;
import com.springproject.market.dao.IDaoSellerProduct;
import com.springproject.market.util.Pageing;
import com.springproject.market.util.Share;

@Controller
public class BControllerSeller {
	@Autowired
	private SqlSession sqlSession;

	BCommand command = null;
	public static int from;
	public static int to;


	/*
	 *  Command
	 */
	private BCommand addProductCommand = null;
	private BCommand deliveryListCommand = null;
	private BCommand deliverySearchListCommand = null;
	private BCommand DeliveryEndCommand = null;
	private BCommand DeliveryINGCommand = null;
	private BCommand selectOrderPage_viewCommand = null;
	private BCommand deliveryForCommand = null;
	private BCommand deliveryFinishCommand = null;

	private BCommand buyListCommand = null;
	private BCommand buySearchListCommand = null;
	private BCommand buyTureListCommand = null;
	private BCommand buyCancelListCommand = null;
	private BCommand selectBuy_vlewCommand = null;
	private BCommand buyToDeliverCommand = null;

	private BCommand QnAListCommand = null;
	private BCommand searchQnAListCommand = null;
	private BCommand QnATureListCommand = null;
	private BCommand QnAFalseListCommand = null;
	private BCommand selectQnAPage_viewCommand = null;
	private BCommand QnAAddCommand = null;
	private BCommand QnAEditCommand = null;
	private BCommand QnADeleteCommand = null;

	@Autowired
	public void autoInsert(BCommand insert) {
		this.addProductCommand = insert;
	}

	@Autowired
	public void autoDelivery(BCommand DeliveryList, BCommand deliverySearchList,
			BCommand DeliveryEnd, BCommand DeliveryING, BCommand selectOrderPage_view, BCommand deliveryFor,
			BCommand deliveryFinish) {
		this.deliveryListCommand = DeliveryList;
		this.deliverySearchListCommand = deliverySearchList;
		this.DeliveryEndCommand = DeliveryEnd;
		this.DeliveryINGCommand = DeliveryING;
		this.selectOrderPage_viewCommand = selectOrderPage_view;
		this.deliveryForCommand = deliveryFor;
		this.deliveryFinishCommand = deliveryFinish;
	}

	@Autowired
	public void autoBuy(BCommand sellerBuyList, BCommand buySearchList, BCommand buyTureList,
			BCommand buyCancelList, BCommand selectBuy_vlew, BCommand buyToDeliver) {
		this.buyListCommand = sellerBuyList;
		this.buySearchListCommand = buySearchList;
		this.buyTureListCommand = buyTureList;
		this.buyCancelListCommand = buyCancelList;
		this.selectBuy_vlewCommand = selectBuy_vlew;
		this.buyToDeliverCommand = buyToDeliver;
	}
	
	@Autowired
	public void autoQnA(BCommand QnAList, BCommand searchQnAList, BCommand QnATureList, BCommand QnAFalseList,
			BCommand selectQnAPage_view, BCommand QnAAdd, BCommand QnAEdit, BCommand QnADelete) {
		this.QnAListCommand = QnAList;
		this.searchQnAListCommand = searchQnAList;
		this.QnATureListCommand = QnATureList;
		this.QnAFalseListCommand = QnAFalseList;
		this.selectQnAPage_viewCommand = selectQnAPage_view;
		this.QnAAddCommand = QnAAdd;
		this.QnAEditCommand = QnAEdit;
		this.QnADeleteCommand = QnADelete;
	}


	/*
	 * ??????
	 */
	@RequestMapping("sellerStore")
	public String sellerStore(HttpSession session, Model model) {
		System.out.println("sellerStore");
		command = new BCommandSellerStore();
		command.execute(session, model, sqlSession);
		return "sellerStore";
	}

	/*
	 * ?????? ?????????
	 */
	@RequestMapping("/product_list_management")
	public String product_list_management(HttpServletRequest request, Model model) {
		IDaoSellerProduct dao = sqlSession.getMapper(IDaoSellerProduct.class);
		Pageing pageing = new Pageing();

		int count = dao.sales(Share.userId);
		String strPg = request.getParameter("pg");

		pageing.getFrom(count, strPg);

		model.addAttribute("SALESCOUNT", dao.sales(Share.userId));
		model.addAttribute("SALESTURE", dao.salesTrue(Share.userId));
		model.addAttribute("SALESFALSE", dao.salesFalse(Share.userId));
		model.addAttribute("list", dao.productList(Share.userId, pageing.from, pageing.to));

		// ????????? ?????????
		model.addAttribute("PG", pageing.pg); // ???????????????
		model.addAttribute("BLOCK", pageing.block); // ??????
		model.addAttribute("FROMPAGE", pageing.fromPage); // DB????????? ????????? 10????????? ????????? ????????? ??????. (??? 21?????? 3????????? ??? ??? ?????????)
		model.addAttribute("TOPAGE", pageing.toPage); // DB????????? ????????? 10????????? ????????? ????????? ??????. (??? 21?????? 3????????? ??? ??? ?????????)
		model.addAttribute("pageCount", pageing.pageCount); // jps forEach??? ????????? ?????? ?????????
		model.addAttribute("ALLPAGE", pageing.allPage); // ??? ????????? ??????

		return "product_list_management";
	}

	@RequestMapping("/ProductSearchList")
	public String ProductSearchList(HttpServletRequest request, Model model) {
		IDaoSellerProduct dao = sqlSession.getMapper(IDaoSellerProduct.class);
		Pageing pageing = new Pageing();

		int count = dao.getSearchCount(request.getParameter("search"), request.getParameter("searchTxt"));
		String strPg = request.getParameter("pg");

		pageing.getFrom(count, strPg);

		model.addAttribute("SALESCOUNT", dao.sales(Share.userId));
		model.addAttribute("SALESTURE", dao.salesTrue(Share.userId));
		model.addAttribute("SALESFALSE", dao.salesFalse(Share.userId));
		model.addAttribute("list", dao.searchProduct(Share.userId, request.getParameter("search"), request.getParameter("searchTxt"),
				pageing.from, pageing.to));

		// ????????? ?????????
		model.addAttribute("PG", pageing.pg); // ???????????????
		model.addAttribute("BLOCK", pageing.block); // ??????
		model.addAttribute("FROMPAGE", pageing.fromPage); // DB????????? ????????? 10????????? ????????? ????????? ??????. (??? 21?????? 3????????? ??? ??? ?????????)
		model.addAttribute("TOPAGE", pageing.toPage); // DB????????? ????????? 10????????? ????????? ????????? ??????. (??? 21?????? 3????????? ??? ??? ?????????)
		model.addAttribute("pageCount", pageing.pageCount); // jps forEach??? ????????? ?????? ?????????
		model.addAttribute("ALLPAGE", pageing.allPage); // ??? ????????? ??????
		return "ProductSearchList";
	}

	@RequestMapping("/ProductTureList")
	public String ProductTureList(HttpServletRequest request, Model model) {
		IDaoSellerProduct dao = sqlSession.getMapper(IDaoSellerProduct.class);
		Pageing pageing = new Pageing();

		int count = dao.salesTrue(Share.userId);
		String strPg = request.getParameter("pg");

		pageing.getFrom(count, strPg);

		model.addAttribute("SALESCOUNT", dao.sales(Share.userId));
		model.addAttribute("SALESTURE", dao.salesTrue(Share.userId));
		model.addAttribute("SALESFALSE", dao.salesFalse(Share.userId));
		model.addAttribute("list", dao.salesTureList(Share.userId, pageing.from, pageing.to));

		// ????????? ?????????
		model.addAttribute("PG", pageing.pg); // ???????????????
		model.addAttribute("BLOCK", pageing.block); // ??????
		model.addAttribute("FROMPAGE", pageing.fromPage); // DB????????? ????????? 10????????? ????????? ????????? ??????. (??? 21?????? 3????????? ??? ??? ?????????)
		model.addAttribute("TOPAGE", pageing.toPage); // DB????????? ????????? 10????????? ????????? ????????? ??????. (??? 21?????? 3????????? ??? ??? ?????????)
		model.addAttribute("pageCount", pageing.pageCount); // jps forEach??? ????????? ?????? ?????????
		model.addAttribute("ALLPAGE", pageing.allPage); // ??? ????????? ??????
		return "ProductTureList";
	}

	@RequestMapping("/ProductFalseList")
	public String ProductFalseList(HttpServletRequest request, Model model) {
		IDaoSellerProduct dao = sqlSession.getMapper(IDaoSellerProduct.class);
		Pageing pageing = new Pageing();

		int count = dao.salesFalse(Share.userId);
		String strPg = request.getParameter("pg");

		pageing.getFrom(count, strPg);

		model.addAttribute("SALESCOUNT", dao.sales(Share.userId));
		model.addAttribute("SALESTURE", dao.salesTrue(Share.userId));
		model.addAttribute("SALESFALSE", dao.salesFalse(Share.userId));
		model.addAttribute("list", dao.salesFalseList(Share.userId, pageing.from, pageing.to));

		// ????????? ?????????
		model.addAttribute("PG", pageing.pg); // ???????????????
		model.addAttribute("BLOCK", pageing.block); // ??????
		model.addAttribute("FROMPAGE", pageing.fromPage); // DB????????? ????????? 10????????? ????????? ????????? ??????. (??? 21?????? 3????????? ??? ??? ?????????)
		model.addAttribute("TOPAGE", pageing.toPage); // DB????????? ????????? 10????????? ????????? ????????? ??????. (??? 21?????? 3????????? ??? ??? ?????????)
		model.addAttribute("pageCount", pageing.pageCount); // jps forEach??? ????????? ?????? ?????????
		model.addAttribute("ALLPAGE", pageing.allPage); // ??? ????????? ??????
		return "ProductFalseList";
	}

	@RequestMapping("/selectProduct_vlew")
	public String selectProduct_vlew(HttpServletRequest request, Model model) {
		IDaoSellerProduct dao = sqlSession.getMapper(IDaoSellerProduct.class);
		ArrayList<String> category = new ArrayList<String>();
		category.add("????????????");
		category.add("????????????");
		category.add("????????????");
		category.add("??????????????????");
		category.add("?????????");
		category.add("????????????");
		category.add("?????????");

		model.addAttribute("selectProduct", dao.selectProduct(request.getParameter("pCode")));
		model.addAttribute("Category", category);
		return "selectProduct_vlew";
	}

	@RequestMapping("productModify")
	public String productModify(HttpServletRequest request, Model model) {
		IDaoSellerProduct dao = sqlSession.getMapper(IDaoSellerProduct.class);
		String pCode = request.getParameter("pCode");
		String pCategory = request.getParameter("pCategory");
		String pName = request.getParameter("pName");
		String pPriceDC = request.getParameter("pPriceDC");
		String pProductEA = request.getParameter("pProductEA");
		String pStatus = request.getParameter("pStatus");

		dao.productModify(pCategory, pName, pPriceDC, pProductEA, pStatus, pCode);
		return "redirect:product_list_management";
	}

	@RequestMapping("productDelete")
	public String productDelete(HttpServletRequest request, Model model) {
		IDaoSellerProduct dao = sqlSession.getMapper(IDaoSellerProduct.class);

		String pCode = request.getParameter("pCode");
		String pDelete = "1";

		dao.deleteList(pCode, pDelete);
		dao.deleteRegister(pCode);
		return "redirect:product_list_management";
	}

	/*
	 * ?????? ??????
	 */
	@RequestMapping("insertProduct")
	public String insertProduct(Model model) {
		return "insertProduct";
	}

	@RequestMapping("insert")
	public String insert(Model model, MultipartHttpServletRequest request) {
		HttpSession session = request.getSession();
		model.addAttribute("request", request);

		addProductCommand.execute(session, model, sqlSession);

		return "redirect:product_list_management";
	}

	/*
	 * ?????? ??????
	 */
	@RequestMapping("DeliveryList")
	public String DeliveryList(HttpServletRequest request, Model model, HttpSession session) {
		System.out.println("DeliveryList");

		model.addAttribute("request", request);

		deliveryListCommand.execute(session, model, sqlSession);
		return "DeliveryList";
	}

	@RequestMapping("deliverySearchList")
	public String deliverySearchList(HttpServletRequest request, Model model, HttpSession session) {

		model.addAttribute("request", request);

		deliverySearchListCommand.execute(session, model, sqlSession);
		return "DeliveryList";
	}

	@RequestMapping("DeliveryEnd")
	public String DeliveryEnd(HttpServletRequest request, Model model, HttpSession session) {

		model.addAttribute("request", request);

		DeliveryEndCommand.execute(session, model, sqlSession);
		return "DeliveryList";
	}

	@RequestMapping("DeliveryING")
	public String DeliveryING(HttpServletRequest request, Model model, HttpSession session) {

		model.addAttribute("request", request);

		DeliveryINGCommand.execute(session, model, sqlSession);
		return "DeliveryList";
	}

	@RequestMapping("selectOrderPage_view")
	public String selectOrderPage_view(HttpServletRequest request, Model model, HttpSession session) {

		model.addAttribute("request", request);

		selectOrderPage_viewCommand.execute(session, model, sqlSession);
		return "selectOrderPage_view";
	}

	@RequestMapping("deliveryFor")
	public String deliveryFor(HttpServletRequest request, Model model, HttpSession session) {
		model.addAttribute("request", request);

		deliveryForCommand.execute(session, model, sqlSession);
		return "redirect:DeliveryList";
	}

	@RequestMapping("deliveryFinish")
	public String deliveryFinish(HttpServletRequest request, Model model, HttpSession session) {

		model.addAttribute("request", request);

		deliveryFinishCommand.execute(session, model, sqlSession);
		return "redirect:DeliveryList";
	}

	/*
	 * ?????? ??????
	 */
	@RequestMapping("sellerBuyList")
	public String buyList(HttpServletRequest request, Model model, HttpSession session) {

		model.addAttribute("request", request);

		buyListCommand.execute(session, model, sqlSession);
		return "buyList";
	}

	@RequestMapping("buySearchList")
	public String buySearchList(HttpServletRequest request, Model model, HttpSession session) {

		model.addAttribute("request", request);

		buySearchListCommand.execute(session, model, sqlSession);
		return "buySearchList";
	}

	@RequestMapping("buyTureList")
	public String buyTureList(HttpServletRequest request, Model model, HttpSession session) {

		model.addAttribute("request", request);

		buyTureListCommand.execute(session, model, sqlSession);
		return "buyTureList";
	}

	@RequestMapping("buyCancelList")
	public String buyCancelList(HttpServletRequest request, Model model, HttpSession session) {
		model.addAttribute("request", request);

		buyCancelListCommand.execute(session, model, sqlSession);
		return "buyCancelList";
	}

	@RequestMapping("selectBuy_vlew")
	public String selectBuy_vlew(HttpServletRequest request, Model model, HttpSession session) {
		model.addAttribute("request", request);

		selectBuy_vlewCommand.execute(session, model, sqlSession);
		return "selectBuy_vlew";
	}

	@RequestMapping("buyToDeliver")
	public String buyToDeliver(HttpServletRequest request, Model model, HttpSession session) {
		model.addAttribute("request", request);

		buyToDeliverCommand.execute(session, model, sqlSession);
		return "redirect:selectOrderPage_view";
	}

	/*
	 * ?????? ??????
	 */
	@RequestMapping("QnAList")
	public String QnAList(HttpServletRequest request, Model model, HttpSession session) {
		model.addAttribute("request", request);

		QnAListCommand.execute(session, model, sqlSession);
		return "QnAList";
	}

	@RequestMapping("searchQnAList")
	public String searchQnAList(HttpServletRequest request, Model model, HttpSession session) {
		model.addAttribute("request", request);

		searchQnAListCommand.execute(session, model, sqlSession);
		return "searchQnAList";
	}

	@RequestMapping("QnATureList")
	public String QnATureList(HttpServletRequest request, Model model, HttpSession session) {
		model.addAttribute("request", request);

		QnATureListCommand.execute(session, model, sqlSession);
		return "QnATureList";
	}

	@RequestMapping("QnAFalseList")
	public String QnAFalseList(HttpServletRequest request, Model model, HttpSession session) {
		model.addAttribute("request", request);

		QnAFalseListCommand.execute(session, model, sqlSession);
		return "QnAFalseList";
	}

	@RequestMapping("selectQnAPage_view")
	public String selectQnAPage_view(HttpServletRequest request, Model model, HttpSession session) {
		model.addAttribute("request", request);

		selectQnAPage_viewCommand.execute(session, model, sqlSession);
		return "selectQnAPage_view";
	}

	@RequestMapping("QnAAdd")
	public String QnAAdd(HttpServletRequest request, Model model,HttpSession session) {
		model.addAttribute("request", request);

		QnAAddCommand.execute(session, model, sqlSession);
		return "redirect:QnAList";
	}

	@RequestMapping("QnAEdit")
	public String QnAEdit(HttpServletRequest request, Model model, HttpSession session) {
		model.addAttribute("request", request);

		QnAEditCommand.execute(session, model, sqlSession);
		return "redirect:QnAList";
	}

	@RequestMapping("QnADelete")
	public String QnADelete(HttpServletRequest request, Model model, HttpSession session) {
		model.addAttribute("request", request);

		QnADeleteCommand.execute(session, model, sqlSession);
		return "redirect:QnAList";
	}
}
