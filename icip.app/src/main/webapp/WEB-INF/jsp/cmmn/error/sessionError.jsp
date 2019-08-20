<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/js/jquery/jquery-1.11.1.min.js" ></script>
<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/js/jquery/jquery-ui.min.js" ></script>
<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/js/jquery/jquery.validate.min.js" ></script>
<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/js/jquery/jquery.form.js" ></script>
<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/js/commonUtil.js" ></script>
<script type="text/javascript">
$(document).ready(function () {
	var _c_member_id = commonUtil.makeNetSsoCookie();
	
	<c:choose>
	<c:when test="${zone ne 'local'}">
		<c:choose>
		<c:when test="${isMisMatchSsoId eq 'true'}">
		location.reload();
		</c:when>
		<c:when test="${isEmptySsoId eq 'true'}">
		//goTarget(_c_member_id);	
		</c:when>
		</c:choose>
	</c:when>
	<c:otherwise>
	goTarget(_c_member_id);
	</c:otherwise>
	</c:choose>
});

	function goTarget(_c_member_id) {
	    alert("${msg}");
		history.back();
		location.href="${targeturl}";
	}
</script>
</head>
<body>
</body>
</html>
