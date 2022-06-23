<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp" %>
<div class="container">
	<form>
		<div class="form-group">
			<label for="title">Title : </label>
			<input type="text" value="${board.title}" placeholder="title" id="title" class="form-control">
		</div>
		<div class="form-group">
			<label for="content">Content : </label>
			<textarea class="summernote" rows="5" id="content">${board.content}</textarea>
		</div>
		<br/>
		<button type="button" onclick="updateBoard(${board.id})" class="btn btn-primary">글 수정하기</button>
	</form>
	<br/>
</div>
<script>
	function updateBoard(id) {
		console.log("id : " + id);
		
		let title = document.querySelector("#title").value;
		let content = document.querySelector("#content").value;
		
		// 컨트롤러에 던질 때 주소설계 어떻게 해야하나?
		let board = {
				title: title,
				content: content
		}

		// fetch 비동기 통신을 사용하기 위해
		 fetch("/board/" + id, {
			method: "put",
			headers: {
				"content-type" : "application/json; charset=utf-8"
			},
			body: JSON.stringify(board)
		 })
		.then(res => res.text())
		.then(res => {
			if(res == "ok") {
				alert("글수정 성공");
				location.href = "/";
			} else {
				alert("글수정 실패");
			}
		 });
	} // end of updateBoard
	

      $('.summernote').summernote({
        placeholder: 'Hello Bootstrap 4',
        tabsize: 2,
        height: 300
      });
    </script>

<%@ include file= "layout/footer.jsp" %>