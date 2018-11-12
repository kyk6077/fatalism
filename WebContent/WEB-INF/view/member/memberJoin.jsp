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
	.container{
		margin: 70px 0 0 400px;
	}
	.contents{
		margin: 0 auto;
		
	}
	h2{
		
		text-align: center;/* 가운데 정렬 */
	}
	th{
		background-color: #fbfafa;
		font-weight: normal;
		font-size: 12px;
		padding: 11px 0 10px 18px;
	}
	br{
		padding: 5px;
	}
	h3{
		padding: 50px 0 10px;
		font-size: 13px;
		font-weight: bold;
	}
	.consent{
		padding:20px;
		height: 209px;
		width:100%;
		border-width: 1px;
		border-color: #e6e6e6;
		background-color: #efeff0;
	}
	.consent_2{
		overflow: auto;
		height: 110px;
		padding: 20px;
		border: 1px solid #d5d5d5;
		background: #fff;
	}
	.join_button{
		padding: 10px 0;
		text-align: center;
	}
	#footer{
		width: 100%;
		margin: 0 0;
		overflow: hidden;
		padding: 0 0 40px 0;
		background: black;
		
	}
	#btn{
		width:140px;
		height:50px;
		background: url('../images/join.png');
		background-repeat:no-repeat;
		margin: auto;
	}
	#btn2{
		width: 140px;
		height: 50px;
		background-image: url("../images/cancel.png");
	}
</style>
<script type="text/javascript">
	$(function() {
		
		$("#id").blur(function() {
			 $("#id_1").html("사용가능한 아이디입니다.");
		});
		/* $("#btn2").click(function() {
			location.href="../index.jsp";
		}); */
		
	});
</script>
</head>
<body>
<c:import url="../../../temp/h.jsp"></c:import>
	<div class="container">
		<div class="contents">
		<form name="frm" action="./memberJoin.do" method="post">
		<h2>JOIN</h2>
		<h3>기본정보</h3>
		<input type="hidden" value="f" name="checkid" id="checkid">
			<table class="table table-bordered">
				
				<tr>
				<th >아이디</th>
				<td><input type="text" id="id" name="id">
				<span id="id_1"></span>
				(영문소문자/숫자 4~16자)
				</td>
				</tr>
				<tr>
				<th >비밀번호</th>
				<td><input type="password" id="pw" name="pw">
				(영문 대문자/숫자/특수문자 중 2가지 이상 조합, 8자~16자)
				</td>
				</tr>
				<tr>
				<th>비밀번호 확인</th>
				<td><input type="password" id="pw2" name="pw2"></td>
				</tr>
				<tr>
				<th>이름</th>
				<td><input type="text"></td>
				</tr>
				<tr>
				<th>주소</th>
				<td><input type="text" size="4" style="margin-bottom: 5px"> <button>우편번호</button> <input type="checkbox"> 해외 거주자인 경우, 체크해 주세요.<br>
					<input type="text" size="30" style="margin-bottom: 5px"> 기본주소<br>
					<input type="text" size="30"> 나머지주소
				</td>
				</tr>
				<tr>
				<th>휴대전화</th>
				<td>
				<select >
					<option>010</option>
					<option>011</option>
					<option>016</option>
					<option>017</option>
					<option>018</option>
					<option>019</option>
				</select>
				-
				<input type="text" maxlength="4" size="4">
				-
				<input type="text" maxlength="4" size="4">
				</td>
				</tr>
				<tr>
   				<th> 이메일 </th>
   				<td>
    			<input type = "text"/> @ <input type = "text"/> &nbsp;&nbsp; 
    			<select>
     				<option> 직접입력 </option>
     				<option> naver.com </option>
     				<option> daum.net </option>
     				<option> nate.com </option>
    			</select>
   				</td>
  				</tr>
			</table>
			<h3>전체동의</h3>
			
			<div class="all_consent">
				<p>
				
				</p>	
			</div>
			
			<div class="consent">
				<h6 style="font-weight: bold;">[필수] 이용약관 동의</h6>
				<div class="consent_1">
					<div class="consent_2">
						<p>제1조(목적)
