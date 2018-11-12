<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<c:if test="${result eq '1'}">
		<h3>${param.id}는 사용 가능한 아이디 입니다.</h3>
		</c:if>
		<c:if test="${result eq '2'}">
		<h3>${param.id} 사용 불가능한 이이디 입니다. 다시입력해주세요.</h3>
		</c:if>
	</div>



</body>
</html>