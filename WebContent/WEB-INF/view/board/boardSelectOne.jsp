<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../../../temp/b.jsp" />
<style type="text/css">
/* body 시작 */
#page_title {
	text-align: center;
}

.write {
	min-width: 650px;
}

.write_table {
	border: 1px solid #EAEAEA;
	width: 100%;
}

.write_table td {
	padding: 10px;
	border: 1px solid #EAEAEA;
}

.write_table th {
	padding: 12px;
	border: 1px solid #EAEAEA;
	width: 150px;
	background: #F6F6F6;
}

#contents_ck {
	padding: 0px;
}

.row_btn {
	margin-top: 20px;
}

.row_btn2 {
	float: right;
}

#table_contents>ul {
	border-bottom: 1px solid #EAEAEA;
	padding: 15px;
	line-height: 15px;
}

#table_contents li {
	display: inline-block;
}

.select_contents {
	padding: 15px;
}

#hit_list {
	margin-left: 20px;
}

#bottom_row {
	margin-top: 20px;
}

#comment_write {
	margin-bottom: 20px;
}

.reply_date {
	margin-left: 20px;
}

.comment_row {
	float: right;
}

.comment_header {
	padding: 10px;
}

.comment_view {
	border: 1px solid #EAEAEA;
}

.comment_contents {
	border-top: 1px solid #EAEAEA;
	padding: 20px; 
}
/* body 끝 */
</style>

<script type="text/javascript">
	$(function() {

		$('#delete_btn').on('click', function() {
			var pw = $('#board_pw').val();
			if (pw == "" || pw == null) {
				alert('비밀번호를 입력해주세요');
			} else {
				$('#delete_selectform').submit();
			}

		});

		$('#comment').on('click', function() {
			$.ajax({
				type : "POST",
				url : "./comment.do",
				data : {
					"bnum" : $('#comment_num').val(),
					"id" : $('#comment_id').val(),
					"contents" : $('#comment_contents').val()
				},
				success : function(data) {
					$('#comment_view').html("");
					$('#comment_view').html(data.trim());
					$('#comment_contents').val("");
				},
				error : function() {
					alert('reply fail');
				}
			});
		});

		$('#comment_view').on('click', '.comment_delete', function() {
			var index = $(this).attr('title');
			$.ajax({
				type : "POST",
				url : "./commentDelete.do",
				data : {
					"num" : index
				},
				success : function(data) {
					var c = 'comment' + index;
					$('#' + c).remove();
					alert("삭제 완료");
					location.href="#comment_view";
				},
				error : function() {
					location.href="#comment_view";
					alert("비밀번호가 틀립니다.");
				}

			});

		});

		$('#comment_view').on('click', '.comment_modify', function() {
			var index = $(this).attr('title');
			$('#contents_p'+index).css("display","none");
			$('#update_row'+index).css("display","block");

		});
		
		
		$('#comment_view').on('click', '.comment_btn', function() {
			var index = $(this).attr('title');
			var contents_val = $('#update_text'+index).val();
			$.ajax({
				type : "POST",
				url : "./commentUpdate.do",
				data : {
					"num" : index,
					"contents" : contents_val
				},
				success : function(data) {
					alert(data);
					$('#update_text'+index).val(data);
					$('#contents_p'+index).html(data);
					$('#update_row'+index).css("display","none");
					$('#contents_p'+index).css("display","block");
					alert("Update success");
					location.href="#comment_view";
				},
				error : function() {
					location.href="#comment_view";
					alert("Update fail");
				}

			});

		});

	});
</script>
</head>
<body>
	<c:import url="../../../temp/h.jsp" />
	<div id="sub_container">
		<div id="sub_contents">
			<div class="write">
				<h3 id="page_title">${board}</h3>
				<table class="write_table">
					<tr>
						<th>제목</th>
						<td>${boardDTO.subject}</td>
					</tr>
					<tr>
						<th>작성자</th>
						<td>${boardDTO.writer}</td>
					</tr>
					<tr>
						<td colspan="2" id="table_contents">
							<ul>
								<li><strong>작성일 </strong>${boardDTO.reg_date}</li>
								<li id="hit_list"><strong> 조회수 </strong>${boardDTO.hit}</li>
							</ul>
							<div class="select_contents">
								<p>${boardDTO.contents}</p>
							</div>
						</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><form action="./${board}Delete.do" id="delete_selectform">
								<input type="hidden" value="${boardDTO.num}" name="num">
								<input type="password" id="board_pw" name="board_pw" size=10>
							</form></td>
					</tr>
				</table>

				<div class="row_btn">
					<a href="./${board}List.do"><img alt="1"
						src="../images/write_img/btn_list.png"></a>

					<div class="row_btn2">
						<a id="delete_btn"><img alt="1"
							src="../images/write_img/btn_bodelete.png"></a> <a
							href="./${board}Update.do?num=${boardDTO.num}"><img alt="1"
							src="../images/write_img/btn_modify.png"></a>
					</div>
					
					<c:if test="${board=='qna'}">
						<a href="./reboardWrite.do?num=${boardDTO.num}&subject=${boardDTO.subject}" class="reboard_btn">답글</a>
					</c:if>
					
					
					<c:if test="${board=='review'}">
						<div id="bottom_row">
							<div id="comment_write">
								<input type="hidden" id="comment_num" value="${boardDTO.num}">
								<input type="hidden" id="comment_id" value="tester"> <input
									type="text" size="50px" placeholder="댓글을 달수있습니다."
									id="comment_contents">
								<button id="comment">등록</button>
							</div>
							<div id="comment_view">
								<c:forEach items="${replyList}" var="replyDTO"
									varStatus="status">
									<div id="comment${replyDTO.num}">
										<div class="comment_header">
											<strong>${replyDTO.id}</strong><span class="reply_date">${replyDTO.reg_date}</span>
											<%-- <c:if test="${member.id==replyDTO.id"> --%>
											<div class="comment_row">
												<a href="#" class="comment_modify" title="${replyDTO.num}"><img
													alt="" src="../images/btn_comment_modify.png"></a> <a
													href="#" class="comment_delete" title="${replyDTO.num}"><img
													alt="" src="../images/btn_comment_delete.png"></a>
											</div>
											<%-- </c:if> --%>
										</div>
										<div class="comment_contents">
											<p id="contents_p${replyDTO.num}">${replyDTO.contents}</p>
											<div id="update_row${replyDTO.num}" style="display:none">
												<input type="text" id="update_text${replyDTO.num}" size="50px" 
													value="${replyDTO.contents}">
												<button class="comment_btn" title="${replyDTO.num}">수정완료</button>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<c:import url="../../../temp/footer.jsp" />
</body>
</html>