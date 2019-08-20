<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    response.setContentType("application/json");
%>
<c:choose>
<c:when test="${fn:indexOf(exception.cause, 'java.sql.SQL') > -1}">
{"baseException" : { "name" : "${exception}", "message" : "장애가 발생하였습니다. 관리자에게 문의해 주세요" }}
</c:when>
<c:otherwise>
{"baseException" : { "name" : "${exception}", "message" : ${exception.message} }}
</c:otherwise>
</c:choose>
