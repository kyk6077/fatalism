<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="../../../temp/b.jsp" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('.slider').bxSlider({
			mode : 'fade',
			controls : false,
			auto : true
		});

	})
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.home_image {
	width: 100%;
	height: 350px;
}

.first_row {
	width: 100%;
	height: 350px; background-size : cover;
	background-image: url('../images/shop_slide_img/list01.jpg');
	background-size: cover;
}

.second_row {
	width: 100%;
	height: 350px;
	background-size: cover;
	background-image: url('../images/shop_slide_img/list02.jpg');
}

.third_row {
	width: 100%;
	height: 350px;
	background-size: cover;
	background-image: url('../images/shop_slide_img/list03.jpg');
}

.fourth_row {
	width: 100%;
	height: 350px;
	background-size: cover;
	background-image: url('../images/shop_slide_img/list04.jpg');
}

.shop_title {
	margin-top: 20px;
}

.container_body {
	min-width: 800px;
	text-align: center;
}

.product_items {
	width: 97%;
	min-width: 750px;
}

.product_items>li {
	width: 23%;
	min-width: 200px;
	height: 600px;
	display: inline-block;
	padding: 5px;
	margin-top: 20px;
	margin-bottom: 20px;
	vertical-align: top;
}

.item {
	width: 100%;
	height: 60%;
}

.cartbtn_row {
	text-align: right;
	margin-top: 5px;
}

.item_contents_row {
	text-align: left;
	margin-top: 5px;
	border-top: 1px solid #F6F6F6;
}

.item_name {
	font-size: 12px;
	color: #696969
}

.item_price {
	font-size: 13px;
}

.item_des {
	font-size: 10px;
}
</style>
</head>
<body>
	<c:import url="../../../temp/h.jsp" />
	<div id="sub_container">
		<div id="sub_contents">
			<div class="container_body">
				<a href="./productInsert.do">write</a>
				<div class="slider">
					<div class="shop_image">
						<div class="first_row"></div>
					</div>

					<div class="shop_image">
						<div class="second_row"></div>
					</div>

					<div class="shop_image">
						<div class="third_row"></div>
					</div>

					<div class="shop_image">
						<div class="fourth_row"></div>
					</div>
				</div>


				<h4 class="shop_title">${kind}</h4>
				<ul class="product_items">

					<c:forEach items="${product_list}" var="productDTO">
						<li><c:forEach items="${upload_list}" var="uploadDTO">
								<c:if test="${productDTO.pnum==uploadDTO.pnum}">
									<a href="./productSelect.do?pnum=${productDTO.pnum}"><img class="item" alt=""
										src="${pageContext.request.contextPath }/upload/${uploadDTO.fname}"></a>
								</c:if>
							</c:forEach>
							<div class="cartbtn_row">
								<img alt="" src="../images/cart_btn.png">
							</div>
							<div class="item_contents_row">
								<span class="item_name">#${productDTO.num}
									${productDTO.name}<br>
								</span><strong><span class="item_price">${productDTO.price}원<br></span></strong>
								<span class="item_des">${productDTO.description}</span>
							</div></li>
					</c:forEach>
				</ul>



				<div class="row_pager">
					<ul class="pagination">
						<c:if test="${pager.startNum > 1}">
							<li><a
								href="./productList.do?curPage=${pager.startNum-1}&type=${pager.search.kind}"><span
									class="glyphicon glyphicon-chevron-left"></span></a></li>
						</c:if>
						<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" 
							var="p">
							<li><a
								href="./productList.do?curPage=${p}&type=${pager.search.kind}">${p}</a></li>
						</c:forEach>

						<c:if test="${pager.curBlock < pager.totalBlock}">
							<li><a
								href="./productList.do?curPage=${pager.lastNum+1}&type=${pager.search.kind}"><span
									class="glyphicon glyphicon-chevron-right"></span></a></li>
						</c:if>
					</ul>
				</div>

			</div>
		</div>
	</div>
	<c:import url="../../../temp/footer.jsp" />
</body>
</html>