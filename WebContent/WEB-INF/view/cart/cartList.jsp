<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../../../temp/b.jsp"></c:import>
<style type="text/css">
	
	 .cart_menu{
	 	padding:0 0;
	 	height : 50px;
		margin: 0 0 20px;
		box-sizing: border-box;
		background: #f2f2f2;
		border: 1px solid #cbcdce;
	}
	.selected{
		float:left;
		border-right:1px solid #d6d4d4;
		height: 100%;
		width: 20%;
		text-align: center;
		background: white;
	}
	.sub_selected{
		line-height: 45px;
	}
	.cart_sub{
		width:100%;
		height:50px;
		margin :40px 0 0 ;
	}
	#ca{
		margin: 50px 0;
		text-align: center;
		font-weight: bold;
		color: #707070;
	}
	.btn_cart{
		margin: 10px 0 40px;
		padding: 10px 0;
		text-align: center;
		position: relative;
	}
	.order_ing{
		position: absolute;
		right: 0;
	}
	.sub_contents2{
		margin: 20px 0;
		border: 1px solid #d6d4d4;
		line-height: 18px;
		background: #fbfbfb;
	}
	.h6{
		font-weight: bold;
		padding: 0 0 0 10px;
		
	}
	.inner{
		padding: 0 9px 12px;
	}
	.ol{
		color: #3c3c5d;
		font-size: 11px;
	}
	.cartList_title{
		height: 38px; 
		margin: 20px 0 0;
		padding: 0 0 0 9px;
		border: 1px solid #d7d5d5;
		border-bottom: 0;
		line-height: 38px;
		background: #f6f6f6;
	}
	.cartList{
		border: 1px solid #d7d5d5;
		width: 100%;
	}
	.cartList th{
		height: 17px;
		padding: 11px 0 10px;
		border-left: 1px solid #dfdfdf;
		border-bottom: 1px solid #dfdfdf;
		vertical-align: middle;
		background: #fbfafa;
		text-align: center;
	}
	.cartList td{
		text-align: center;
		border-top: 1px solid #dfdfdf;
	}
	.cart_name{
		padding-left: 10px;
		text-align: left;
	}
	.cart_name a{
		color: black;
	}
	.cart_img{
		padding: 8px 0 7px;
	}
	.sum{
		font-size: 14px;
		font-weight: bold;
	}
	.sum2{
		padding: 15px 10px 17px;
		background: #fbfafa;
		font-size: 15px;
		font-weight: bold;
	}
	.cart_font{
		color: #707070;
		font-size: 11px;
	}
	.alldelete{
		margin: 0 0 40px;
		padding: 10px 0;
		text-align: right;
	}
	
</style>
<script type="text/javascript">
	 $(function() {
		 
		 var a = $('#allprice').val();
		 var s = parseInt(0);
		 for(var i=0;i<a;i++){
			 s += parseInt($('#totalprice'+(i+1)).text());
		 }
		 $('#all_price').text(s);
		 
		$('.cartList').on('click','.test',function(){
			
			var aaa=$(this).attr("title");
			var sum = $('#price'+aaa).text() * $('#product_count'+aaa).val();
			$('#totalprice'+aaa).text(sum);
		});
		
		$(".allCheck").click(function() {
			$('.ch').prop('checked', this.checked);
		});
		
	 }); 
