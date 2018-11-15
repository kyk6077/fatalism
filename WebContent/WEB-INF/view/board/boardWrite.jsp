<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://cdn.ckeditor.com/4.11.1/standard/ckeditor.js"></script>
<!-- ckeditor -->
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../../../temp/b.jsp"/>
<script type="text/javascript">
	$(function(){
		CKEDITOR.replace('contents');
		
		$('#register_btn').on('click',function(){
			var checked_radio = $('input:radio[name=agree_radio]:checked').val();
			if(checked_radio == 'y'){
				$('#boardWriteForm').submit();
			}else{
				alert("이용약관에 동의해주시기 바랍니다.");
			}
		});
		
		
		
	});
</script>
<style type="text/css">
/* body 시작 */
	#page_title{
		text-align: center;
	}
	.write{
		
	}
	.write_table{
		border: 1px solid #EAEAEA;
		width:1170px;
	}
	.write_table td{
		padding:10px;
		border: 1px solid #EAEAEA;
	}
	.write_table th{
		padding:12px;
		border: 1px solid #EAEAEA;
		width:150px;
		background: #F6F6F6;
	}
	#contents_ck{
		padding: 0px;
	}
	.row_btn{
		margin-top: 20px;
	}
	.row_btn2{
		float: right;
	}
	
/* body 끝 */
	
</style>

</head>
<body>
<c:import url="../../../temp/h.jsp"/>
	<div id="sub_container">
		<div id="sub_contents">
		<h3 id="page_title">board</h3>
			<div class="write">
				<form action="./${board}Write.do" method="post" id="boardWriteForm">
					<table class="write_table">
						<tr>
							<th>제목</th>
							<td><input type="text" id="subject" name="subject" size=50></td>
						</tr>
						
						<tr>
							<th>작성자</th>
							<td><input type="text" id="writer" name="writer" size=15></td>
						</tr>
						<tr>
							<td colspan="2" id="contents_ck"><textarea rows="50" cols="40" class="form-control" id="contents" name="contents">
							</textarea></td>
						</tr>
						<tr>
							<th>첨부파일</th>
							<td><button>파일선택</button>선택된 파일 없음</td>
						</tr>
						<tr>
							<th>비밀번호</th>
							<td><input type="text" id="board_pw" name="board_pw" size=10></td>
						</tr>
		
						<c:if test="${board!='qna'}">
						<tr>
							<th>비밀글설정</th>
							<td>
							<input type="radio" checked="checked" name="hide_radio" value="N">공개글 
							<input type="radio" name="hide_radio" value="S">비밀글</td>
						</tr>
						</c:if>
						
						<tr>
							<th>개인정보 수집 및 이용 동의</th>
							<td><textarea cols="70" rows="8" readonly="readonly">■ 개인정보의 수집·이용 목적서비스 제공 및 계약의 이행, 구매 및 대금결제, 물품배송 또는 청구지 발송, 회원관리 등을 위한 목적 ■ 수집하려는 개인정보의 항목이름, 주소, 연락처 등 ■ 개인정보의 보유 및 이용 기간회사는 개인정보 수집 및 이용목적이 달성된 후에는 예외없이 해당정보를 파기합니다.</textarea><br>
							개인정보 수집 및 이용에 동의하십니까? <input type="radio" class="agree_radio" name="agree_radio" value="y">동의함 <input type="radio" class="agree_radio" name="agree_radio" value="n">동의 안함 </td>
						</tr>
					</table>
					
					<div class="row_btn">
						<a href="#"><img alt="1" src="../images/write_img/btn_list.png"></a>
						<div class="row_btn2">
							<a href="#" id="register_btn"><img alt="1" src="../images/write_img/btn_register.png"></a>
							<a href="#" ><img alt="1" src="../images/write_img/btn_cancel.png"></a>
<!-- 					<input type="submit" value="등록"> -->
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<c:import url="../../../temp/footer.jsp"/>
</body>
</html>