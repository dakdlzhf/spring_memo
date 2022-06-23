<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function update(){
		let url = '/memo/update/${memono}';
		location.href = url;
	}
	function del(){
		let url = '/memo/delete/${memono}';
		location.href = url;
	}
	function reply(){
		let url = '/memo/reply/${memono}';
		location.href = url;
	}
</script>
</head>
<body>
 
	<div class="container">
		<h1 class="col-sm-offset-2 col-sm-10">메모</h1>
		<div class="panel panel-default">

			<div class="panel panel-default">
				<div class="panel-heading">이름</div>
				<div class="panel-body">${dto.wname}</div>

				<div class="panel-heading">제목</div>
				<div class="panel-body">${dto.title}</div>

				<div class="panel-heading">내용</div>
				<div class="panel-body">${dto.content}</div>


				<div class="panel-heading">조회수</div>
				<div class="panel-body">${dto.viewcnt}</div>
				
				<div class="panel-heading">등록일</div>
				<div class="panel-body">${dto.wdate}</div>
				

			</div>
			<div>
			<button type="button" class="btn" onclick="location.href='/memo/create'">등록</button>
			<button type="button" class="btn" onclick="update()">수정</button>
			<button type="button" class="btn" onclick="del()">삭제</button>
			<button type="button" class="btn" onclick="reply()">답변</button>
			<button type="button" class="btn" onclick="location.href='/memo/list'">목록</button>
			</div>
		</div>
</body>
</html>