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
	.titleArea2{
		text-align: center;/* 가운데 정렬 */
		margin: 10px 0 92px;
	}
	.product_main{
		width: 100%;
		position: relative;
		margin: 0 auto;
	}
	.detailArea{
		padding: 0 0 0 50%;
		height: 870px;
	}
	.main_img{
		float: left;
		width: 100%;
		height:100%;
		margin: 10px 0 0 -100%;
	}
	.keyImg{
		width: 82.5%;
		margin: 0 0.5% 0 0;
		text-align: left;
		float: left; 
	}
	.thumbnail{
		display: inline-block;
		position: relative;
		max-width: 100%;
		padding: 0px;
	}
	.sub_img{
		float: left;
		display: inline-block;
		width: 16.1%;
		margin: 0 auto 0;
	}
	.record{
		width: 100%;
		display: inline-block;
		margin: 0 0 2px;
		font-size: 12px;
		line-height: 14px;
		vertical-align: top;
	}
	.ThumdImage{
		width: 100%;
		border: 1px solid #ececec;
		cursor: pointer;
	}
	.icon{
		padding: 0 0 4px 0px;
		width: 400px;
	}
	.infoArea{
		width: 75%;
		height: 100%;
		margin: 10px 0 0 0;
		float: right;
	}
	.description td{
		padding: 8px 6px 8px 0;
	}
	.social{
		padding: 15px 0;
	}
	.product_size{
		padding-top: 14px;
		border-top:1px solid #e8e8e8;
		padding: 7px 0 5px 5px;
		vertical-align: top;
		background: white;
		width: 70px;
	}
	.product_sizeSelect{
		padding: 4px 10px 4px 0;
		border-top:1px solid #e8e8e8;
	}
	.buttons{
		padding: 15px 0 10px 0;
	}
	.btn_cart{
		width: 99.7%;
		height: 50px;
		color: black;
		border: 1px solid black;
		margin-bottom: 3px;
		display: block;
	}
	.cont{
		width: 100%;
		margin: 0 auto;
		padding: 100px 0 50px 0;
		line-height: 18px;
	}
	#prdInfo{
		width: 100%;
		height: 780px;
	}
	.contInfo1{
		width: 100%;
		margin: 100px 0 0 0;
		padding: 30px 0 30px 30px;
		line-height: 18px;
		border-top: 1px solid #e3e3e3;
		border-bottom: 1px solid #e3e3e3;
	}
	.h5{
		margin: 0 0 20px;
	}
	.contInfo2{
		width: 100%;
		padding: 30px 0 30px 30px;
		line-height: 18px;
		border-bottom: 1px solid #e3e3e3;
	}
	.contInfo3{
		width: 100%;
		margin: 0 0 100px 0;
		padding: 30px 0 30px 30px;
		line-height: 18px;
		border-bottom: 1px solid #e3e3e3;
	}
	#prdReview{
		height: 211px;
	}
	.product_board{
		padding: 27px 0 46px;
		line-height: 18px;
		clear: both;
	}
	.board_review{
		margin: 0 auto;
		width: 200px;
		height: 30px;
		line-height: 30px;
		font-size: 12px;
		text-align: center;
		border-top: 1px solid black;
		border-right: 1px solid black;
		border-bottom: 2px solid black;
		border-left: 1px solid black;
	}
	.review_data{
		width:50%;
		margin: 15px auto 0;
		border: 1px solid white;
		font-weight: bold;
		line-height: 39px;
		text-align: center;
		background: black;
	}
