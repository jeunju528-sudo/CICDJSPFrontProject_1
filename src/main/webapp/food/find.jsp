<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container {
	margin-top : 50px;
}
.row {
	margin: 0px auto;
	width: 960px;
}
p {
	overflow : hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	commons(1)
	$('.btns').on('click',function(){
		
	})
	
})

function commons(page){
	let types=[]
	$('input[name=type]:checked').each(function(){
		types.push($(this).val())
	})
	let ss = $('#ss').val()
	let column = $('#column').val()
	
	let params = {
		"type":types,
		"ss":ss,
		"column":column
	}
	
	$.ajax({
		type:'post',
		url:'find_ajax.do',
		data:params,
		tradition:'true', /* 배열 넣기위해 */
		success:function(result){
			//java에서 JSONArray를 toString으로 
			let json = JSON.parse(result)
			console.log(json)
			$('#ss').val(json[0].ss)
			jsonView(json)
		}
	})
}

function jsonView(json){
	let html = ''
	json.forEach((food)=>{
		html += '<div class="col-sm-3">'
		+ '<a href="#">'
		+ '<div class="thumbnail">'
		+ '<img src="'+food.poster+'" style="width:250px;height:130px;">'
		+ '</div>'
		+ '<p>'+food.name+'</p>'
		+ '</a>'
		+ '</div>'
	})
	$('#print').html(html)
}

</script>
</head>
<body>
	<div class="container">
		<div class="row">
<!-- 			<form method="post" action="find.do">  -->
				<select id="column" class="input-sm">
					<option value="address">주소</option>
					<option value="name">맛집명</option>
				</select>
				<input type="checkbox" name="type" value="A">한식
				<input type="checkbox" name="type" value="B">양식
				<input type="checkbox" name="type" value="C">일식
				<input type="checkbox" name="type" value="D">중식
				<input type="checkbox" name="type" value="E">분식
				<input type="text" id="ss" class="input-sm" size=15 value="마포">
				<button class="btn btn-sm btn-primary btns" >검색</button>
<!--			</form> -->
		</div>
		<div class="row" style="margin-top: 20px" id="print">
<%-- 		<c:forEach var="vo" items="${list }">
				<div class="col-sm-3">
					<a href="#">
						<div class="thumbnail">
							<img src="${vo.poster }" style="width:250px; height:130px;">
						</div>
						<p>${vo.name }</p>
					</a>
				</div>
			</c:forEach> --%>
		</div>
		<div class="row text-center" style="margin-top: 20px" id="pagePrint">
		
		</div>
	</div>
</body>
</html>