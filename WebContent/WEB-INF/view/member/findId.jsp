<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../../../temp/b.jsp"></c:import>
<style type="text/css">
	.name{
		width: 140px;
		font-weight: normal;
		display: inline-block;
		padding: 5px;
		margin: 0 0 5px;
		font-size: 11px;
	}
	
</style>
<script type="text/javascript">
	$(function() {
		$(".viewId").click(function() {
			 if(document.frm.name.value==""){
				alert("이름을 입력하세요");
				document.frm.name.focus();
			}else if(document.frm.email.value==""){
				alert("이메일을 입력하세요");
				document.frm.email.focus();
			}else if(document.frm.email2.value==""){
				alert("이메일을 입력하세요");
				document.frm.email2.focus();
			}else{
				$('.findId').submit();								
			}
		});
		
		$(".viewId2").click(function() {
			 if(document.frm2.name.value==""){
				alert("이름을 입력하세요");
				document.frm2.name.focus();
			}else if(document.frm2.phone.value==""){
				alert("핸드폰번호를 입력하세요");
				document.frm2.phone.focus();
			}else if(document.frm2.phone2.value==""){
				alert("핸드폰번호를 입력하세요");
				document.frm2.phone2.focus();	
			}else{
				$('.findId2').submit();								
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
				<h2>FIND ID</h2>
				<ul>
					<li>이메일찾기와 휴대폰번호으로 찾을수있습니다.</li>
					
				</ul>
			</div>
			<form action="./findId.do" method="post" class="findId" name="frm">
			<div class="login">
				<div class="findId2">
					<fieldset>
						<h6 style="font-weight: bold;">이메일로 찾기</h6> 
						<p>
							<strong class="name"> > 이름</strong>
							<input type="text" size="28" name="name">
						</p>
						<p>
							<strong class="name"> > 이메일로찾기</strong>
							<input type="text" size="10" name="email"> @ <input type="text" size="10" name="email2">
						</p>
						
						<div class="join_button">
							<a href="#" class="viewId">
								<img alt="확인" src="../images/member/btn_submit.png">
							</a>
						</div>
					</fieldset>
				</div>
			</div>
			</form>
			
			
			<form action="./findId2.do" method="post" class="findId2" name="frm2">
			<div class="login">
				<div class="findId">
					<fieldset>
						<h6 style="font-weight: bold;">핸드폰으로 찾기</h6> 
						<p>
							<strong class="name"> > 이름</strong>
							<input type="text" size="28" name="name">
						</p>
						<p>
							<strong class="name"> > 핸드폰으로 찾기</strong>
							<select name="phone">
							<option>010</option>
							<option>011</option>
							<option>016</option>
							<option>017</option>
							<option>018</option>
							<option>019</option>
							</select>
							-
							<input type="text" maxlength="8" size="19" name="phone2" placeholder=" - 는 생략해주세요">
						</p>
						
						<div class="join_button">
							<a href="#" class="viewId2">
								<img alt="확인" src="../images/member/btn_submit.png">
							</a>
						</div>
					</fieldset>
				</div>
			</div>
			</form>
			
		</div>
	</div>
<c:import url="../../../temp/footer.jsp"></c:import>
</body>
</html>