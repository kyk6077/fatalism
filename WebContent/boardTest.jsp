<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="./temp/b.jsp"/>

<style type="text/css">
/* body 시작 */

	.container{
		margin: 70px 0 0 400px;
	}
	#page_title{
		text-align:center;
	}
	#board_table{
		border: 1px solid #EAEAEA;;
		margin-top:50px;
		width:1300px;
	}
	#board_table tr{
		height: 45px;
	}
	#table_title{	
		background: sliver;
		text-align: center;
		background: #F6F6F6;
	}
	#table_title>tr{	
		height: 30px;
	}
	#board_table td{
		text-align: center;
		border: 1px solid #EAEAEA;;
	}
	.table_subject{
		width:750px;
	}

/* body 끝 */
	
</style>

</head>
<body>
<c:import url="./temp/h.jsp"/>
	<div class="container">
		<h3 id="page_title">Board</h3>
		<div class="row">
			<table id="board_table">
			<thead id="table_title">
				<tr>
					<td>NO</td><td>SUBJECT</td><td>WRITER</td><td>DATE</td><td>HIT</td>
				</tr>
			</thead>
				<c:forEach var="v" begin="1" end="10" step="1">
				<tr>
					<td>sdsf+${v}</td><td class="table_subject">sadsdsfsadsdsf</td><td>sadfdsf</td><td>sadfdsf</td><td>sadfdsf</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>