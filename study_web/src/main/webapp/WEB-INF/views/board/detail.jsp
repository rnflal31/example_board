<%@ page language='java' contentType='text/html; charset=UTF-8'
	pageEncoding='UTF-8'%>
<script type="text/javascript">
$(document).ready(function(){
	$("#deleteButton").click(function(){
		var isDeleted = confirm("삭제 하시겠습니까?");
		if(isDeleted) {
			var options = { 
				url: '${contextPath}/board/delete',
				type : 'POST',
			    success: function(data) { 
					if(data != 0) {
						alert("삭제되었습니다.");
			    		$(location).attr('href', "${contextPath}/board/list");
					} else {
						alert(data);		
					}
			    } 
			}; 
			$("#detailForm").ajaxSubmit(options);
		}
	});
});

</script>
<form id="detailForm" action='${contextPath}/board/modify'>
	<input type='text' name='id' value='${board.id}'> 
	<input type='submit' value='수정하기'>
	<input type='button' value='삭제하기' id="deleteButton">
	<table class='table table-bordered'>
		<tbody>
			<tr>
				<th>게시글 유형</th>
				<td>${board.type}<input type='hidden' name='type'
					value='${board.type}'></td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${board.title}<input type='hidden' name='title'
					value='${board.title}'></td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${board.content}<input type='hidden' name='content'
					value='${board.content}'></td>
			</tr>
			<tr>
				<th>삭제여부</th>
				<td>${board.delYn}<input type='hidden' name='delYn'
					value='${board.delYn}'></td>
			</tr>
			<tr>
				<th>마지막 수정 날짜</th>
				<td>${board.lastModified}<input type='hidden'
					name='lastModified' value='${board.lastModified}'></td>
			</tr>
			<tr>
				<th>등록날짜</th>
				<td>${board.regDate}<input type='hidden' name='regDate'
					value='${board.regDate}'></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${board.writor}<input type='hidden' name='writor'
					value='${board.writor}'></td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${board.count}<input type='hidden' name='count'
					value='${board.count}'></td>
			</tr>
		</tbody>
	</table>
</form>
