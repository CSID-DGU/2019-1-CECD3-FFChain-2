<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:forEach var="result" items="${codeInfoList}">
<input type="checkbox" id="${cmcd_id}" name="${cmcd_id}" value="<c:out value="${result.cdId}"/>"><c:out value="${result.cdNm}"/>
</c:forEach>