<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<script type='text/javascript'>
window.parent.CKEDITOR.tools.callFunction('${file.CKEditorFuncNum}', '${contextPath}${file.attachPath}${file.filename}', '파일 전송 완료.');
</script>