<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<script type="text/javaScript" language="javascript" defer="defer">
<!--
/* pagination 페이지 링크 function */
function fn_egov_link_page(pageNo){
	document.getElementById("listForm").pageIndex.value = pageNo;
	document.getElementById("listForm").action = "<c:url value='/icip/util/zipcodelist.do'/>";
   	document.getElementById("listForm").submit();
}

 // -->
</script>
<form:form commandName="searchVO" name="listForm" id="listForm" method="post">
    <input type="hidden" name="codeId" />
<div id="content_pop">
	<!-- 타이틀 -->
	<div id="title">
		<ul>
			<li><img src="<c:url value='/images/egovframework/example/title_dot.gif'/>" alt="title" /> List </li>
		</ul>
	</div>
	<!-- // 타이틀 -->
	<!-- List -->
	<div id="table">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<colgroup>
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>			
							</colgroup>		  
			<tr>
								<th align="center">seqid</th>
								<th align="center">zipcode</th>
								<th align="center">sido</th>
								<th align="center">gugun</th>
								<th align="center">dong</th>
								<th align="center">bunji</th>
							</tr>
			<c:forEach var="result" items="${resultList}" varStatus="status">
			<tr>
		 				<td align="center" class="listtd"><c:out value="${result.seqid}"/>&nbsp;</td>
								<td align="center" class="listtd"><c:out value="${result.zipcode}"/>&nbsp;</td>
								<td align="center" class="listtd"><c:out value="${result.sido}"/>&nbsp;</td>
								<td align="center" class="listtd"><c:out value="${result.gugun}"/>&nbsp;</td>
								<td align="center" class="listtd"><c:out value="${result.dong}"/>&nbsp;</td>
								<td align="center" class="listtd"><c:out value="${result.bunji}"/>&nbsp;</td>
									</tr>
			</c:forEach>
		</table>
	</div>
	<!-- /List -->
	<div id="paging">
		<ui:pagination paginationInfo="${paginationInfo}" type="paging" jsFunction="fn_egov_link_page"/>
		<form:hidden path="pageIndex" />
	</div>
</div>
</form:form>
</body>
</html>
