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
	.sub_title{
		margin: 0 0 30px;
		text-align: center;
		font-size: 12px;
		font-weight: normal;
	}
	.findId {
		width: 500px;
		margin: 0 auto;
	}
	.memberInfo{
		overflow: hidden;
		border: 1px solid #d2d2d2;
		background: #f3f3f3;
		text-align: left;
	}
	.memberInfo_2{
		float: right;
		width: 318px;
		padding: 30px 0;
		font-size: 12px;
	}
	.copy{
		margin: 40px 0 20px;
		text-align: center;
		font-size: 12px;
	}
</style>
<script type="text/javascript">
	
</script>
</head>
<body>
<c:import url="../../../temp/h.jsp"></c:import>
	<div id="sub_container">
		<div id="sub_contents">
			<div class="titleArea">
				<h2>FIND ID</h2>
			</div>
			<div class="login">
				<div class="findId">
					
						<p class="sub_title">고객님 아이디 찾기가 완료 되었습니다</p>
						<div class="memberInfo">
							<ul class="memberInfo_2">
								<li>이름 : 
									<strong><span>${findId.name }</span></strong>
								</li>
								<li>이메일 : 
									<strong>${findId.email} @ ${findId.email2}</strong>
								</li>
								<li>
									아이디 : 
									<span>${findId.id }</span>
								</li>
								<li>고객님 즐거운 쇼핑 하세요!</li>
							</ul>
						</div>
						<p class="copy">
							고객님의 아이디 찾기가 성곡적으로 이루어졌습니다. 항상 고객님의<br>
							즐겁고 편리한 쇼핑을 위해 최선의 노력을 다하는 쇼핑몰이 되도록 하겠습니다.
						</p>		
						<div class="join_button">
							<a href="${pageContext.request.contextPath }/member/memberLogin.do">
								<img alt="" src="../images/member/btn_complete_login.png">
							</a>
							<a href="${pageContext.request.contextPath }/member/findPw.do">
								<img alt="" src="../images/member/btn_find_pw.png">
							</a>
						</div>
				</div>
			</div>
		</div>
	</div>	

<c:import url="../../../temp/footer.jsp"></c:import>
</body>
</html>