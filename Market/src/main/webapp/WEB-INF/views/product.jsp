<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	
    <script src="js/jquery-2.1.3.min.js"></script>
  	<script src="js/jquery.scrollTo.min.js"></script>
  
</head>
<style type="text/css">
	html {
		scroll-behavior: smooth;
	}
	.product_wrap {
		background-color: #FAFAFA;
		padding: 30px 0;
	}
	.product_top, .img_wrap, .review_wrap, .deliver_wrap, .cancel_wrap, .qa_wrap {
		width: 1000px;
		margin: auto;
		margin-bottom: 30px;
		background-color: #fff;
		display: block;
	}
	.product_top {
		height: 435px;
		padding-top: 30px;
	}
	.product_top div {
		float: left;
	}
	.product_img {
		width: 400px;
		height: 400px;
		margin-left: 30px;
		margin-right: 30px; 
		overflow: hidden;
	}
	.product_img img {
		width: 100%;
	}
	.product_info form {
		width: 500px;
	}
	.product_info form h2 {
		padding: 20px;
		border-bottom: 1px solid lightgray;
	}
	.product_info form table {
		margin-top: 20px;
	}
	.product_info form table tr td {
		padding: 10px 20px;
		font-size: 120%;
	}
	.product_info form table tr td:first-child {
		text-align: right;
	}
	
	input[type=number] {
		width: 100px;
		height: 30px;
		padding-left: 10px;
	}
	
	.product_info form input[type=submit] {
		margin-top: 20px;
		width: 500px;
		height: 50px;
		background-color: lightgray;
		border: none;
		border-radius: 10px;
		font-size: 130%;
	}
	.product_info form input[type=submit]:hover {
		background-color: red;
		color: #fff;
	}
	
	
	.discount {
		color: red;
	}
	
	/* 제품 상세 카테고리 */
	.product_nav li {
		float: left;
		width: 200px;
		line-height: 50px;
		text-align: center;
		border-bottom: 3px solid #FAFAFA;
	}
	.product_nav li.on {
		border-bottom: 3px solid red;
	}
	.product_nav li.on a {
		color: red;
		font-weight: bold;
	}
	.img {
		/* overflow: hidden; */
		top: -1000px;
	}
	
	/* 리뷰 이미지 기본 사이즈 */
	.review {
		width: 90%;
		margin: auto;
		padding-bottom: 30px;
	}
	.review_info {
		text-align: center;
	}
	.review_info div {
		display: inline-block;
		margin: 30px 30px 0 30px;
		text-align: center;
	}
	.review_info div p {
		font-size: 200%;
	}
	.review table {
		width: 100%;
	}
	.review table:first-child {
		margin-top: 20px;
	}
	.review table tr {
	}
	.review table tr td {
		border-top: 1px solid lightgray;
		padding: 10px;
	}
	.review table tr td:nth-child(1) {
		width: 106px;
	}
	.review table tr td:nth-child(2) p {
		display: inline-block;
		padding-left: 10px;
	}
	.review table tr td img {
		width: 55px;
		height: 55px;
	}
	.review table tr td img:hover {
		position: absolute;
		width: 500px;
		height: auto;
	}
	
	/* qna 아코디언 */
	.qa {
		margin: 30px;
		padding-bottom: 20px;
	}
	.uk-accordion {
		width: 750px;
		margin: auto;
	}
	.uk-accordion li {
		line-height: 50px;
    	border-bottom: 1px solid gray;
	}
	.uk-accordion-title {
	}
	.uk-accordion-title div {
		display: inline-block;
		margin: 0 2.3px;
		text-align: center;
	}
	.uk-accordion-title div.qna_qTitle {
		text-align: left;
	}
	.qna_code {
		width: 30px;
	}
	.qna_cId {
		width: 100px;
	}
	.qna_qTitle{
		width: 400px;
		padding-left: 10px;
	}
	.qna_qDate {
		width: 100px;
	}
	.qna_answer {
		width: 70px;
	}
	.uk-accordion-content div {
		border-top: 1px solid lightgray;
		display: block;
		padding: 0 40px;
	}
	.qna_content img {
		width: 100%;
		padding-top: 30px;
	}
	.qna_content p {
		width: 100%;
		word-break: break-all;
		padding-bottom: 30px;
	}
	.qa .qa_pg {
		margin: auto;
		margin-top: 30px;
	}
	.qa form {
		width: 750px;
		margin: auto;
		margin-top: 30px;
		margin-bottom: 30px;
		text-align: right;
	}
	.qnaBtn {
		background-color: lightgray;
		width: 80px;
		height: 40px;
		margin-right: 10px;
		border: none;
		border-radius: 10px;
	}
	.qnaBtn:hover {
		background-color: gray;
		color: #fff;
	}
	
