<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../../../temp/b.jsp"></c:import>

<script type="text/javascript">
	$(function() {
		 
	$("#id").blur(function() {
			var id = $(this).val();
			$.ajax({
				url : "../member/memberCheckId.do",
				type : "POST",
				data : {
					id : id
				},
				success : function(data) {
					data=data.trim();
					if (data=='2') {
						$("#id_1").html( id+"는 사용가능한 아이디입니다");
					} else {
						$("#id_1").html( id+"는 사용불가능한 아이디입니다");
						$("#id").val('');
						document.frm.id.focus();
					}
				},
				 error:function(){
					$("#id").val('');
					alert("아이디입력");
				} 
			});
		});

		$("#btn").click(function() {
			var isSeasonCHK = $("input:checkbox[name='SEASON[]']").is(":checked");
			if(document.frm.id.value==""){
				alert("아이디를 입력하세요");
				document.frm.id.focus();
			}else if(document.frm.pw_1.value==""){
				alert("비밀번호를 입력하세요");
				document.frm.pw_1.focus();
			/* }else if(document.frm.pw.value==""){
				alert("비밀번호확인를 입력하세요");
				document.frm.pw.focus();	 */
			}else if(document.frm.name.value==""){
				alert("이름를 입력하세요");
				document.frm.name.focus();
			}else if(document.frm.num_address.value==""){
				alert("우편주소를 입력하세요");
				document.frm.num_address.focus();
			}else if(document.frm.phone2.value==""){
				alert("핸드폰번호를 입력하세요");
				document.frm.phone2.focus();
			}else if(document.frm.email.value==""){
				alert("이메일을 입력하세요");
				document.frm.email.focus();
			}if (!isSeasonCHK) {
					alert("[필수]약관에 동의하세요");
					return false;
			}if($("#pw").val() != $("#pw2").val()){		
				alert("비밀번호가일치하지않습니다");
				document.frm.pw.focus();
			}else{
				$('#join_form').submit();
			}
			
			
			});
		
		  $('#user_pass').blur(function() {
			$('font[name=check]').text('');
		});

		$('#pw2').blur(function() {
			if ($('#pw').val() != $('#pw2').val()) {
				$('font[name=check]').text('');
				$('font[name=check]').html("비밀번호가 일치하지 않습니다.");
			} else {
				$('font[name=check]').text('');
				$('font[name=check]').html("비밀번호가 일치합니다");
			} 
		});  
	});
</script>
</head>
<body>
<c:import url="../../../temp/h.jsp"></c:import>
	<div id="sub_container">
		<div id="sub_contents">
		<div class="titleArea">
		<h2>JOIN</h2>
		</div>
		<form name="frm" action="./memberJoin.do" id="join_form" method="post">
		<h3 class="h3">기본정보</h3>
		<p style="text-align: right;font-size: 11.4px;color: "><img src="../images/member/required.png"> 필수입력사항</p>
		<input type="hidden" value="f" name="checkid" id="checkid">
			<table class="table table-bordered">
				
				<tr>
				<th>아이디 <img src="../images/member/required.png"></th>
				<td><input type="text" id="id" name="id">
				<span id="id_1"> </span>
				(영문소문자/숫자 4~16자)
				</td>
				</tr>
				<tr>
				<th >비밀번호 <img src="../images/member/required.png"></th>
				<td><input type="password" id="pw" name="pw_1">
				(영문 대문자/숫자/특수문자 중 2가지 이상 조합, 8자~16자)
				</td>
				</tr>
				<tr>
				<th>비밀번호 확인 <img src="../images/member/required.png"></th>
				<td>
					<input type="password" id="pw2" name="pw">
					<font name = "check" size="2" color="black"></font>
				</td>
				</tr>
				<tr>
				<th>이름 <img src="../images/member/required.png"></th>
				<td><input type="text" name="name"></td>
				</tr>
				<c:import url="../../address/addressAPI.jsp"></c:import>
				<tr>
				<th>주소 <img src="../images/member/required.png"></th>
				<td><input type="text" size="4" style="margin-bottom: 5px" name="num_address"id="num_address"> <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호"><br>
					<input type="text" size="30" style="margin-bottom: 5px" name="main_address"id="main_address"> 기본주소<br>
					<input type="text" size="30" name="sub_address"id="sub_address"> 나머지주소
				</td>
				</tr>
				<tr>
				<th>휴대전화 <img src="../images/member/required.png"></th>
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
				<input type="text" maxlength="8" size="12" name="phone2" placeholder=" - 는 생략해주세요">
				
				</td>
				</tr>
				<tr>
   				<th> 이메일 <img src="../images/member/required.png"></th>
   				<td>
    			<input type = "text" name="email"> @ <input type = "text" name="email2"> &nbsp;&nbsp; 
    			<select>
     				<option> 직접입력 </option>
     				<option> naver.com </option>
     				<option> daum.net </option>
     				<option> nate.com </option>
    			</select>
   				</td>
  				</tr>
			</table>
			<h3 class="h3">약관동의</h3>
			
			<div class="all_consent">
				
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
						이약관에 동의하십니까? <input type="checkbox" id="c1" name="SEASON[]" value="1"> 동의함
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
						이약관에 동의하십니까? <input type="checkbox" id="c2" name="SEASON[]" value="2"> 동의함
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
				<button type="button" id="btn"></button>
<!-- 				<button type="submit" id="btn"></button> -->
				 <!-- <button id="btn2" ></button> -->
				<a href="${pageContext.request.contextPath }/index.jsp" id="btn2"></a>
			</div>
						
			</form>
		</div>
	</div>
<c:import url="../../../temp/footer.jsp"></c:import>

</body>
</html>