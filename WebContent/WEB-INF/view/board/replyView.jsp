<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="comment_header">
	<strong>${replyDTO.id}</strong><span id="reply_date">${replyDTO.reg_date}</span>
	<div class="comment_row">
		<a><img alt="" src="../images/btn_comment_modify.png"></a> <a><img
			alt="" src="../images/btn_comment_delete.png"></a>
	</div>
</div>
<div class="comment_contents">${replyDTO.contents}</div>