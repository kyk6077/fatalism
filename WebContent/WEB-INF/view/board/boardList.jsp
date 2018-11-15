<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../../../temp/b.jsp"/>

<style type="text/css">
/* body 시작  */
 	.container{ 
 		margin: 70px 0 0 300px; 
 	} 
 	.main_table{ 
 		width: 100%; 
 	} 
 	#page_title{ 
 		text-align:center; 
 	} 
 	#board_table{ 
 		border: 1px solid #EAEAEA;; 
 		margin-top:50px; 
 		width:100%; 
 	} 
 	#board_table tr{ 
 		height: 45px; 
 	} 
 	#table_title{	 
 		text-align: center; 
 		background: #F6F6F6; 
 	} 
 	#table_title>tr{ 
 		height: 40px; 
 	} 
 	#board_table td{ 
 		text-align: center; 
 		border: 1px solid #EAEAEA;; 
 	} 
 	.table_subject{ 
 		width:600px; 
 	} 
 	.row_pager{ 
 		border-radius: 0px; 
 	} 
 	.write_btn{ 
 		margin-top: 20px; 
 		text-align: right; 
 	} 
 /* body 끝 */ 
	
</style>
</head>
<body>
<c:import url="../../../temp/h.jsp"/>
	<div class="container">
		<h3 id="page_title">${board}</h3>
		<div class="row_table">
				<table id="board_table">
				<thead id="table_title">
					<tr>
						<td>NO</td><td>SUBJECT</td><td>WRITER</td><td>DATE</td><td>HIT</td>
					</tr>
				</thead>
					<c:forEach items="${list}" var="boardDTO">
					<tr>
						<td>${boardDTO.num}</td><td class="table_subject"><a href="./${board}SelectOne.do?num=${boardDTO.num}">${boardDTO.subject}</a></td><td>${boardDTO.writer}</td><td>${boardDTO.reg_date}</td><td>${boardDTO.hit}</td>
					</tr>
					</c:forEach>
				</table>
			<div class="write_btn">
				<a href="./${board}Write.do"><img alt="???" src="../images/btn_write.png"></a>
			</div>
		<div class="row_pager">
			<ul class="pagination">
				<c:if test="${pager.startNum > 1}">
					<li><a href="./${board}List.do?curPage=${pager.startNum-1}&search=${pager.search.search}&kind=${pager.search.kind}"><span class="glyphicon glyphicon-chevron-left"></span></a></li>
 	 			</c:if>
 	 			<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="p">
 	 				<li><a href="./${board}List.do?curPage=${p}&search=${pager.search.search}&kind=${pager.search.kind}">${p}</a></li>
 	 			</c:forEach>
 	 			
 	 			<c:if test="${pager.curBlock < pager.totalBlock}">
    				<li><a href="./${board}List.do?curPage=${pager.lastNum+1}&search=${pager.search.search}&kind=${pager.search.kind}"><span class="glyphicon glyphicon-chevron-right"></span></a></li>
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
				        <option>상품정보</option>
			     	</select>	    
			      <input type="text" class="form-control" id="search" placeholder="Enter search" name="search">
			    </div>
			    <button type="submit" class="btn btn-default">Submit</button>
			  </form>
			</div>
		</div>
	</div>
</body>
</html>