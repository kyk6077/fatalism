<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach items="${replyList}" var="replyDTO" varStatus="status">
	<div id="comment${replyDTO.num}">
		<div class="comment_header">
			<strong>${replyDTO.id}</strong><span class="reply_date">${replyDTO.reg_date}</span>
			<%-- <c:if test="${member.id==replyDTO.id"> --%>
			<div class="comment_row">
				<a href="#" class="comment_modify" title="${replyDTO.num}"><img
					alt="" src="../images/btn_comment_modify.png"></a> <a href="#"
					class="comment_delete" title="${replyDTO.num}"><img alt=""
					src="../images/btn_comment_delete.png"></a>
			</div>
			<%-- </c:if> --%>
		</div>
		<div class="comment_contents">
			<p id="contents_p${replyDTO.num}">${replyDTO.contents}</p>
			<div id="update_row${replyDTO.num}" style="display: none">
				<input type="text" id="update_text${replyDTO.num}" size="50px"
					value="${replyDTO.contents}">
				<button class="comment_btn" title="${replyDTO.num}">수정완료</button>
			</div>
		</div>
	</div>
</c:forEach>