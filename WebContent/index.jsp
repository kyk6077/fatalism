<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<c:import url="./temp/b.jsp" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('.slider').bxSlider({
			mode:'vertical',
			controls:false,
			auto:true
		});
		
		
		
		
// 		$(".slider").on('mousewheel DOMMouseScroll', function(e){
// 			var wheel = e.originalEvent;
// 			var	delta = wheel.wheelDelta;
// 			if(delta>0){
// 				$('.slider').goToPrevSlide();
// 			}else{
// 				$('.slider').goToNextSlide();
// 			}
// 		});
	});
</script>
<style type="text/css">
body, html {
	margin: 0;
	padding: 0;
	height: 100%;
}
.index_container{
	width: 100%;
	height: 100%;
}
.home_image {
	width:100%;
	height: 920px;
}

.first_row {
	width: 100%;
	min-height: 100%;
	background-size: cover;
	background-image: url('./images/index_img/main01.jpg');
}

.second_row {
	width: 100%;
	min-height: 100%;
	background-size: cover;
	background-image: url('./images/index_img/main02.jpg');
}

.third_row {
	width: 100%;
	min-height: 100%;
	background-size: cover;
	background-image: url('./images/index_img/main03.jpg');
}
</style>
</head>
<body>
	<c:import url="./temp/h.jsp"></c:import>
	<div class="index_container">
	<div class="slider">
		<div class="home_image">
			<div class="first_row"></div>
		</div>

		<div class="home_image">
			<div class="second_row"></div>
		</div>

		<div class="home_image">
			<div class="third_row"></div>
		</div>
	</div>
	</div>
</body>
</html>