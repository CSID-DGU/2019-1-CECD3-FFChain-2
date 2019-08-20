<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<% pageContext.setAttribute("NEWLINE_ALERT", "\n"); %>
<% pageContext.setAttribute("NEWLINE_ALERT_REPLACE", "\\n"); %>
<link href="<c:out value="${pageContext.request.contextPath}"/>/css/common.css" rel="stylesheet" type="text/css" />
<link href="<c:out value="${pageContext.request.contextPath}"/>/css/normalize.css" rel="stylesheet" type="text/css" />
<link href="<c:out value="${pageContext.request.contextPath}"/>/css/layout.css" rel="stylesheet" type="text/css" />
<link href="<c:out value="${pageContext.request.contextPath}"/>/css/main.css" rel="stylesheet" type="text/css" />
<script language="javascript"> //document.domain = "icip.net"; </script>
<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/js/jquery/jquery-1.11.1.min.js" ></script>
<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/js/jquery/jquery-ui.min.js" ></script>
<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/js/jquery/jquery.validate.min.js" ></script>
<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/js/jquery/jquery.form.js" ></script>
<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/js/commonUtil.js" ></script>

<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/js/web_lib/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/js/js2012.js"></script>

<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/js/web_lib/jquery.maskedinput.js"></script>
<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/js/calendar.js"></script>
<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/js/common.js"></script>
<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/js/jsp/code/code_select.js"></script>
<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/js/web_lib/jquery.cookie.js"></script>

<link type="text/css" rel="stylesheet" href="<c:out value="${pageContext.request.contextPath}"/>/css/style2017.css" />
<link type="text/css" rel="stylesheet" href="<c:out value="${pageContext.request.contextPath}"/>/js/web_lib/css/ui-lightness/jquery-ui-1.8.custom.css" />


<script type="text/javascript">
    <%-- 전체 환경번수 정의 --%>
    var gEnv = {
        STATIC_APP_ROOT : ''
    };

    $(window).load(function(){
        var msg = "<c:out value="${msg}"/>";
        if ( msg.length > 0 ) {
            alert(msg);
        }
    });

    $(document).ready(function () {
        <%-- Back Space 키 방지 --%>
        commonUtil.preventBackSpace();

        <%-- IE9 Table 태그 사이 공백시 문제 공통처리 --%>
        try {
            if (browserDetect && browserDetect.browser == "Explorer" && browserDetect.version == "9") {
                if ($("table") && $("table tbody tr").length > 0) {
                    $.each($("table tbody tr"), function(){
                        $(this).html($(this).html().replace(/^\s+/, '').replace(/\s+$/, ''));
                    });
                }
            }
        } catch(e) {}

        commonUtil.makeNetSsoCookie();
    });

    jQuery.extend(jQuery.validator.messages,
    {
        alphanumeric : '<spring:message code="validate.alphanymeric.error" />',
        required : '<spring:message code="validate.required.error" />',
        email : '<spring:message code="validate.email.error" />',
        url : '<spring:message code="validate.url.error" />',
        number : '<spring:message code="validate.number.error" />',
        digits : '<spring:message code="validate.digits.error" />',
        minlength : '<spring:message code="validate.minlength.error" />',
        maxlength : '<spring:message code="validate.maxlength.error" />',
        date : '<spring:message code="validate.date.error" />',
        xss : '<spring:message code="validate.xss.error" />',
        xssEditor : '<spring:message code="validate.xssEditor.error" />',
        rangelength : '<spring:message code="validate.rangelength.error" />',
        minbytelength : '<spring:message code="validate.minbytelength.error" />',
        maxbytelength : '<spring:message code="validate.maxbytelength.error" />',
        min : '<spring:message code="validate.min.error" />',
        max : '<spring:message code="validate.max.error" />',
        fixlength : '<spring:message code="validate.fixlength.error" />',
        fixbytelength : '<spring:message code="validate.fixbytelength.error" />'
    });

</script>
