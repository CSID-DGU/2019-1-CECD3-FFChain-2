<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%

    Object errorCode = request.getAttribute("javax.servlet.error.message");
    pageContext.setAttribute("exception", exception);
    pageContext.setAttribute("_errorCode", errorCode);
%>
	<div class="container">
		<div class="cm_boxst">
			<div class="cm_txt1">
				<p>요청하신 페이지를 찾을 수 없거나 <br />시스템에 다른 문제가 발생했습니다.</p>
				<p>이전페이지에서 다시 시도해 보시기 바랍니다.<br />이용에 불편을 드려 죄송합니다. </p>
				<div class="cm_box_y mt20">
					<p>
					<c:choose>
		                <c:when test="${fn:indexOf(exception.cause, 'java.sql.SQL') > -1}">
		                	시스템 에러 - 관리자에게 문의하시기 바랍니다.
		                </c:when>
		                <c:otherwise>
		                	<c:if test="${empty _errorCode}"><c:out value="${exception.message}" /></c:if>
							<c:if test="${not empty _errorCode}"><c:out value="${_errorCode}" /></c:if>
		                </c:otherwise>
	                </c:choose>
					</p>
				</div>
			</div>
		</div>
		<div>
			<a href="javascript:history.back()" class="cm_btn_b_f"><span>이전 페이지로</span></a>
			<a href="/" class="cm_btn_b_o"><span>메인으로</span></a>
		</div>
	</div>