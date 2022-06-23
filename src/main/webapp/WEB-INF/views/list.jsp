
<%@page import="com.study.model.MemoDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function read(memono) {
		let url = 'read/' + memono;
		location.href = url;
	}
</script>
</head>
<body>
	<div class="container">
		<h1 class="col-sm-offset-2 col-sm-10">메모 목록</h1>

		<form class="form-inline" action="/memo/list">
			<div class="form-group">
				<select class="form-control" name="col">
					<option value="wname" <c:if test="${col == wname }">selected</c:if>>이름</option>
					<option value="title" <c:if test="${col == title }">selected</c:if>>제목</option>
					<option value="content"
						<c:if test="${col == content }">selected</c:if>>내용</option>
					<option value="title_content"
						<c:if test="${col == title_content }">selected</c:if>>제목+내용</option>
					<option value="total" <c:if test="${col == total }">selected</c:if>>전체출력</option>
				</select>
			</div>
			<div class="form-group">
				<input type="text" class="form-control" placeholder="Enter 검색어"
					name="word" value="${word}" />
			</div>
			<button class="btn btn-default">검색</button>
			<button class="btn btn-default" type="button"
				onclick="location.href='/memo/create'">등록</button>
		</form>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>이름</th>
					<th>등록날짜</th>
					<th>grpno</th>
					<th>indent</th>
					<th>ansnum</th>
				</tr>
			</thead>
			<tbody>

				<c:choose>
					<c:when test="${empty list}">
						<tr>
							<td colspan='7'>등록된 글이 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="dto" items="${list}">
							<tr>
								<td>${dto.memono}</td>
								<td><c:forEach begin="1" end="${dto.indent}">
									&nbsp;&nbsp;
									</c:forEach> 
									<c:if test="${dto.indent > 0 }">
										<img src='/images/re.jpg' />
									</c:if> 
									<a href="javascript:read( '${dto.memono}' )">${dto.title}</a>
								</td>
								<td>${dto.wname}</td>
								<td>${dto.wdate}</td>
								<td>${dto.grpno}</td>
								<td>${dto.indent}</td>
								<td>${dto.ansnum}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		<div>${paging}</div>
	</div>
</body>
</html>