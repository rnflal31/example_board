<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
$(document).ready(function(){
	CKEDITOR.replace('content_editor', {
// 	    language: 'ko',
//         resize_enabled : false, // 에디터 크기를 조절하지 않음
        enterMode : CKEDITOR.ENTER_BR , // 엔터키를 <br> 로 적용함.
        shiftEnterMode : CKEDITOR.ENTER_P ,  // 쉬프트 +  엔터를 <p> 로 적용함.
        toolbarCanCollapse : true , 
//         removePlugins : "elementspath", // DOM 출력하지 않음
		filebrowserImageUploadUrl: '${contextPath}/board/image/upload'
	});
    CKEDITOR.on('dialogDefinition', function( ev ){
        var dialogName = ev.data.name;
        var dialogDefinition = ev.data.definition;
        switch (dialogName) {
            case 'image': //Image Properties dialog
                dialogDefinition.removeContents('Link');
                dialogDefinition.removeContents('advanced');
//                 dialogDefinition.resize(500, 400);
                break;
        }
    });
	function validationCheck(){
		var isValid = true
		$(".notNullFiled").each(function(index, element) {
			if($(this).val() == "" || $(this).val() == null) {
				isValid = false;
				return false;
			}
		});
		return isValid;
	};
	function registerBoard(){
// 		var param =$("#boardForm").serialize() + "&content='" + CKEDITOR.instances['content_editor'].getData()+"'";
// 		var param =$("#boardForm").serialize();
		var options = { 
		    success: function(data) { 
				if(data != 0) {
		    		$(location).attr('href', "${contextPath}/board/list");
				} else {
					alert(data);		
				}
		    } 
		}; 
		$("#boardForm").ajaxSubmit(options);
	};
    $("#insertBoardButton").click(function(){
    	CKEDITOR.instances['content_editor'].updateElement();
		if(validationCheck()) {
			registerBoard();			
		} else {
			alert("유효하지 않은 값 입니다.")
		}
    });
});
</script>
<form action="${contextPath}/board/merge" method="post" id="boardForm">
	<input type="hidden" value="${board.id}" name="id">
	<input type="hidden" value="samhyun" name="writor">
	<input type="hidden" value="n" name="delYn">
	<input type="hidden" value="normal" name="type">
	<table class="table table-bordered">
		<tr>
			<th>제목</th> 
			<td><input type="text" name="title" class="notNullFiled" value="${board.title}"/></td>
		</tr>
		<tr>
			<th colspan="2">내용</th>
		</tr>
		<tr>
			<td colspan="2">
				<textarea id="content_editor" name="content" class="notNullFiled noneBorder">
					${board.content}
				</textarea>
			</td>
		</tr>
	</table>
<!-- 		<input type="submit" value="등록"> -->
	<input type="button" id="insertBoardButton" value="등록">
</form>