</script>
</head>
<body>
<c:import url="../../../temp/h.jsp"></c:import>
	<div id="sub_container">
		<div id="sub_contents">
			<div class="titleArea">
				<h2>CART</h2>
			</div>
			<div>
				<div class="cart_sub">
					<ul class="cart_menu">
						<li class="selected">
							<a href="#" class="sub_selected">국내배송상품</a>
						</li>
					</ul>
					
				</div>
				<div>
					<div class="cartList_title">
						<h6 class="h6">일반상품</h6>
					</div>
					<table class="cartList">
						<colgroup>
							<col style="width:27px"/>
							<col style="width:92px"/>
							<col style="width:auto"/>
							<col style="width:98px"/>
							<col style="width:75px"/>
							<col style="width:98px"/>
							<col style="width:98px"/>
							<col style="width:85px"/>
							<col style="width:98px"/>
							<col style="width:110px"/>
						</colgroup>
						<thead>
							<tr>
								<th scope="col"><input type="checkbox" class="allCheck"></th>
								<th scope="col">이미지</th>
								<th scope="col">상품정보</th>
								<th scope="col">판매가</th>
								<th scope="col">수량</th>
								<th scope="col">적립금</th>
								<th scope="col">배송구분</th>
								<th scope="col">배송비</th>
								<th scope="col">합계</th>
								<th scope="col">선택</th>
							</tr>
						</thead>
						<c:forEach	items="${cart}" var="cDTO" varStatus="c">
						<tbody>
							<tr>
								<td><input type="checkbox" class="ch"></td>
								<td class=cart_img>
									<a>
										<img alt="" src="../images/member/test.jpg">
									</a>
								</td>
								<td class="cart_name">
									<a>
										<strong>${cDTO.name}</strong>
									</a>
									<ul>
										<li>${cDTO.bodysize }</li>
									</ul>
								</td>
								<td>
									<p id="price${c.count}" title="${c.count}" >${cDTO.price }</p>
								</td>
								<td>
									<input type="text" size="2" style="border-radius: 2px" value="1" id="product_count${c.count }"><br>
									<a class="test" title="${c.count}">
										<img alt="변경" src="../images/member/btn_quantity_modify.png">
									</a>
								</td>
								<td>
									<span class="cart_font"> 
										3000원
									</span>
								</td>
								<td class="cart_font">
									기본배송
								</td>
								<td class="cart_font">
									무료
								</td>
								<td>
								
									<p id="totalprice${c.count}" class="sum">${cDTO.price }</p>
								</td>
								<td>
									<a>
										<img alt="주문" src="../images/member/btn_order.png">
									</a>
									<a href="selectDelete.do?num=${cDTO.num }" class="delete_btn"<%--  title="${cDTO.num}" --%>>
										<img alt="삭제" src="../images/member/btn_order_delete.png">
									</a>
															
								</td>
							</tr>
						</tbody>
						<c:if test="${c.last}">
							<input type="hidden" value="${c.count}" id="allprice">
						</c:if>
						</c:forEach>
						<tfoot>
							<tr>
								<td colspan="10" class="sum2">
									총합계 : 
									<span id="all_price">
										
									</span>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
				<div class="alldelete">
					<strong>전체상품 : </strong>
					<a href="allDelete.do">
						<img alt="전체비우기" src="../images/member/btn_clear.png">
					</a>
				</div>
				
				<div class="btn_cart">
					<a href="./order.do">
						<img alt="전체상품결제" src="../images/member/btn_order_all.png">						
					</a>
					<a href="#">
						<img alt="선택상품결제" src="../images/member/btn_order_select.png">
					</a>
					<span class="order_ing">
						<a href="${pageContext.request.contextPath }/index.jsp">
							<img alt="쇼핑하기" src="../images/member/btn_order_ing.png">
						</a>
					</span>
				</div>
			</div>
			<div class="sub_contents2">
				<h6 class="h6">이용안내</h6>
				<hr style="margin: 0 0;">
				<div class="inner">
					<h6>장바구니 이용안내</h6>
					<ol class="ol">
						<li>선택하신 상품의 수량을 변경하시려면 수량변경후 [변경] 버튼을 누르시면 됩니다.</li>
						<li>[쇼핑계속하기] 버튼을 누르시면 쇼핑을 계속 하실 수 있습니다.</li>
						<li>장바구니와 관심상품을 이용하여 원하시는 상품만 주문하거나 관심상품으로 등록하실 수 있습니다.</li>
						<li>파일첨부 옵션은 동일 상품을 장바구니에 추가할 경우 마지막에 업로드 한 파일로 교체 됩니다.</li>
					</ol>
					<h6>무이자할부 이용안내</h6>
					<ol class="ol">
						<li>상품별 무이자할부 해택을 받으시려면 무이자할부 상품만 선택하여 [주문하기] 버튼을 눌러 주문/결제 하시면 됩니다.</li>
						<li>[전체 상품 주문] 버튼을 누르시면 장바구니의 구분없이 선택된 모든 상품에 대한 주문/결제가 이루어집니다.</li>
						<li>단, 전체 상품을 주문/결제하실 경우, 상품별 무이자할부 해택을 받으실 수 없습니다.</li>
					</ol>
				</div>
			</div>
		</div>
	</div>

<c:import url="../../../temp/footer.jsp"></c:import>
</body>
</html>










