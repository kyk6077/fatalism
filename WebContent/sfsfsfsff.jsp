<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="./temp/b.jsp"></c:import>
<title>Insert title here</title>
<script src="https://cdn.bootpay.co.kr/js/bootpay-2.0.12.min.js"
	type="application/javascript"></script>
<script type="text/javascript">
	$(function() {
		$("#btn12").click(function() {

			BootPay.request({
				price : '1000',
				application_id : "5bfe6266396fa67afed7c874",
				name : '블링블링 마스카라',
				pg : 'lgup',
				method : 'card',
				show_agree_window : 0,

				user_info : {
					username : '이유성',
					email : 'asergh45@naver.com',
					addr : '석관동 261-157',
					phone : '010-1234-4567'
				},
				order_id : '123'
			}).error(function(data) {

				console.log(data);
			}).cancel(function(data) {

				console.log(data);
			}).ready(function(data) {

				console.log(data);
			}).confirm(function(data) {

				console.log(data);
				if (is_somthing) {
					this.transactionConfirm(data);
				} else {
					this.removePaymentWindow();
				}
			}).close(function(data) {

				console.log(data);
			}).done(function(data) {

				console.log(data);
			});
		});
	});
</script>
</head>
<body>
	<button id="btn12">asdfadsf</button>
</body>
</html>