이 약관은 '(주)퍼스트매니지먼트' 회사(전자상거래 사업자)가 운영하는 '페이탈리즘' 사이버 몰(이하 “몰”이라 한다)에서 제공하는 인터넷 관련 서비스(이하 “서비스”라 한다)를 이용함에 있어 사이버 몰과 이용자의 권리,의무 및 책임사항을 규정함을 목적으로 합니다.</p>
						<p>※「PC통신, 무선 등을 이용하는 전자상거래에 대해서도 그 성질에 반하지 않는 한 이 약관을 준용합니다」</p>
						<p>제2조(정의)</p>
						<p>①“몰” 이란 회사가 재화 또는 용역(이하 “재화등”이라 함)을 이용자에게 제공하기 위하여 컴퓨터등 정보통신설비를 이용하여 재화등을 거래할 수 있도록 설정한 가상의 영업장을 말하며, 아울러 사이버몰을 운영하는 사업자의 의미로도 사용합니다.</p>
						<p>②“이용자”란 “몰”에 접속하여 이 약관에 따라 “몰”이 제공하는 서비스를 받는 회원 및 비회원을 말합니다.</p>
						<p>③ ‘회원’이라 함은 “몰”에 개인정보를 제공하여 회원등록을 한 자로서, “몰”의 정보를 지속적으로 제공받으며, “몰”이 제공하는 서비스를 계속적으로 이용할 수 있는 자를 말합니다.</p>
						<p>④ ‘비회원’이라 함은 회원에 가입하지 않고 “몰”이 제공하는 서비스를 이용하는 자를 말합니다.</p>
					</div>
						이약관에 동의하십니까? <input type="checkbox"> 동의함
				</div>
			</div>
			<div class="consent">
				<h6 style="font-weight: bold;">[필수] 이용약관 동의</h6>
				<div class="consent_1">
					<div class="consent_2">
						<p>제1조(목적)
이 약관은 '(주)퍼스트매니지먼트' 회사(전자상거래 사업자)가 운영하는 '페이탈리즘' 사이버 몰(이하 “몰”이라 한다)에서 제공하는 인터넷 관련 서비스(이하 “서비스”라 한다)를 이용함에 있어 사이버 몰과 이용자의 권리,의무 및 책임사항을 규정함을 목적으로 합니다.</p>
						<p>※「PC통신, 무선 등을 이용하는 전자상거래에 대해서도 그 성질에 반하지 않는 한 이 약관을 준용합니다」</p>
						<p>제2조(정의)</p>
						<p>①“몰” 이란 회사가 재화 또는 용역(이하 “재화등”이라 함)을 이용자에게 제공하기 위하여 컴퓨터등 정보통신설비를 이용하여 재화등을 거래할 수 있도록 설정한 가상의 영업장을 말하며, 아울러 사이버몰을 운영하는 사업자의 의미로도 사용합니다.</p>
						<p>②“이용자”란 “몰”에 접속하여 이 약관에 따라 “몰”이 제공하는 서비스를 받는 회원 및 비회원을 말합니다.</p>
						<p>③ ‘회원’이라 함은 “몰”에 개인정보를 제공하여 회원등록을 한 자로서, “몰”의 정보를 지속적으로 제공받으며, “몰”이 제공하는 서비스를 계속적으로 이용할 수 있는 자를 말합니다.</p>
						<p>④ ‘비회원’이라 함은 회원에 가입하지 않고 “몰”이 제공하는 서비스를 이용하는 자를 말합니다.</p>
					</div>
						이약관에 동의하십니까? <input type="checkbox"> 동의함
				</div>
			</div>
			<div class="consent">
				<h6 style="font-weight: bold;">[선택] 이용약관 동의</h6>
				<div class="consent_1">
					<div class="consent_2">
						<p>제1조(목적)
이 약관은 '(주)퍼스트매니지먼트' 회사(전자상거래 사업자)가 운영하는 '페이탈리즘' 사이버 몰(이하 “몰”이라 한다)에서 제공하는 인터넷 관련 서비스(이하 “서비스”라 한다)를 이용함에 있어 사이버 몰과 이용자의 권리,의무 및 책임사항을 규정함을 목적으로 합니다.</p>
						<p>※「PC통신, 무선 등을 이용하는 전자상거래에 대해서도 그 성질에 반하지 않는 한 이 약관을 준용합니다」</p>
						<p>제2조(정의)</p>
						<p>①“몰” 이란 회사가 재화 또는 용역(이하 “재화등”이라 함)을 이용자에게 제공하기 위하여 컴퓨터등 정보통신설비를 이용하여 재화등을 거래할 수 있도록 설정한 가상의 영업장을 말하며, 아울러 사이버몰을 운영하는 사업자의 의미로도 사용합니다.</p>
						<p>②“이용자”란 “몰”에 접속하여 이 약관에 따라 “몰”이 제공하는 서비스를 받는 회원 및 비회원을 말합니다.</p>
						<p>③ ‘회원’이라 함은 “몰”에 개인정보를 제공하여 회원등록을 한 자로서, “몰”의 정보를 지속적으로 제공받으며, “몰”이 제공하는 서비스를 계속적으로 이용할 수 있는 자를 말합니다.</p>
						<p>④ ‘비회원’이라 함은 회원에 가입하지 않고 “몰”이 제공하는 서비스를 이용하는 자를 말합니다.</p>
					</div>
						이약관에 동의하십니까? <input type="checkbox"> 동의함
				</div>
			</div>
			<div class="join_button">
				<button type="submit" id="btn"></button>
				<!-- <button id="btn2" ></button> -->
				<%-- <a href="${pageContext.request.contextPath }/index.jsp"><img src="../images/cancel.png"></a> --%>
			</div>
						
			</form>
		</div>
	</div>
	<div id="footer">
		
	</div>


</body>
</html>