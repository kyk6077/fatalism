<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../../../temp/b.jsp" />
<style type="text/css">
</style>
<script type="text/javascript">
	$(function(){
		
		var fcount = 1;
		var findex = 1;
		$('.table').on('click','.file_plus',function(){
			
			findex++;
			var ht = '<tr id="f'+findex+'"><th>ProductImage</th><td><input type="file" name="f'+findex+'">';
			ht += '<span class="glyphicon glyphicon-plus file_plus"></span>';
			ht += '<span class="glyphicon glyphicon-remove file_drop"></span></td></tr>';
			$('.table').append(ht);
			fcount++;
		});
		
		$('.table').on('click','.file_drop',function(){
			if(fcount>1){
				$(this).closest("tr").remove();
				fcount--;
			}
		});
		
		
	});
</script>
</head>
<body>
	<c:import url="../../../temp/h.jsp" />
	<div id="sub_container">
		<div id="sub_contents">
			<h3 id="page_title">Product</h3>
			<form action="./productInsert.do" method="post"
				enctype="multipart/form-data">
				<table class="table">
					<tr>
						<th>Name</th>
						<td><input type="text" name="name"></td>
					</tr>
					<tr>
						<th>Price</th>
						<td><input type="number" name="price"></td>
					</tr>
					<tr>
						<th>Type</th>
						<td><select name="type">
								<option>27y</option>
								<option>FATALISM</option>
								<option>GARMENTS LINE</option>
								<option>NewProduct</option>
						</select></td>

					</tr>
					<tr>
						<th>Size Count</th>
						<td>S : <input type="number" name="scount">
						R : <input type="number" name="rcount">
						L : <input type="number" name="lcount"></td>
						
<!-- 						<td><select name="size"> -->
<!-- 								<option>L</option> -->
<!-- 								<option>R</option> -->
<!-- 								<option>S</option> -->
<!-- 						</select></td> -->
					</tr>
<!-- 					<tr> -->
<!-- 						<th>Count</th> -->
<!-- 						<td><input type="number" name="count"></td> -->
<!-- 					</tr> -->
					<tr>
						<th>Description</th>
						<td><input type="text" name="description"></td>
					</tr>
					
					<tr id="f1">
					<th>ProductImage</th>
					<td><input type="file" name="f1"><span class="glyphicon glyphicon-plus file_plus"></span><span class="glyphicon glyphicon-remove file_drop"></span></td></tr>
					
					
				</table>
				<input type="submit" value="상품 등록">
			</form>
		</div>
	</div>
	<c:import url="../../../temp/footer.jsp" />
</body>
</html>