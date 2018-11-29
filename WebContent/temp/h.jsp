<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="left">
	<div id="logo">
		<a href="${pageContext.request.contextPath }/index.jsp"> <img
			alt=""
			src="${pageContext.request.contextPath }/images/member/logo.png"
			id="logo_images">
		</a>
	</div>
	<!-- 로그인메뉴 -->
	<div id="login_menu">
		<ul id="login_menu_1">
			<!-- 로그인후 -->
			<c:choose>
				<c:when test="${not empty member}">
					<li id="login_menu_2"><a style="color: #000"
						href="${pageContext.request.contextPath }/member/memberLogout.do">로그아웃</a>
						<span>I</span> <a style="color: #000"
						href="${pageContext.request.contextPath }/member/memberUpdate.do">회원정보수정</a>
						<span>I</span> <a style="color: #000"
						href="${pageContext.request.contextPath }/myPage.jsp">마이페이지</a></li>
					<li id="login_menu_2"><a style="color: #000"
						href="${pageContext.request.contextPath }/cart/cartList.do">장바구니</a>
						<span>I</span> <a style="color: #000" href="">주문내역</a>
				</c:when>
				<c:otherwise>
					<li id="login_menu_2">
						<!-- 로그인전 --> <a style="color: #000"
						href="${pageContext.request.contextPath }/member/memberLogin.do">로그인</a>
						<span>I</span> <a style="color: #000"
						href="${pageContext.request.contextPath }/member/memberJoin.do">회원가입</a>
						<span>I</span> <a style="color: #000"
						href="${pageContext.request.contextPath }/member/memberLogin.do">마이페이지</a>
					</li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
	<!-- 카테고리 -->
	<div id="abox">
		<dl class="abox_dl">
			<dt class="abox_dt">스토어</dt>
			<dd>
				<a href="${pageContext.request.contextPath}/product/productList.do?type=NewProduct">신상품</a>
			</dd>

			<dd>
				<a href="${pageContext.request.contextPath}/product/productList.do?type=FATALISM">FATALISM</a>
			</dd>

			<dd>
				<a href="${pageContext.request.contextPath}/product/productList.do?type=GARMENTS LINE">GARMENTS LINE</a>
			</dd>

			<dd>
				<a href="${pageContext.request.contextPath}/product/productList.do?type=27y">27y</a>
			</dd>

			<dd>
				<a href="${pageContext.request.contextPath}/product/productList.do">품절</a>
			</dd>
		</dl>
		<dl class="abox_dl">
			<dt style="font-variant: 600;" id="abox_dt">
				<a href="${pageContext.request.contextPath }/sub_menu/about.jsp">ABOUT US</a>
			</dt>
		</dl>
		<dl class="abox_dl">
			<dt style="font-variant: 600;" id="abox_dt">
				<a>오프라인 매장</a>
			</dt>
		</dl>
		<dl class="abox_dl">
			<dt style="font-variant: 600;" id="abox_dt">
				<a>LOOKBOOK</a>
			</dt>
		</dl>
		<dl class="abox_dl">
			<dt style="font-variant: 600;" id="abox_dt">
				<a href="${pageContext.request.contextPath }/sub_menu/sponse.jsp">협 찬</a>
			</dt>
		</dl>
		<dl class="abox_dl">
			<dt class="abox_dt">COMMUNITY</dt>
			<dd>
				<a href="${pageContext.request.contextPath }/notice/noticeList.do">NOTICE</a>
			</dd>
			<dd>
				<a href="${pageContext.request.contextPath }/qna/qnaList.do">Q&A</a>
			</dd>
			<dd>
				<a href="${pageContext.request.contextPath }/review/reviewList.do">REVIEW</a>
			</dd>
		</dl>
	</div>

	<div class="box1">
		<span id="aa">COMPANY INFO</span>
		<p id="aa2">CUSTOMER SERVICE<br>070-4146-6369<br>09:30 AM - 17:00 PM
			(점심시간 : 12:00 - 13:00)<br> WEEKEND/HOLIDAY OFF<br><br>BANK ACCOUNT<br>우리은행
			1005-703-486615<br> 예금주 (주)퍼스트매니지먼트<br><br>COMPANY: (주)퍼스트매니지먼트 OWNER: 한창호<br> TEL:
			070-4146-6369 E-MAIL: fatalismofficial@naver.com<br>ADDRESS: 경기도 의정부시
			민락동 732-10번지 5층 페이탈리즘<br> BUSINESS LICENSE: 487-87-01003<br>MAIL-ORDER
			LICENSE: 제 2018-의정부송산-0304호<br>COPYRIGHT © FATALISM ALL RIGHTS RESERVED.</p>
	</div>
	<!-- 아이콘 -->
	<div id="sns">
		<a href="https://www.instagram.com/fatalism_official/" target="_blank">
			<img
			src="${pageContext.request.contextPath }/images/member/instagram.png">
		</a> <span>I</span> <a href="https://www.facebook.com/fatalism23/"
			target="_blank"> <img
			src="${pageContext.request.contextPath }/images/member/facebook.png">
		</a> <span>I</span> <a href="https://pf.kakao.com/_jxhVRj" target="_blank">
			<img
			src="${pageContext.request.contextPath }/images/member/kakaotalk.png">
		</a>
	</div>
</div>
<!-- left의 끝 -->
