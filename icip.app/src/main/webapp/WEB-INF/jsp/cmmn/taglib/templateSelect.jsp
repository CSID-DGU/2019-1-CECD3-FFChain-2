<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<select id="${cmcd_id}" name="${cmcd_name}" title="${cmcd_title}" 
<c:if test="${not empty cmcd_cssClass}">class="${cmcd_cssClass}"</c:if> 
<c:if test="${not empty cmcd_cssStyle}">style="${cmcd_cssStyle}"</c:if>>
<c:if test="${cmcd_emptyOptionYn eq 'Y'}">
<option value=""><c:out value="${cmcd_emptyOptionText}" /></option>
</c:if>
<c:forEach var="detailsCodeList" items="${detailsCodeVOList}">
  <c:set var="isSelectedVal" value="false" />
  <c:if test="${detailsCodeList.code eq cmcd_selectedVal}">
    <c:set var="isSelectedVal" value="true" />
  </c:if>
  <option value="<c:out value="${detailsCodeList.code}"/>" ${isSelectedVal ? "selected='selected'" : ""}>
    <c:out value="${detailsCodeList.cname}" />
  </option>
</c:forEach>
</select>