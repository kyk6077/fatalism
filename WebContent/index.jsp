<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="./temp/b.jsp"/>
 
 <script type="text/javascript">
   	$(function() {
 		$(".abox_dt").click(function() {
 			 $("dd").toggle(350);
 		});
		/* 스토어 누르면 이벤트가 발생하는 코드 */
 	});
</script>
</head>
<body>

<c:import url="./temp/h.jsp"></c:import>


</body>
</html>