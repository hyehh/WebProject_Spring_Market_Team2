<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<img src="${pageContext.request.contextPath }/resources/image/${param.fileNameMain}">
	<img src="${pageContext.request.contextPath }/resources/image/${param.fileNameDetail}">

</body>
</html>