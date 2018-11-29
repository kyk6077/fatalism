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
	#delete{
		position: absolute;
		right: 0;
	}
	#btn_delete{
		width:140px;
		height:51px;
		background: url('../images/member/btn_delete.png');
		background-repeat:no-repeat;
		margin: auto;
		display: inline-block;
	}
	#btn_update{
		width:140px;
		height:51px;
		background: url('../images/member/btn_update.png');
		background-repeat:no-repeat;
		margin: auto;
		display: inline-block;
	}
</style>
<script type="text/javascript">
	$(function() {
		
		$("#btn_update").click(function() {
			if(document.frm.id.value==""){
				alert("아이디를 입력하세요");
				document.frm.id.focus();	
			}else if(document.frm.name.value==""){
				alert("이름를 입력하세요");
				document.frm.name.focus();
			/* }else if(document.frm.pw_1.value==""){
				alert("비밀번호를 입력하세요");
				document.frm.pw_1.focus();
			}else if(document.frm.pw.value==""){
				alert("비밀번호확인를 입력하세요"); */
				document.frm.pw.focus();
			}else if(document.frm.num_address.value==""){
				alert("우편주소를 입력하세요");
				document.frm.num_address.focus();
			}else if(document.frm.phone2.value==""){
				alert("핸드폰번호를 입력하세요");
				document.frm.phone2.focus();
			}else if(document.frm.email2.value==""){
				alert("이메일을 입력하세요");
				document.frm.email2.focus();
			}if($("#pw").val() != $("#pw2").val()){		
				alert("비밀번호가일치하지않습니다");
				document.frm.pw.focus();	
			}else{
				$('#update_form').submit();
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
		$("#btn_delete").click(function() {
			var d = confirm("탈퇴하면 적립금이 삭제됩니다.\n정말로 탈퇴 하시겠습니까?");
			if(d==true){
				location.href="./memberDelete.do";
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
		<form name="frm" action="./memberUpdate.do" method="post" id="update_form">
		<h3>기본정보</h3>
		<input type="hidden" value="f" name="checkid" id="checkid">
			<table class="table table-bordered">
				
				<tr>
				<th >아이디</th>
				<td><input type="text" id="id" name="id" value="${member.id }" readonly="readonly">
				<span id="id_1"></span>
				(영문소문자/숫자 4~16자)
				</td>
				</tr>
				<tr>
				<th >비밀번호</th>
				<td><input type="password" id="pw" name="pw_1">
				(영문 대문자/숫자/특수문자 중 2가지 이상 조합, 8자~16자)
				</td>
				</tr>
				<tr>
				<th>비밀번호 확인</th>
				<td>
					<input type="password" id="pw2" name="pw">
					<font name = "check" size="2" color="black"></font>
				</td>
				</tr>
				<tr>
				<th>이름</th>
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
				<tr>
   				<th> 이메일 </th>
   				<td>
    			<input type = "text" name="email" value="${member.email }"> @ <input type = "text" name="email2" value="${member.email2 }"> &nbsp;&nbsp; 
    			<select>
     				<option> 직접입력 </option>
     				<option> naver.com </option>
     				<option> daum.net </option>
     				<option> nate.com </option>
    			</select>
   				</td>
  				</tr>
			</table>
			<div class="join_button">
				<button id="btn_update" type="button"></button>
				<a href="${pageContext.request.contextPath }/index.jsp" id="btn2"></a>
				<span id="delete">
				<a id="btn_delete" href="#"></a>
				</span>
				
			</div>
						
			</form>
				
		</div>
	</div>



<c:import url="../../../temp/footer.jsp"></c:import>
</body>
</html>