</style>
</head>
<body>
<c:import url="./temp/h.jsp"></c:import>
	<div id="sub_container">
		<div id="sub_contents">
			<div class="titleArea2">
				<h3>신상품</h3>
			</div>
			<div class="product_main">
				<div class="detailArea">
					<!-- 메인이미지 -->
					<div class="main_img">
						<div class="keyImg">
							<div class="thumbnail">
								<img alt="Middle biue wide fit" src="./images/productImg/jin1.jpg">
							</div>
						</div>
						<div class="sub_img">
							<ul style="padding: 0px;">
								<li class="record">
									<img class="ThumdImage" src="./images/productImg/sub_jin1.jpg">
								</li>
							</ul>				
						</div>
					</div>
					<div class="infoArea">
						<div class="icon" > </div>
						<h3>
							#Middle blue wide fit
						</h3>
						<div class="description">
							<table>
								<caption> </caption>
								<tbody>
									<tr>
										<td>
											<span>
												<strong>113,000원</strong>
												<!-- 가격을 넣어야함 -->
											</span>
										</td>
									</tr>
									<tr>
										<td>
											<span style="font-size: 12px; color:#555555;">
												면 100% (11.5oz) 원단으로 루즈하게 떨어지는 라인을 만들고 편안한 착용감과....
											</span>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<table>
							<tbody>
								<tr>
									<td class="social">
										<a href="https://www.facebook.com/fatalism23/">
											<img src="./images/productImg/facebook.jpg">
										</a>
										<a href="https://twitter.com/intent/tweetstatu">
											<img src="./images/productImg/twitter.jpg">
										</a>
									</td>
								</tr>
							</tbody>
						</table>
						<table>
							<tbody>
								<tr>
									<th class="product_size" scope="row">Size</th>
									<td class="product_sizeSelect">
										<select>
											<option value="*">- [필수] 옵션을 선택해 주세여 -</option>
											<option value="**">---------------------</option>
											<option value="S">S</option>
											<option value="M">M</option>
											<option value="L">L</option>
										</select>
									</td>
								</tr>
							</tbody>
						</table>
						<div class="buttons">
							<div>
							<c:import url="./"></c:import>
								<a class="btn_cart" id="btn12">
									BUY IT NOW
								</a>
								<a class="btn_cart">
									ADD TO CART
								</a>
							</div>
						</div>			
					</div>
				</div>
				<div>
					<div class="cont">
						<!-- 상품상세정보이미지넣는곳 -->
					</div>
					<div id="prdInfo">
						<div class="contInfo1">
							<h5 class="h5">PAYMENT</h5>
							고액결제의 경우 안전을 위해 카드사에서 확인전화를 드릴 수도있습니다. 확인과정에서 도난 카드의 사용이나 타인 명의의 주문등 정상적인 주문이 아니라고 판단될 경우 임의로 주문을 보류또는 취소할수있습니다.<br>
							<br>
							무통장 입금은 상품 구매 대금은 pc뱅킹, 인터넷뱅킹, 텔레뱅킹 혹은 가까운 은행에서 직접 입급하시면 됩니다.
						</div>
						<div class="contInfo2">
							<h5 class="h5">DELIVERY</h5>
							<ul>
								<li>배송 방법 : 택배</li>
								<li>배송 지역 : 전국지역</li>
								<li>배송 비용 : 무료</li>
								<li>배송 기간 : 2일~5일</li>
								<li>
									배송 안내 : - 산간벽지나 도서지방은 별도의 추가 금액을 지불하셔야 하는 경우가 있습니다. <br>
									고객님께서 주문하신 상품은 입금 확인후 배송해 드립니다. 다만 상품종류에 따라서 상품의 배송이 다소 지연될수있습니다.<br>
									<br>
								</li>
							</ul>
						</div>
						<div class="contInfo3">
							<h5 class="h5">RETURNS / EXCHANGE</h5>
							<b>교환 및 반품이 가능한 경우</b>
							<br>
							- 상품을 공급 받으신 날로부터 7일이내 단, 가전제품의
							<br>
							경우 포장을 개봉하였거나 포장이 훼손되어 상품가치가 상실된 경우에는 교환/반품이 불가능합니다.
							<br>
							<b>교환 및 반품이 불가능한 경우 </b>
							<br>
							- 고객님의 책임 있는 사유로 상품등이 멸실 또느 훼손된 경우. 단, 상품의 내용을 확인하기 위하여
							<br>
							포장 등을 훼손한 경우는 제외
							<br>
							- 포장을 개봉하였거나 포장이 훼손되어 상품가치가 상실된 경우....
						</div>
					</div>
					<div id="prdReview">
						<div class="product_board">
							<h3 class="board_review">REVIEW</h3>
							<a href="${pageContext.request.contextPath }/review/reviewList.do">
								<p class="review_data" style="color: white;">리뷰보러가기</p>
							</a>
						</div>
					</div>
					<div class="product_board">
							<h3 class="board_review">Q&A</h3>
							<a href="${pageContext.request.contextPath }/qna/qnaList.do">
								<p class="review_data" style="color: white;">Q&A로가기</p>
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
<c:import url="./temp/footer.jsp"></c:import>
</body>
</html>