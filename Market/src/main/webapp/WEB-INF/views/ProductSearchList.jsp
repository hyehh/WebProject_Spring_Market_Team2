<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 관리</title>
<link rel="stylesheet" href="/market/resources/css/css.css">
</head>
<!-- <script language="javascript">document.google.category.value=document.google.cate.value;</script> -->
<script type="text/javascript">
	document.search.value = ${search}
</script>
<style>
	.container_wrap {
		background-color: #FAFAFA;
		padding: 30px 0;
	}
	.container {
		width: 1000px;
		margin: auto;
		background-color: #fff;
		padding: 50px 0;
	}
</style>
<body>
	<%@include file = "headerSeller.jsp" %>
	<div class="container_wrap">
		<div class="container">
			<h2 align="center">상품 관리</h2>
			<br>
			<form action="ProductSearchList" method="post">
				<table class="table">
					<tr>
						<td>상품현황</td>
						<td>총 상품 <a href = "product_list_management">${SALESCOUNT }</a>건</td>
						<td>판매중 <a href = "ProductTureList">${SALESTURE }</a>건</td>
						<td>품절 <a href = "ProductFalseList">${SALESFALSE }</a>건</td>
						<td></td>
					</tr>
					<tr>
						<td>검색 필터</td>
						<td>
							<select name = "search" id = "search">
								<option value = " and pName">상품명</option>
								<option value = " and pCategory">종류명</option>
							</select>
						</td>
						<td colspan="2"><input type = "text" name = "searchTxt" size = "30"></td>
						<td><input type="submit" value="검색" class="button button5" style="zoom: 0.3;"></td>
					</tr>
				</table>
			</form>
			<br>
			<br>
			<br>
			<br>
			<h2 align="center">상품 목록</h2>
			<br>
			<table class="table">
				<tr>
					<th>번호</th>		
					<th>상품종류</th>		
					<th>상품명</th>		
					<th>가격</th>		
					<th>할인가격</th>		
					<th>유통기한</th>		
					<th>품절여부</th>		
					<th>등록일</th>		
				</tr>
				<c:forEach items="${list}" var="dto">
				<tr class="value" onclick="location.href='selectProduct_vlew?pCode=${dto.pCode }'">
					<td>${dto.pCode }</td>
					<td>${dto.pCategory }</td>
					<td>${dto.pName }</td>
					<td>${dto.pPrice }</td>
					<td>${dto.pPriceDC }</td>
					<td>${dto.pExpirationDate }</td>
					<td>${dto.pStatus }</td>
					<td>${dto.pAddDate }</td>
				</tr>
				</c:forEach>
			</table>
			<br>
			<table class="table">
				<tr>
					<td align="center">
			      	  	<a href="ProductSearchList"><button type="button" class="buttonNum buttonNum">◀◀</button></a>
						<a href="ProductSearchList?pg=${FROMPAGE }"><button type="button" class="buttonNum buttonNum">◀</button></a> 
							<c:forEach items="${pageCount }" var="page" varStatus="ftp">
								<a href="ProductSearchList?pg=${ftp.count}"><button type="button" class="buttonNum buttonNum">${ftp.count }</button></a>
							</c:forEach> 
						<a href="ProductSearchList?pg=${TOPAGE }"><button type="button" class="buttonNum buttonNum">▶</button></a> 
						<a href="ProductSearchList?pg=${ALLPAGE }"><button type="button" class="buttonNum buttonNum">▶▶</button></a>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<%@ include file="footerSeller.jsp" %>
</body>
</html>