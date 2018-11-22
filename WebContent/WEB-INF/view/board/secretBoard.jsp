<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="../../../temp/b.jsp" />
<script type="text/javascript">
	$(function(){
		$('#sc_submit').on('click',function(){
			$('#sc_form').submit();
			
		});
	});	
</script>
<style type="text/css">
	.sb_title{
		text-align: center;
	}
	.sc_formbody{
		margin-top: 35px;
	}
	.sc_btn{
		margin-top: 40px;
	}
	#sc_description{
		margin-top: 60px;
	}
</style>
<title>Insert title here</title>
</head>
<body>
	<c:import url="../../../temp/h.jsp" />
	<div id="sub_container">
		<div id="sub_contents">
			<div class="sb_title">
				<h3>Q/A</h3>
				<p id="sc_description">이글은 비밀글입니다.<strong> 비밀번호를 입력하여 주세요</strong><br>
				관리자는 확인버튼만 누르시면 됩니다.</p>
			<div class="sc_formbody">
				<form action="./${board}PwCheck.do" method="post" id="sc_form">
					<input type="hidden" name="num" value="${num}">
					> 비밀번호  <input type="password" name="pw">
					<div class="sc_btn">
						<a href="./${board}List.do"><img alt="" src="../images/write_img/btn_list.png"></a>
						<a id="sc_submit"><img alt="" src="../images/btn_okay.png"></a>
					</div>
				</form>
			</div>
			</div>
		</div>
	</div>
	<c:import url="../../../temp/footer.jsp" />
</body>
</html>