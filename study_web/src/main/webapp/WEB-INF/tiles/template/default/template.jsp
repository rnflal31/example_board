<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>    
<%@ taglib uri='http://www.springframework.org/tags' prefix='spring'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<c:set var='contextPath' scope="request" value='${pageContext.request.contextPath}' />
<%-- <spring:url var="contextPath" scope="request" value="${pageContext.request.contextPath}" /> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${contextPath}/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/jquery.form.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${contextPath}/js/bootstrap/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${contextPath}/style/common.css">
<link rel="stylesheet" type="text/css" href="${contextPath}/style/bootstrap/css/bootstrap.min.css">
<%-- <link rel="stylesheet" type="text/css" href="${contextPath}/style/bootstrap/css/bootstrap-theme.min.css"> --%>
<link rel="stylesheet" type="text/css" href="${contextPath}/style/custom.css">
<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<body>
	<tiles:insertAttribute name="toolbar" />
	<div class="wrapper">
		<div class="container">
			<tiles:insertAttribute name="header" />
			<div class="row" style="margin-top: 40px;">
				<div class="col-md-10" style="border-right: 1px solid #eeeeee;">
					<tiles:insertAttribute name="body" />
				</div>
				<div class="col-md-2">
					<tiles:insertAttribute name="menu" />
				</div>
			</div>			
		</div>
	</div>
	<tiles:insertAttribute name="footer" />
</body>
</html>