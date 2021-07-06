<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 2021.05.13 조혜지 판매자 회원정보 수정 view -->
<title>판매자정보 변경</title>
</head>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function PostalCode() {
        new daum.Postcode({
            oncomplete: function(data) {
                var roadAddr = data.roadAddress;
                var extraRoadAddr = '';
                
                document.getElementById('sample4_postcode').value = data.zonecode;
                document.getElementById("sample4_roadAddress").value = roadAddr;
                
                var guideTextBox = document.getElementById("guide");
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }
</script>
<script type="text/javascript">
	function infoUpdate() {
		
		var regExpsName = /^[가-힣a-zA-z]{1,30}$/;
		var regExpsTel = /^[0-9]{11,12}$/;
		var regExpsEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
		var regExpsNumber = /^[0-9]{10}$/;
		var form = document.sellerInfoUpdateForm;
		var sName = form.sName.value;
		var sTel = form.sTel.value;
		var sEmail = form.sEmail.value;
		var sNumber = form.sNumber.value;
		
		if (!regExpsName.test(sName)) {
			alert("이름은 한글,영어 1~30자로 입력해주세요!");
			form.sName.select();
			return;
		}
		if (!regExpsTel.test(sTel)) {
			alert("전화번호는 -없이 숫자11~12자리로 입력해주세요!");
			form.sTel.select();
			return;
		}
		if (!regExpsEmail.test(sEmail)) {
			alert("이메일 형식을 확인해주세요!");
			form.sEmail.select();
			return;
		}
		if(form.sPostalCode.value == ""){
			alert("우편번호 찾기를 통해 우편번호를 입력해 주세요!");
			form.sPostalCode.focus();
			return;
		}
		if(form.sAddress1.value == ""){
			alert("우편번호 찾기를 통해 도로명주소를 입력해 주세요!");
			form.sAddress1.focus();
			return;
		}
		if(form.sAddress2.value == ""){
			alert("상세 주소를 입력해 주세요!");
			form.sAddress2.focus();
			return;
		}
		if(form.sShopName.value == ""){
			alert("상점 이름을 입력해 주세요!");
			form.sShopName.focus();
			return;
		}
		if (!regExpsNumber.test(sNumber)) {
			alert("사업자 등록 번호를 -없이 숫자 10자리만 입력해 주세요!");
			form.sNumber.select();
			return;
		}

		form.submit();
		alert("판매자정보가 수정되었습니다!");
	}
</script>
<style>
	/* .container_wrap {
		background-color: #FAFAFA;
		padding: 30px 0;
	}
	.container {
		width: 1000px;
		margin: auto;
		background-color: #fff;
	} */
	.container form {
		width: 570px;
		margin: auto;
	}
	caption {
		font-style: 200px; 
		font-weight: bold; 
		margin: 50px;
		font-size: x-large;
	}
	th, td {
		padding: 10px;
		text-align: left;
	}
	.inputText td:nth-child(2) input:not(.buttonAdd) {
		color:black; 
		background-color: white;
		padding-top:2px;
		border-width:2px; 
		border-color:gray; 
		border-top-style:none; 
		border-right-style:none; 
		border-left-style:none; 
		border-bottom-style:line
	}
	.buttonJSP{
		width: 70px;
	    background-color: #646464;
	    border: none;
	    color: #fff;
	    padding: 6px 0;
	    text-align: center;
	    text-decoration: none;
	    display: inline-block;
	    font-size: 15px;
	    margin: 10px;
	    cursor: pointer;
		border-radius: 10px;
	}
	.buttonAdd{
		width: 90px;
	    background-color: #828282;
	    border: none;
	    color: #fff;
	    padding: 2px 0;
	    text-align: center;
	    text-decoration: none;
	    display: inline-block;
	    font-size: 13px;
	    margin: 10px;
	    cursor: pointer;
		border-radius: 10px;
	}
</style>
<body>
	<%@include file = "headerSeller.jsp" %>
	<div class="container_wrap">
		<%@include file = "SellerInfo.jsp" %>
		<div class="container">
			<form name="sellerInfoUpdateForm" action="SellerInfoUpdate" method="post">
				<table border="0">
					<caption>판매자정보 변경</caption>
						<tr>
							<td>ID</td>
							<td>${SELLERINFO.sId }</td>
						</tr>
						<tr class="inputText">
							<td>이름</td>
							<td><input type="text" name="sName" value="${SELLERINFO.sName }" size="25" placeholder="이름을 입력하세요."></td>
						</tr>
						<tr class="inputText">
							<td>전화번호</td>
							<td><input type="text" name="sTel" value="${SELLERINFO.sTel }" size="25" placeholder="-를 생략하고 입력하세요."></td>
						</tr>
						<tr class="inputText">
							<td>이메일</td>
							<td><input type="text" name="sEmail" value="${SELLERINFO.sEmail }" size="25" placeholder="이메일을 입력하세요."></td>
						</tr>
						<tr>
							<td>생년월일</td>
			 				<td> 
			 					<select name="sBirthY">
									<c:forEach var="i" begin="1900" end="2021" step="1" >
										<c:choose>
										<c:when test ="${fn:substring(SELLERINFO.sBirth,0,4) == i }">
											<option value="${i }" selected="selected">${i }</option>
										</c:when>
			  							<c:otherwise>
											<option value="${i }">${i }</option>
										</c:otherwise>  
										</c:choose>
									</c:forEach>
								</select>
								년 
			  				  	<select name="sBirthM">
									<c:forEach var="i" begin="1" end="12" step="1" >
										<c:choose>
										<c:when test ="${fn:substring(SELLERINFO.sBirth,5,7) == i }">
											<option value="${i }" selected="selected">${i }</option>
										</c:when>
			  							<c:otherwise>
											<option value="${i }">${i }</option>
										</c:otherwise>  
										</c:choose>
									</c:forEach>
								</select>
								월 
			 				  	<select name="sBirthD">
									<c:forEach var="i" begin="1" end="31" step="1" >
										<c:choose>
										<c:when test ="${fn:substring(SELLERINFO.sBirth,8,10) == i }">
											<option value="${i }" selected="selected">${i }</option>
										</c:when>
			  							<c:otherwise>
											<option value="${i }">${i }</option>
										</c:otherwise>  
										</c:choose>
									</c:forEach>
								</select>
								일 
							</td>
						</tr>	
						<tr class="inputText">
							<td>주소</td>
							<td>
								<input type="text" id="sample4_postcode" name="sPostalCode" placeholder="우편번호를 입력하세요." value="${SELLERINFO.sPostalCode }">
								<input class="buttonAdd" type="button" onclick="PostalCode()" value="우편번호 찾기"><br>
								<input type="text" id="sample4_roadAddress" name="sAddress1" placeholder="도로명주소를 입력하세요." value="${SELLERINFO.sAddress1 }">
								<span id="guide" style="color:#999;display:none"></span>
								<input type="text" id="sample4_detailAddress" name="sAddress2" placeholder="상세주소를 입력하세요." value="${SELLERINFO.sAddress2 }">
							</td>
						</tr>
						<tr class="inputText">
							<td>상점이름</td>
							<td><input type="text" name="sShopName" value="${SELLERINFO.sShopName }" size="25" placeholder="상점이름을 입력하세요."></td>
						</tr>
						<tr class="inputText">
							<td>사업자 등록 번호</td>
							<td><input type="text" name="sNumber" value="${SELLERINFO.sNumber }" size="25" placeholder="-를 생략하고 입력하세요."></td>
						</tr>
						<tr>
							<td></td>
							<td><input class="buttonJSP" type="button" style="float:right" value="확인" onclick="infoUpdate()"></td>
						</tr>
				</table>
			</form>
		</div>
	</div>
	<%@include file = "footerSeller.jsp" %>
</body>
</html>