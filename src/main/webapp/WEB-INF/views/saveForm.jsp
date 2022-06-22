<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp" %>
<div class="container">
	<form>
		<div class="form-group">
			<label for="title">Title : </label>
			<input type="text" placeholder="title" id="title" class="form-control">
		</div>
		<div class="form-group">
			<label for="content">Content : </label>
			<textarea class="summernote" rows="5" id="content"></textarea>
		</div>
		<br/>
		<button type="button" onclick="saveBoard()" class="btn btn-primary">글쓰기</button>
	</form>
	<br/>
</div>
<script>
	function saveBoard() {
		let title = document.querySelector("#title").value;
		let content = document.querySelector("#content").value;
		
		console.log(title);
		console.log(content);
		
		let board = {
				title: title,
				content: content
		}

		// fetch 매개변수로 url 과 오브젝트
		 fetch("/save", {
			method: "post",
			headers: {
				"content-type" : "application/json; charset=utf-8"
			},
			body: JSON.stringify(board)
		 })
		.then(res => res.text())
		.then(res => {
			if(res == "ok") {
				alert("글쓰기 성공");
				location.href = "/";
			} else {
				alert("글쓰기 실패");
			}
		 });
	} // end of saveBoard
	

      $('.summernote').summernote({
        placeholder: 'Hello Bootstrap 4',
        tabsize: 2,
        height: 300
      });
    </script>

<%@ include file= "layout/footer.jsp" %>