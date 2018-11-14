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
	.login{
		margin: 0 0 3px 0 ;
		padding: 30px 0 ;
	}	
	fieldset{
		position:relative;
		width: 380px;
		margin: 0 auto;
	}
	ul{
		padding: 8px 5px 5px;
		margin: 0;
		border: 0;
	}
	#btn_login{
		width: 100%;
		height: 40px;
		background-color: black;
		font-size: 15px;
		font-weight: bold;
		color: white;
	}
	
</style>
</head>
<body>
<c:import url="../../../temp/h.jsp"></c:import>
	<div id="sub_container">
		<div id = "sub_contents">
			<div class="titleArea">
				<h2>LOGIN</h2>
			</div>
			<form action="./memberLogin.do" method="post">
				<div class="login">
					<fieldset>
						
						<label title="아이디">
							<input placeholder="아이디" type="text" size="43" id="id" name="id">
						</label>
						<label title="비밀번호">
							<input type="password" placeholder="비밀번호" size="43" id="pw" name="pw">
						</label>
						<button type="submit" id="btn_login">LOGIN</button>
						<p>
							<input type="checkbox">
							<label>아이디저장</label>
							<img alt="보안접속" src="../images/ico_access.gif">
							보안 접속                    
						</p>
						<%-- <a href="${pageContext.request.contextPath }/index.jsp"id="login_btn"><img src="../images/btn_login.png"></a> 버튼--%>
						<ul>
							<li>
								<a href="${pageContext.request.contextPath }/index.jsp">
									<img alt="" src="../images/btn_findid.png">
								</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath }/index.jsp">
									<img alt="" src="../images/btn_findpw.png">
								</a>
							</li>
						</ul>
						<hr>
						<p>
							회원가입을 하시면 다양하고 특별한<br>
							혜택이 준비되어 있습니다!!
						</p>
						<a href="./memberJoin.do">
								<img alt="" src="../images/btn_join.png">
						</a>
						
						
					</fieldset>
				</div>
			</form>
		</div>	
	</div>

<c:import url="../../../temp/footer.jsp"></c:import>		
</body>
</html>