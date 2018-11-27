<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/b.jsp" />
<style type="text/css">
#sub_contents {
	overflow: hidden;
	padding: 30px 160px 80px 340px;
	margin: 0 auto;
}

div {
	display: block;
}

html, body, div, dl, dt, dd, ul, ol, li, h1, h2, h3, h4, h5, h6, pre,
	code, form, fieldset, legend, input, textarea, p, blockquote, th, td,
	img {
	margin: 0;
	padding: 0;
}

.xans-product-menupackage .title {
	height: 85px;
	background: #fff;
	margin: 0px 0 0px;
	zoom: 1;
	text-align: center;
	z-index: 4;
}

.xans-product-menupackage .title h2 {
	height: 25px;
	padding: 10px 0px 0 0;
	background: #fff;
	text-align: center;
}

.xans-product-menupackage .title h2 span {
	width: 100%;
	text-align: center;
	font-weight: 500;
	font-size: 18px;
	font-family: 'Roboto', nanum gothic, sans-serif, dotum, verdana, arial;
	color: #252525;
}

.xans-product-menupackage .title ul {
	width: 100%;
	text-align: center;
	margin: 0 auto;
	height: 21px;
	padding: 10px 0 0 0;
	color: #272823;
}

.xans-product-menupackage .title p {
	display: inline-block;
	margin: 5px 0 0 0;
	padding: 0 0 0 0;
	color: #939393;
	*display: inline;
	*zoom: 1;
}

.xans-product-menupackage .title:after {
	content: "";
	display: block;
	clear: both;
}

.xans-product-listnormal {
	margin: 0px auto;
	width: 100%;
}

.xans-product-listnormal ul {
	clear: both;
	zoom: 1;
	margin: 0px auto;
}

.xans-product-listnormal ul li {
	width: 46%;
	float: left;
	margin: 0px auto;
	padding: 30px 2% 30px 2%;
	line-height: 1.6em;
}

.xans-product-listnormal ul .prdImg {
	display: block;
	background: #000;
}

a {
	text-decoration: none;
	color: #000;
	-webkit-transition-property: width, height, background-color, font-size,
		left, top, right, bottom, border, color, margin, opacity;
	-webkit-transition-duration: 0.5s;
	-webkit-transition-timing-function: ease;
	-ms-transition-property: width, height, background-color, font-size,
		left, top, right, bottom, border, color, margin, opacity;
	-ms-transition-duration: 0.5s;
	-ms-transition-timing-function: ease;
	-ms-transition-property: width, height, background-color, font-size,
		left, top, right, bottom, border, color, margin, opacity;
	-ms-transition-duration: 0.5s;
	-ms-transition-timing-function: ease;
	-o-transition-property: width, height, background-color, font-size, left,
		top, right, bottom, border, color, margin, opacity;
	-o-transition-duration: 0.5s;
	-o-transition-timing-function: ease;
	transition-property: width, height, background-color, font-size, left,
		top, right, bottom, border, color, margin, opacity;
	transition-duration: 0.5s;
	transition-timing-function: ease;
}

a:-webkit-any-link {
	color: -webkit-link;
	cursor: pointer;
	text-decoration: underline;
}

.xans-product-listnormal ul .prdImg img {
	display: block;
	width: 100%;
}

img, fieldset {
	border: none;
	vertical-align: top;
}

.ec-base-paginate {
	margin: 30px 0;
	text-align: center;
	font-size: 0;
	line-height: 0;
	clear: both;
}

.ec-base-paginate img {
	vertical-align: top;
}

img, fieldset {
	border: none;
	vertical-align: top;
}

.ec-base-paginate ol {
	display: inline-block;
	font-size: 0;
	line-height: 0;
	vertical-align: top;
	*display: inline;
	*zoom: 1;
	*margin: 0;
}

.ec-base-paginate li:first-child {
	margin-left: 0;
}

.ec-base-paginate li {
	display: inline-block;
	margin: 0 0 0 -1px;
	border: 1px solid #d7d5d5;
	font-size: 12px;
	color: #757575;
	vertical-align: top;
	*display: inline;
	*zoom: 1;
}

.ec-base-paginate li a.this {
	padding-bottom: 6px;
	border-bottom: 3px solid #252525;
	color: #252525;
}

.ec-base-paginate li a {
	display: block;
	width: 33px;
	padding: 9px 0;
	font-weight: bold;
	color: #939393;
	line-height: 14px;
	background: #fff;
}

a:-webkit-any-link {
	color: -webkit-link;
	cursor: pointer;
	text-decoration: underline;
}

li {
	list-style: none;
}
</style>
</head>
<body>
	<c:import url="../temp/h.jsp" />
	<div id="sub_container">
		<div id="sub_contents">

			<div class="xans-element- xans-product xans-product-menupackage ">
				<div
					class="xans-element- xans-product xans-product-headcategory title ">
					<h2>
						<span>PRESS</span>
					</h2>
					<ul></ul>
					<p>협찬이미지를 클릭하시면 관련 상품 구매와 확인이 가능합니다</p>
				</div>
			</div>



			<div class="xans-element- xans-product xans-product-normalpackage ">
				<div class="xans-element- xans-product xans-product-listnormal">
					<ul>
						<li class="xans-record-"><a
							href="../images/hyupchan_pics/hy1.jpg" class=" prdImg"><img
								src="../images/test_hy/hy1.jpg" style="" /></a></li>
						<li class="xans-record-"><a
							href="../images/hyupchan_pics/hy2.jpg" class=" prdImg"><img
								src="../images/test_hy/hy2.jpg" style="" /></a></li>
						<li class="xans-record-"><a
							href="../images/hyupchan_pics/hy3.jpg" class=" prdImg"><img
								src="../images/test_hy/hy3.jpg" style="" /></a></li>
						<li class="xans-record-"><a
							href="../images/hyupchan_pics/hy4.jpg" class=" prdImg"><img
								src="../images/test_hy/hy4.jpg" style="" /></a></li>
						<li class="xans-record-"><a
							href="../images/hyupchan_pics/hy5.jpg" class=" prdImg"><img
								src="../images/test_hy/hy5.jpg" style="" /></a></li>
						<li class="xans-record-"><a
							href="../images/hyupchan_pics/hy6.jpg" class=" prdImg"><img
								src="../images/test_hy/hy6.jpg" style="" /></a></li>
						<li class="xans-record-"><a
							href="../images/hyupchan_pics/hy7.jpg" class=" prdImg"><img
								src="../images/test_hy/hy7.jpg" style="" /></a></li>
						<li class="xans-record-"><a
							href="../images/hyupchan_pics/hy8.jpg" class=" prdImg"><img
								src="../images/test_hy/hy8.jpg" style="" /></a></li>
					</ul>

				</div>
			</div>
		</div>
	</div>
	<c:import url="../temp/footer.jsp" />
</body>
</html>
