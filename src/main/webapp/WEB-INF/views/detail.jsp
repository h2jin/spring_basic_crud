<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp" %>
<main class= "container">

	<a class="btn btn-warning" href="/updateForm/${board.id}">수정</a>
	<button type="button" onclick="deleteBoard(${board.id})" class="btn btn-danger">삭제</button>
	<br/>
	<br/>
	
	<h5 class="m-2">조회수 : <i>${board.readCount}</i></h5>
	
	<br/>
	<br/>
	
	<h3 class="m-2">${board.title}</h3>
	<hr/>
	
	<div class="form-group">
		<div class="m-2">${board.content}</div>
	</div>
	<hr/>

</main>

<script>
	function deleteBoard(id) {
		
		fetch("/board/" + id, {
			method: "delete"
		})
		.then(res => res.text()) // res = response ->텍스트로 변환해라
		.then(res => {
			if(res == "true") {
				alert("삭제 성공");
				location.href = "/";
			} else {
				alert("삭제실패");
			}
		});
	}
</script>

<%@ include file="layout/footer.jsp" %>