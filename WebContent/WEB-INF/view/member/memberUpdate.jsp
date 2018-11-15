<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../../../temp/b.jsp"></c:import>
</head>
<body>
<c:import url="../../../temp/h.jsp"></c:import>
	<div id="sub_container">
		<div id="sub_contents">
		<div class="titleArea">
		<h2>JOIN</h2>
		</div>
		<form name="frm" action="./memberUpdate.do" method="post">
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
				<td><input type="password" id="pw" >
				(영문 대문자/숫자/특수문자 중 2가지 이상 조합, 8자~16자)
				</td>
				</tr>
				<tr>
				<th>비밀번호 확인</th>
				<td><input type="password" id="pw2" name="pw"></td>
				</tr>
				<tr>
				<th>이름</th>
				<td><input type="text" name="name" value="${member.name }"></td>
				</tr>
				<tr>
				<th>주소</th>
				<td><input type="text" size="4" style="margin-bottom: 5px" name="num_address" value="${member.num_address }"> <button>우편번호</button> <input type="checkbox" name="c"> 해외 거주자인 경우, 체크해 주세요.<br>
					<input type="text" size="30" style="margin-bottom: 5px" name="main_address" value="${member.main_address }"> 기본주소<br>
					<input type="text" size="30" name="sub_address" value="${member.sub_address }"> 나머지주소
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
				<input type="text" maxlength="4" size="4" name="phone1">
				-
				<input type="text" maxlength="4" size="4" name="phone2">
				</td>
				</tr>
				<tr>
   				<th> 이메일 </th>
   				<td>
    			<input type = "text" name="email"> @ <input type = "text" name="email1"> &nbsp;&nbsp; 
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
				<button type="submit" id="btn"></button>
				 <!-- <button id="btn2" ></button> -->
				<a href="${pageContext.request.contextPath }/index.jsp" id="btn2"></a>
			</div>
						
			</form>
				
		</div>
	</div>



<c:import url="../../../temp/footer.jsp"></c:import>
</body>
</html>