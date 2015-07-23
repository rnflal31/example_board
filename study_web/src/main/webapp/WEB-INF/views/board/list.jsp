<%@ page language='java' contentType='text/html; charset=UTF-8'
	pageEncoding='UTF-8'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<script type="text/javascript">
$(document).ready(function(){
// 	var getParameters = function(key){
// 		this.parameterKeyMap = {
// 			"all" : ["keyword", "writerId"],
// 			"keyword" : ["keyword"],
// 			"writer" : ["writerId"]
// 		};
// 		this.searchParameters = "";
// 		console.log(key);
// 		console.log(this.parameterKeyMap[key]);
// 		$.each(this.parameterKeyMap[key], function(idx, value) {
// 			console.log(value);
// 			var val = $("#searchKey").val();
// 			searchParameters += "&"+ value + "=" + val;
// 		});
// 		return searchParameters;
// 	};
	$("#pageSizeSelectBox").val("${page.pageSize}");
	$("#searchType").val("${page.searchType}");
	$("#pageSizeSelectBox").change(function() {
		$(location).attr('href', "${contextPath}/board/list?pageNum=1&pageSize="+$(this).val());
	});
	$("#searchButton").click(function() {
		var searchTypeValue = $("#searchType option:selected").val();
		var keyword = $("#searchKey").val();
		var url = "${contextPath}/board/list?pageNum=1&pageSize=${page.pageSize}&searchType=" + searchTypeValue + "&keyword=" + keyword;
		$(location).attr('href', url);
// 		alert(getParameters(searchTypeValue));
	});
});
</script>	
<div class="row">
	<div class="col-md-2">
		<div class="form-group">
		  <label for="pageSizeSelectBox">게시글 갯수</label>
		  <select class="form-control input-sm" id="pageSizeSelectBox">
		    <option value="10">10</option>
		    <option value="15">15</option>
		    <option value="20">20</option>
		  </select>
		</div>
	</div>
	<div class="col-md-5">
<!--  		<div class="page-header"> -->
<!--      		<h4 id="type">게시판</h4> -->
<!--    		</div> -->
	</div>
	<div class="col-md-5">
		<div class="row">
			<label for="pageSizeSelectBox" style="margin-left: 20px;">검색</label>
		</div>
		<div class="row">
			<div class="form-group" style="float: left; margin-left: 20px;">
				<select class="form-control input-sm" id="searchType">
				    <option value="all" selected="selected">모두</option>
				    <option value="titleAndContent">제목 + 내용</option>
				    <option value="writer">작성자</option>
			  	</select>		  
			</div>
			<div class="form-group" style="float: left; margin-left: 20px;">			
	  			<input type="text" class="form-control input-sm" id="searchKey" value="${page.keyword}">
			</div>
			<div class="form-group" style="float: right; margin-right: 20px;">
<!-- 				<a href="#" class="btn btn-primary btn-md">검색</a> -->
				<a class="btn btn-default" id="searchButton">검색</a>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="bs-component">
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>board_id</th>
						<th>게시글 유형</th>
						<th>제목</th>
						<th>삭제여부</th>
						<th>마지막 수정 날짜</th>
						<th>등록날짜</th>
						<th>작성자</th>
						<th>조회수</th>
					</tr>
				</thead>
				<tbody>
				<c:choose>
					<c:when test="${empty page.entities}">
						<tr>
							<th colspan="8"><p>empty</p></th>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${page.entities}" var="board" varStatus="status">
							<tr>
								<td>${board.id}</td>
								<td>${board.type}</td>
								<td><a href="${contextPath}/board/detail/${board.id}">${board.title}</a></td>
								<td>${board.delYn}</td>
								<td>${board.lastModified}</td>
								<td>${board.regDate}</td>
								<td>${board.writor}</td>
								<td>${board.count}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose> 
				</tbody>
			</table>
		</div>	            
	</div>
</div>
<div class="row">
	<div class="col-md-2 valignCenter">
	<a href="${contextPath}/board/write" class="btn btn-primary btn-md">write</a>
	</div>
	<div class="col-md-8" align="center">
		<ul class="pagination">
		<li class="disabled"><a href="#">«</a></li>
		<c:forEach begin="1" end="${page.pageCnt}" var="index">		  
		  <c:choose>
		  	<c:when test="${index == page.pageNum}">
		  		<li class="active"><a href="#">${index}</a></li>		  
		  	</c:when>
		  	<c:otherwise>
		  		<li><a href="${contextPath}/board/list?pageNum=${index}&pageSize=${page.pageSize}&keyword=${page.keyword}&searchType=${page.searchType}">${index}</a></li>
		  	</c:otherwise>
		  </c:choose>
		</c:forEach>
		<li class="disabled"><a href="#">»</a></li>
		</ul>
	</div>
	<div class="col-md-2"></div>
</div>
