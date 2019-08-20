<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
    if (request.getProtocol().equals("HTTP/1.1")) response.setHeader("Cache-Control", "no-cache");
%>

<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8">  
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
<link rel="icon" href="/images/favicon.ico" type="image/x-icon"> 
<title><tiles:insertAttribute name="title"/></title>
<tiles:insertAttribute name="adminpopupinclude" />
<%-- csrf 방지 소스 적용 --%>
<c:if test = "${not empty _csrf.headerName }">
	<meta name = "_csrf_header" content = "${_csrf.headerName }" />
	<meta name = "_csrf" content = "${_csrf.token }" />
</c:if>
<!--[if lt IE 8]>
    <script type="text/javascript" charset="utf-8" src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE8.js"></script>
	<script type="text/javascript" src="/js/PIE_uncompressed.js"></script>
<![endif]-->
<!--[if IE 8]>
	<link type="text/css" rel="stylesheet" href="/css/com/ie8.css">
<![endif]-->
</head>
<body>
<tiles:insertAttribute name="body" />
</body>
</html>
