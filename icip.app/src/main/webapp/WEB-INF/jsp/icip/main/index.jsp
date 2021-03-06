<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import = "java.sql.*" %>
<link href="<c:out value="${pageContext.request.contextPath}"/>/css/index.css" rel="stylesheet" type="text/css" />

<%
  /**
  * @Class Name : index.jsp
  * @Description : index 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2019.03.07.	DGU		신규생성
  *
  * author DGU
  * since 2019.03.07
  *
  * @Copyright ⓒ 2019 Dongguk Univ., All Rights Reserved.
  */
%>
<c:url var="logoutPage" value="/icip/login/logout.do"/>
<c:url var="noticePage" value="/icip/notice/noticeList.do"/>
<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<META http-equiv="Expires" content="-1">
<META http-equiv="Pragma" content="no-cache">
<META http-equiv="Cache-Control" content="No-Cache">
<link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon" />
<script type="text/javascript" src="/js/jquery/jquery-1.11.1.min.js"></script>
<jsp:useBean id="toDay" class="java.util.Date" />

<title>동국대학교</title>
<style type="text/css">

</style>
</head>
<body>
<div id = "mainbox"style = "display:none">
	<div class = "codebox" >
	코드박스
	</div>
		<div class = "innerbox" style = "display : grid;">
			<div>
			교과학습조회
			</div>
			<div>
			비교과학습조회
			</div>
			<div >
			분석조회
			</div>
			<div>
			이용가이드
			</div>
		</div>
</div>

</body>
</html>