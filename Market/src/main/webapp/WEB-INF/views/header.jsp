<%@page import="com.springproject.market.util.Share"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert tile here</title>
	
	<!-- UIkit CSS -->
	<!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/uikit@3.6.22/dist/css/uikit.min.css" /> -->
	
	<!-- UIkit JS -->
	<script src="https://cdn.jsdelivr.net/npm/uikit@3.6.22/dist/js/uikit.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/uikit@3.6.22/dist/js/uikit-icons.min.js"></script>
	
	<link rel="stylesheet" href="/market/resources/css/css1.css">
	<link rel="stylesheet" href="/market/resources/css/search.css">
</head>
<style>
	.logo{
		width: 300px;
		margin: auto;
	}
	.logo img {
		width: 300px;
	}
</style>
<body>
	<header style="height: auto;">
		<!-- 최상단 메뉴 -->
		<div class="top">
			<ul class="top_list">
			<c:choose>
				<c:when test="${empty userId}">
					<li><a href="login">로그인</a></li>
					<li><a href="AgreementTerms.jsp">회원가입</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="CustomerCart_View">장바구니</a></li>
					<li><a href="OrderList_View">마이페이지</a></li>
					<li><a href="logout">로그아웃</a></li>
				</c:otherwise>
			</c:choose>
			</ul>
		</div>
		<!-- //최상단 메뉴 -->
		
		<!-- 로고 -->
		<div class="logo" style="height: 305px;">
			<a href="main"><img alt="떨이마켓 로고" src="resources/img/logoImg.png"></a>
		</div>
		<!-- //로고 -->
		
		<!-- 카테고리 -->
		<div class="category">
			<nav>
				<ul class="category_list">
					<li><a href="best.do">Best</a></li>
					<li><a href="new.do">New</a></li>
					<li><a href="deadline.do">마감임박</a></li>
					<li><a href="category.do?pCategory=주방용품">주방용품</a></li>
					<li><a href="category.do?pCategory=세탁용품">세탁용품</a></li>
					<li><a href="category.do?pCategory=청소용품">청소용품</a></li>
					<li><a href="category.do?pCategory=인테리어소품">인테리어소품</a></li>
					<li><a href="category.do?pCategory=통조림">통조림</a></li>
					<li><a href="category.do?pCategory=냉동식품">냉동식품</a></li>
					<li><a href="category.do?pCategory=식음료">식음료</a></li>
				</ul>
			</nav>
			<div class="search">
				<div class="uk-margin">
				    <form action="search.do" class="uk-search uk-search-default">
				        <span uk-search-icon></span>
				        <input class="uk-search-input" type="search" placeholder="Search" name="pName">
				    </form>
				</div>
			</div>
		</div>
		<!-- //카테고리 -->
	</header>
</body>
</html>