</style>

<body>
	<!-- header -->
	<%@ include file="header.jsp" %>
	<!-- //header -->
	
	
	<div class="product_wrap">
		<!-- 상품 정보 및 구매 -->
		<div class="product_top">
			<div class="product_img">
				<img alt="${product_view.pName } 메인 이미지" src="${product_view.pFilePath }">
			</div>
			<div class="product_info">
				<form action="CustomerCartInsert">
					<h2>${product_view.pName }</h2>
					<table>
						<tr>
							<td>유통기한</td>
							<td>${product_view.pExpirationDate }</td>
						</tr>
						<tr>
							<td>정가</td>
							<td>${product_view.pPrice }</td>
						</tr>
						<tr>
							<td>판매가</td>
							<td>${product_view.pPriceDC } <strong class="discount">${product_view.pDiscount }%</strong></td>
						</tr>
						<tr>
							<td>재고</td>
							<td>${product_view.pProductEA }</td>
						</tr>
						<tr>
							<td>수량</td>
							<td><input type="number" name="wQuantity" value="1" size="5"></td>
						</tr>
					</table>
					<input type="hidden" value="${product_view.pCode }" name="pCode">
					<input type="submit" value="장바구니">
				</form>
			</div>
		</div>
		<!-- //상품 정보 및 구매 -->
		
		<!-- 상품 상세 정보 -->
		<!-- 상세 이미지 -->
		<div id="feature1" class="img_wrap">
			<nav>
				<ul class="product_nav">
					<li class="on"><a href="#feature1">상품상세정보</a></li>
					<li><a href="#feature2">상품후기</a></li>
					<li><a href="#feature3">배송안내</a></li>
					<li><a href="#feature4">교환 및 반품안내</a></li>
					<li><a href="#feature5">상품문의</a></li>
				</ul>
			</nav>
			
			<div class="img">
				<img alt="${product_view.pName } 상세 이미지" src="${product_view.pFilePath }" width="1000px">
			</div>
		</div>
		<!-- // 상세 이미지 -->
		
		<!-- 후기 -->
		<div id="feature2" class="review_wrap">
			<nav>
				<ul class="product_nav">
					<li><a href="#feature1">상품상세정보</a></li>
					<li class="on"><a href="#feature2">상품후기</a></li>
					<li><a href="#feature3">배송안내</a></li>
					<li><a href="#feature4">교환 및 반품안내</a></li>
					<li><a href="#feature5">상품문의</a></li>
				</ul>
			</nav>
			
			<div class="review">
				<div class="review_info">
					<div>
						<h4>리뷰 평점</h4>
						<c:choose>
							<c:when test="${review_info.star eq null }">
								<p>0</p>
							</c:when>
							<c:otherwise>
								<p>${review_info.star }</p>
							</c:otherwise>
						</c:choose>
					</div>
					<div>
						<h4>전체 리뷰 수</h4>
						<p>${review_info.count }</p>
					</div>
				</div>
				<table>
					<c:forEach items="${review_list }" var="dtoReview">
					<tr>
						<td>
							<p>${dtoReview.customer_cId }</p>
							<p>${dtoReview.bReviewScore }</p>
							<p>${dtoReview.bReviewWriteDate }</p>
						</td>
						<td>
							<img alt="${dtoReview.customer_cId }님의 리뷰 이미지" src="${pageContext.request.contextPath }/resources/reviewsave/${dtoReview.rFilePath }" onerror="this.style.display='none'">
							<p>${dtoReview.bReviewContent }</p>
						</td>
					</tr>
					</c:forEach>
				</table>
				
				<table class="review_pg">
					<tr>
						<td align="center">
						<!-- 이렇게 보일것입니다.        [◀◀] [◀] [1] [2] [3] [4]……[▶] [▶▶] -->
							[<a href="product?pCode=${product_view.pCode }"> ◀◀ </a>]
							<!-- 제일 왼쪽으로 가기. 그러므로 제일 먼저 나오기때문에 링크는 꼭 처음의 링크로 -->
							[<a href="product?pCode=${product_view.pCode }&review_pg=${review_FROMPAGE -1 }&qna_pg=${QnA_FROMPAGE -1 }">◀</a>]
							<!-- 한단계 전으로 갈거라 링크에는 꼭 “ ?pg=“ 이거 써줘야합니다. -->
							<%-- ${} 안에 들어오는 변수명은 command에서 구한 값입니다. --%>
							<c:forEach items = "${review_pageCount }" var = "page" varStatus="ftp">
							<!-- 여기서부터가 command에서 구한 리스트 페이지 숫자 배열 사용. -->
								<a href="product?pCode=${product_view.pCode }&review_pg=${ftp.count}">[ ${ftp.count } ]</a>
								<!-- 출력하면됩니다. -->
							</c:forEach>
							[<a href="product?pCode=${product_view.pCode }&review_pg=${review_TOPAGE }&qna_pg=${QnA_TOPAGE}">▶</a>]
							<!-- 위랑 똑같아요 -->
							[<a href="product?pCode=${product_view.pCode }&review_pg=${review_ALLPAGE }&qna_pg=${QnA_ALLPAGE }">▶▶</a>]
							<!-- 위랑 똑같아요 -->
						</td>
					</tr>
				</table>
			</div>
		</div>
		<!-- //후기 -->
		
		<!-- 배송정보 -->
		<div id="feature3" class="deliver_wrap">
			<nav>
				<ul class="product_nav">
					<li><a href="#feature1">상품상세정보</a></li>
					<li><a href="#feature2">상품후기</a></li>
					<li class="on"><a href="#feature3">배송안내</a></li>
					<li><a href="#feature4">교환 및 반품안내</a></li>
					<li><a href="#feature5">상품문의</a></li>
				</ul>
			</nav>
			
			<div class="deliver" style=" height: 800px;">
			
			</div>
		</div>
		<!-- //배송정보 -->
		
		<!-- 교환 및 반품안내 -->
		<div id="feature4" class="cancel_wrap">
			<nav>
				<ul class="product_nav">
					<li><a href="#feature1">상품상세정보</a></li>
					<li><a href="#feature2">상품후기</a></li>
					<li><a href="#feature3">배송안내</a></li>
					<li class="on"><a href="#feature4">교환 및 반품안내</a></li>
					<li><a href="#feature5">상품문의</a></li>
				</ul>
			</nav>
			
			<div class="cancel" style=" height: 800px; ">
			
			</div>
		</div>
		<!-- //교환 및 반품안내 -->
		
		<!-- 상품문의 -->
		<div id="feature5" class="qa_wrap">
			<nav>
				<ul class="product_nav">
					<li><a href="#feature1">상품상세정보</a></li>
					<li><a href="#feature2">상품후기</a></li>
					<li><a href="#feature3">배송안내</a></li>
					<li><a href="#feature4">교환 및 반품안내</a></li>
					<li class="on"><a href="#feature5">상품문의</a></li>
				</ul>
			</nav>
			
			<div class="qa">
				
				<ul uk-accordion>
					<%-- <c:set var="cnt" value="1" /> --%>
					<c:forEach items="${QnA_list }" var="dtoQnA">
				    <li>
				        <a class="uk-accordion-title" href="#">
				        	<%-- <div class="qna_code">${cnt }</div> --%>
				        	<div class="qna_code">${dtoQnA.qnaCode }</div>
				        	<div class="qna_cId">${dtoQnA.customer_cId }</div>
				        	<div class="qna_qTitle">${dtoQnA.qTitle }</div>
				        	<div class="qna_qDate">${dtoQnA.qAddDate }</div>
				        	<c:set var="answer" value="${dtoQnA.answer}" />
				        	<c:choose>
					        	<c:when test="${answer eq null }">
						        	<div class="qna_answer">답변전</div>
					        	</c:when>
					        	<c:otherwise>
						        	<div class="qna_answer">답변완료</div>
					        	</c:otherwise>
				        	</c:choose>
				        </a>
				        <div class="uk-accordion-content">
				        	<div class="qna_content">
				        		<img alt="" src="${pageContext.request.contextPath }/resources/qnasave/${dtoQnA.qFilePath }">
				        		<p>${dtoQnA.qContent }</p>
					        	</p>
				        	</div>
				        	<div class="qna_content">${dtoQnA.aContent }</div>
				        </div>
				    </li>
					<%-- <c:set var="cnt" value="${cnt=cnt+1 }" /> --%>
				    </c:forEach>
				</ul>
				
				<table class="qa_pg">
					<tr>
						<td align="center">
						<!-- 이렇게 보일것입니다.        [◀◀] [◀] [1] [2] [3] [4]……[▶] [▶▶] -->
							[<a href="product?pCode=${product_view.pCode }"> ◀◀ </a>]
							<!-- 제일 왼쪽으로 가기. 그러므로 제일 먼저 나오기때문에 링크는 꼭 처음의 링크로 -->
							
							<c:choose>
					        	<c:when test="${review_FROMPAGE > 1 }">
									[<a href="product?pCode=${product_view.pCode }&review_pg=${review_FROMPAGE -1 }&qna_pg=${QnA_FROMPAGE -1 }">◀</a>]
					        	</c:when>
					        	<c:otherwise>
									[<a href="product?pCode=${product_view.pCode }&review_pg=${review_FROMPAGE }&qna_pg=${QnA_FROMPAGE }">◀</a>]
					        	</c:otherwise>
				        	</c:choose>
							
							<!-- 한단계 전으로 갈거라 링크에는 꼭 “ ?pg=“ 이거 써줘야합니다. -->
							<%-- ${} 안에 들어오는 변수명은 command에서 구한 값입니다. --%>
							<c:forEach items = "${QnA_pageCount }" var = "page" varStatus="ftp">
							<!-- 여기서부터가 command에서 구한 리스트 페이지 숫자 배열 사용. -->
								<a href="product?pCode=${product_view.pCode }&qna_pg=${ftp.count}">[ ${ftp.count } ]</a>
								<!-- 출력하면됩니다. -->
							</c:forEach>
							[<a href="product?pCode=${product_view.pCode }&review_pg=${review_TOPAGE }&qna_pg=${QnA_TOPAGE }">▶</a>]
							<!-- 위랑 똑같아요 -->
							[<a href="product?pCode=${product_view.pCode }&review_pg=${review_ALLPAGE }&qna_pg=${QnA_ALLPAGE }">▶▶</a>]
							<!-- 위랑 똑같아요 -->
						</td>
					</tr>
				</table>
				
				<c:choose>
		        	<c:when test="${QnA_login eq null }">
						<form action="Login_View" method="post">
							<input type="submit" value="문의 등록" class="qnaBtn">
						</form>
		        	</c:when>
		        	<c:otherwise>
						<form action="register_q_view" method="post">
							<input type="hidden" name="QnA_login" value="${QnA_login }">
							<input type="hidden" name="pCode" value="${product_view.pCode }">
							<input type="submit" value="문의 등록" class="qnaBtn">
						</form>
		        	</c:otherwise>
	        	</c:choose>
				
			</div>
		</div>
		<!-- //상품문의 -->
		<!-- //상품 상세 정보 -->
	
	</div>
	
	<!-- footer -->
	<%@ include file="footer.jsp" %>
	<!-- //footer -->

</body>
</html>