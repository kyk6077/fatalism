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
/* body 시작  */
.main_table {
	width: 100%;
}

#page_title {
	text-align: center;
}

#board_table {
	border: 1px solid #EAEAEA;;
	margin-top: 50px;
 	width: 100%; 
	min-width: 100px;
}

#board_table tr {
	height: 45px;
}

#table_title {
	text-align: center;
	background: #F6F6F6;
}

#table_title>tr {
	height: 40px;
}

.table_subject {
	width: 550px;
	text-align: left !important;
	padding-left: 15px;
}

#board_table td {
	text-align: center;
	border: 1px solid #EAEAEA;;
}

.row_pager {
	
}

.write_btn {
	margin-top: 20px;
	text-align: right;
}
.item_td>img{
	width:100px;
	height:150px;
	max-width:150px;
	max-height:180px;
	padding: 5px;
}
.write_day{
	min-width: 100px;
	padding-left: 10px;
	padding-right: 10px;
}
/* body 끝 */
</style>
</head>
<body>
	<c:import url="../../../temp/h.jsp" />
	<div id="sub_container">
		<div id="sub_contents">
			<h1 id="page_title">${board}</h1>
			<div class="row_table">
				<table id="board_table">
					<thead id="table_title">
						<tr>
							<td>NO</td>
							<c:if test="${board=='review'}"><td>ITEM</td></c:if>
							<td>SUBJECT</td>
							<td>WRITER</td>
							<td>DATE</td>
							<td>HIT</td>
						</tr>
					</thead>
					<c:forEach items="${list}" var="boardDTO">
						<tr>
							<td>${boardDTO.num}</td>
							<c:if test="${board=='review'}">
								<td class="item_td">
									<c:forEach items="${list2}" var="boardimgDTO">
										<c:if test="${boardimgDTO.bnum==boardDTO.num}">
											<img alt="" src="${pageContext.request.contextPath }/boardupload/${boardimgDTO.fname}">
										</c:if>
									</c:forEach>
								</td>
							</c:if>
							<td class="table_subject">
							<c:if test="${boardDTO.hide=='N'}">
								<a href="./${board}SelectOne.do?num=${boardDTO.num}">${boardDTO.subject}</a>
							</c:if> 
							<c:if test="${boardDTO.hide=='S'}">
								
								<c:if test="${board=='qna'}">
									<c:forEach begin="2" end="${boardDTO.depth}">
										<img alt="" src="../images/reply_icon.gif">
									</c:forEach>
								</c:if>
								
								<a href="./${board}PwCheck.do?num=${boardDTO.num}">${boardDTO.subject}<img
									alt="" src="../images/secret_icon.gif"></a>
							</c:if>
							</td>
							<td>${boardDTO.writer}</td>
							<td class="write_day">${boardDTO.reg_date}</td>
							<td>${boardDTO.hit}</td>
						</tr>
					</c:forEach>
				</table>
				<div class="write_btn">
					<a href="./${board}Write.do"><img alt="???"
						src="../images/write_img/btn_write.png"></a>
				</div>
				<div class="row_pager">
					<ul class="pagination">
						<c:if test="${pager.startNum > 1}">
							<li><a
								href="./${board}List.do?curPage=${pager.startNum-1}&search=${pager.search.search}&kind=${pager.search.kind}"><span
									class="glyphicon glyphicon-chevron-left"></span></a></li>
						</c:if>
						<c:forEach begin="${pager.startNum}" end="${pager.lastNum}"
							var="p">
							<li><a
								href="./${board}List.do?curPage=${p}&search=${pager.search.search}&kind=${pager.search.kind}">${p}</a></li>
						</c:forEach>

						<c:if test="${pager.curBlock < pager.totalBlock}">
							<li><a
								href="./${board}List.do?curPage=${pager.lastNum+1}&search=${pager.search.search}&kind=${pager.search.kind}"><span
									class="glyphicon glyphicon-chevron-right"></span></a></li>
						</c:if>
					</ul>
				</div>


				<div class="Row_search">
					검색어
					<form class="form-inline" action="./${board}List.do">
						<div class="form-group">
							<select class="form-control" id="menu2" name="kind">
								<option>subject</option>
								<option>contents</option>
								<option>writer</option>
							</select> <input type="text" class="form-control" id="search"
								placeholder="Enter search" name="search">
						</div>
						<button type="submit" class="btn btn-default">Submit</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<c:import url="../../../temp/footer.jsp" />
</body>
</html>