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
				$('#boardUpdateForm').submit();
		});
		
		
		
	});
</script>
<style type="text/css">
/* body 시작 */
	#page_title{
		text-align: center;
	}
	.write_table{
		border: 1px solid #EAEAEA;
		width: 100%;
		min-width: 800px;
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
				<form action="./${board}Update.do" method="post" id="boardUpdateForm">
					<input type="hidden" name="num" value="${boardDTO.num}">
					<table class="write_table">
						<tr>
							<th>제목</th>
							<td><input type="text" id="subject" name="subject" size=50 value="${boardDTO.subject}"></td>
						</tr>
						
						<tr>
							<th>작성자</th>
							<td><input type="text" id="writer" name="writer" size=15 disabled="disabled" value="${boardDTO.writer}"></td>
						</tr>
						<tr>
							<td colspan="2" id="contents_ck"><textarea rows="50" cols="40" class="form-control" id="contents" name="contents">
							${boardDTO.contents}</textarea></td>
						</tr>
						<tr>
							<th>첨부파일</th>
							<td><button>파일선택</button>선택된 파일 없음</td>
						</tr>
					</table>
					
					<div class="row_btn">
						<a href="./${board}List.do"><img alt="1" src="../images/write_img/btn_list.png"></a>
						<div class="row_btn2">
							<a href="#" id="register_btn"><img alt="1" src="../images/write_img/btn_register.png"></a>
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