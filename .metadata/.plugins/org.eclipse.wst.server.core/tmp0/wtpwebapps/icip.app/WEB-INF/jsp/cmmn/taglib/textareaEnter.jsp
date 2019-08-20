<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="chg_cn" value="
"/>
<!--  <div style="white-space: pre-line;">-->
<div>
<c:out escapeXml="false" value="${fn:replace(fn:replace(taglib_contents, ' ', '&nbsp;'), chg_cn, '<br />')}"/>
</div>