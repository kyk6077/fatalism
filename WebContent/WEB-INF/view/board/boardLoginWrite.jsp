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
	});
</script>
<style type="text/css">
/* body 시작 */
	.container{
		margin: 70px 0 0 300px;
	}
	#page_title{
		text-align: center;
	}
	.write_table{
		border: 1px solid #EAEAEA;
		width:1200px;
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
/* body 끝 */
	
</style>
</head>
<body>
<c:import url="../../../temp/h.jsp"/>
	<div class="container">
		<div class=row>
		<h3 id="page_title">board</h3>
			<div class="write">
				<form action="./qna/qnaWrite.do" method="post">
					<table class="write_table">
						<tr>
							<th>제목</th>
							<td><input type="text" id="title" name="title" size=50></td>
						</tr>
						
						<tr>
							<th>작성자</th>
							<td><input type="text" id="writer" name="writer" size=15></td>
						</tr>
						<tr>
							<td colspan="2" id="contents_ck"><textarea rows="50" cols="40" class="form-control" name="contents">
							</textarea></td>
						</tr>
						<tr>
							<th>첨부파일</th>
							<td><button>파일선택</button>선택된 파일 없음</td>
						</tr>
						<tr>
							<th>비밀번호</th>
							<td><input type="text" id="board_pw" name="board_pw" size=15></td>
						</tr>
						<tr>
							<th>비밀글설정</th>
							<td></td>
						</tr>
						<tr>
							<th>개인정보 수집 및 이용 동의</th>
							<td><textarea cols="20" rows="9">개인정보의 수집..</textarea><br>
							개인정보 수집 및 이용에 동의하십니까?</td>
						</tr>
					</table>
					
					<button>목록</button>
					<input type="submit" value="등록">
					<button>취소</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>