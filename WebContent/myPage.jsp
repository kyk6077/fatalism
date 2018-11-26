<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="./temp/b.jsp"></c:import>
<style type="text/css">
	.mypage_top{
		border: 5px solid #e8e8e8;
		color: #404040;
		position:  relative;
		padding: 20px 0;
	}
	.mypage_top_ul{
		height: 90px;
		padding: 0;
	}
	.mypage_top_li{
		float: left;
		margin: 5px 0;
		padding: 0 45px;
		width: 50%;
		height: 20px;
		font-size: 12px;
		line-height: 1.6;
	}
	.mypage_top_title{
		float: left;
		width: 37%;
		padding: 0 0 0 10px;
		font-weight: normal;
		box-sizing: border-box;
	}
	.data{
		float: left;
		width: 50%;
		padding: 0 10px 0 10px;
		text-align: right;
		box-sizing: border-box;
	}
	#line{
		position: absolute;
		top: 0;
		left: 50%;
		display: block;
		content: "";
		width: 1px;
		height: 100%;
		background-color: #e6e6e6;
	}
	.mypage_body{
		margin: 20px 0 0 ;
		border: 1px solid #e9e9e9;
	}
	.mypage_body_top{
		padding: 11px 21px;
		margin: 0;
		border-bottom: 1px soild #e9e9e9;
		background: #f6f6f6;
	}
	.desc{
		font-size: 11px;
		color: #8f8f8f;
	}
	.mypage_body_body{
		overflow: hidden;
		padding: 19px 0;
	}
	.mypage_order{
		float: left;
		width: 80%;
	}
	.mypage_order li{
		float: left;
		width: 25%;
		padding: 0 0 4px;
		border-right: 1px dotted #c9c7ca;
		text-align: center;
	}
	.mypage_order strong{
		display: block;
		margin: 2px 0 7px;
		font-size: 14px;
	}
	.mypage_order a{
		font-weight: bold;
		font-size: 24px;
		color: black;
	}
	.cs{
		float: left;
		width: 20%;
	}
	.cs li{
		margin: 0 0 5px 55px;
	}
	.cs strong{
		font-weight: normal;
		font-size: 12px;
	}
	.cs a {
		color: #000;
		font-weight: bold;
	}
	#shopMain{
		margin: 80px auto 120px;
		width: 100%;
		overflow: hidden;
	}
	#shopMain ul{
		height: 220px;
		padding: 0;
	}
	#shopMain li{
		width: 21%;
		float: left;
		position: relative;
		margin: 0 13% 20px 13%;
		background: white;
	}
	#shopMain a{
		display: block;
		padding: 25px 0;
		border-bottom: 1px solid #a6a6a6;
		border-right: 1px solid #a6a6a6;
		border-left: 1px solid #e4e4e4;
		border-top: 1px solid #e4e4e4;
		line-height: 25px;
		font-weight: bold;
		font-size: 13px;
		text-align: center;
		color: #000;
	}
</style>
</head>
<body>
<c:import url="./temp/h.jsp"></c:import>
	<div id="sub_container">
		<div id="sub_contents">
			<div class="titleArea">
				<h3>MY PAGE</h3>
			</div>
			<div class="mypage_top">
				<div id="line"></div>
				<ul class="mypage_top_ul">
					<li class="mypage_top_li">
						<strong class="mypage_top_title">> 가용적립금</strong>
						<strong class="data">
							<span>${member.point }원</span>
						</strong>
					</li>
					<li class="mypage_top_li">
						<strong class="mypage_top_title">> 총적립금</strong>
						<strong class="data">
							<span>${member.point}원</span>
						</strong>
					</li>
					<li class="mypage_top_li">
						<strong class="mypage_top_title">> 사용적립금</strong>
						<strong class="data">
							<span>0원</span>
						</strong>
					</li>
					<li class="mypage_top_li">
						<strong class="mypage_top_title">> 총주문</strong>
						<strong class="data">
							<span>0원</span>
							(
							<span>0</span>
							회)
						</strong>
					</li>
					<li class="mypage_top_li">
						<strong class="mypage_top_title">> 쿠폰</strong>
						<strong class="data">
							<span>0개</span>
						</strong>
					</li>
					<li class="mypage_top_li">
						<strong class="mypage_top_title">> 예치금</strong>
						<strong class="data">
							<span>${member.money}원</span>
						</strong>
					</li>
				</ul>
			</div>
			<div class="mypage_body">
				<div class="mypage_body_top">
					<h5 style="font-weight: bold;">나의 주문처리 현황<!--a태그에 href걸어주기 -->
					<span class="desc">(최근 <em style="color: black; font-style: normal;">3개월</em> 기준)</span>
					</h5>
				</div>
				<div class="mypage_body_body">
					<ul class="mypage_order">
						<li>
							<strong>입금전</strong>
							<a class="count">
								<span>0<!-- 이분분은 카운트가 올라가야함 --></span>
							</a>
						</li>
						<li>
							<strong>배송준비중</strong>
							<a class="count">
								<span>0<!-- 이분분은 카운트가 올라가야함 --></span>
							</a>
						</li>
						<li>
							<strong>배송중</strong>
							<a class="count">
								<span>0<!-- 이분분은 카운트가 올라가야함 --></span>
							</a>
						</li>
						<li>
							<strong>배송완료</strong>
							<a class="count" >
								<span>0<!-- 이분분은 카운트가 올라가야함 --></span>
							</a>
						</li>
					</ul>
					<ul class="cs">
						<li>
							<strong>취소 : </strong>
							<a>
								0
							</a>
						</li>
						<li>
							<strong>교환 : </strong>
							<a>
								0
							</a>
						</li>
						<li>
							<strong>반품 : </strong>
							<a>
								0
							</a>
						</li>
					</ul>
				</div>
			</div>
			<div id="shopMain">
				<ul>
					<li>
						<p>
							<a href="#">
								MY BOARD
								<br>
								<span style="font-weight: normal;">게시물관리</span>
							</a>
						</p>
					</li>
					<li>
						<p>
							<a href="#">
								ADDRESS BOOK
								<br>
								<span style="font-weight: normal;">배송주소록관리</span>
							</a>
						</p>
					</li>
				</ul>
			</div>
		</div>
	</div>
<c:import url="./temp/footer.jsp"></c:import>
</body>
</html>