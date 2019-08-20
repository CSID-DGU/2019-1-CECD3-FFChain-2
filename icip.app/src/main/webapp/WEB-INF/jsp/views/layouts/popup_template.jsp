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
<tiles:insertAttribute name="include" />
<%-- csrf 방지 소스 적용 --%>
<c:if test = "${not empty _csrf.headerName }">
	<meta name = "_csrf_header" content = "${_csrf.headerName }" />
	<meta name = "_csrf" content = "${_csrf.token }" />
</c:if>
</head>
<body>
<tiles:insertAttribute name="body" />
<!-- 로딩이미지-->
<div id="loading" style='position:absolute;left:40%;top:50%;background:#ffffff;display:none;'>	
<table width="290" cellpadding="0" cellspacing="0" border="0" >
  <tr> 
    <td class="data_none_c"><img SRC='/images/loading/ajax_loding.gif'>
    <br>
  <font color="blue">검색 데이터양에 따라 로딩시간이 지연될수있습니다.</font>
    </td>
  </tr>
</table>
</div>
<!-- 로딩이미지-->
</body>
</html>
