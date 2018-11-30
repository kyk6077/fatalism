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
	.patArea{
		overflow: hidden;
		position: relative;
		/* padding: 0 241px 0 0; */
		border: 1px solid #777;
		color: black;
		line-height: 1.5;
	}
	.payment{
		float: left;
		width: 100%;
	}
	.method{
		padding: 17px 20px 15px;
		font-weight: bold;
		border-bottom: 3px double #dedede;
	}
	.method span{
		margin: 0 15px 5px 0;
	}
	.method label{
		font-size: 12px;
	}
	.total_h{
		text-align: right;
	}
	.btn_payment{
		text-align: center;
		margin: 16px 0 10px;
	}
	.ex{
		padding: 10px 20px;
	}
	.ex_card{
		margin: 0 0 0 130px;
	}
</style>
<c:import url="../../payment/test.jsp"></c:import>
<script type="text/javascript">
	 $(function() {
		 
		/*  $('#test_btn').on('click',function(){
			alert($('input:radio[name=paymethod]:checked').val()); 
		 }) */
		 
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
				<h2>ORDER</h2>
			</div>
			<div>
				<div>
					<div class="cartList_title">
						<h6 class="h6">국내배송상품 주문내역</h6>
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
										<strong>${productDTO.name}</strong>
									</a>
									<ul>
										<li>${productDTO.bodysize }</li>
									</ul>
								</td>
								<td>
									<p id="price${c.count}" title="${c.count}" >${productDTO.price }</p>
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
								
									<p id="totalprice${c.count}" class="sum">${productDTO.price }</p>
								</td>
								<td>
									<a href="selectDelete.do?num=${productDTO.num }" class="delete_btn" title="${productDTO.num}">
										<img alt="삭제" src="../images/member/btn_order_delete.png">
									</a>
															
								</td>
							</tr>
						</tbody>
						<c:if test="${c.last}">
							<input type="hidden" value="${c.count}" id="allprice">
						</c:if>
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
					<hr>
				<table class="table table-bordered">
				<tr>	
				<th>받으시는분</th>
				<td><input type="text" name="name" value="${member.name }"></td>
				</tr>
				<c:import url="../../address/addressAPI.jsp"></c:import>
				<tr>
				<th>주소</th>
				<td><input type="text" size="4" style="margin-bottom: 5px" name="num_address" value="${member.num_address }"id="num_address"> <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호"><br>
					<input type="text" size="30" style="margin-bottom: 5px" name="main_address" value="${member.main_address }"id="main_address"> 기본주소<br>
					<input type="text" size="30" name="sub_address" value="${member.sub_address }"id="sub_address"> 나머지주소
				</td>
				</tr>
				<tr>
				<th>휴대전화</th>
				<td>
				<select name="phone">
					<option>010</option>
					<option>011</option>
					<option>016</option>
					<option>017</option>
					<option>018</option>
					<option>019</option>
				</select>
				-
				<input type="text" maxlength="8" size="8" name="phone2" value="${member.phone2 }">
				</td>
				</tr>
			</table>
				</div>
			</div>
			<hr>
			<div class="patArea">
				<div class="payment">
					<div class="method">
						<span>
							<input type="radio" checked="checked" name="paymethod" class="pg_radio" value="card">
							<label for="card">카드 결제</label>
						</span>
						<span>
							<input type="radio" name="paymethod" class="pg_radio" value="bank">
							<label for="bank">실시간계좌입금</label>
						</span>
						<span>
							<input type="radio" name="paymethod" class="pg_radio" value="phone">
							<label for="phone">휴대폰 결제</label>
						</span>
						<span>
							<input type="radio" name="paymethod" class="pg_radio" value="vbank">
							<label for="vbank">무통장 입금</label>
						</span>
					</div>
						
					<div class="ex">
						<div class="ex_card">
														
						</div>
					</div>
					
					<hr>
					<div class="total_h">
						<h5 style="margin: 17px 10px 0 0;">
							<span>카드</span>
							<span>최종결제 금액</span>
						</h5>
						<p style="text-align: right;clear: none;border: 0px;float: none; margin: 20px 10px 0 0;">최종금액넣는곳
						<span>원</span>
						</p>
						<div class="btn_payment">
						
							<a href="#" id="btn12">
								<img alt="결제하기" src="../images/member/btn_payment.png">
							</a>
						</div>
					</div>
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

