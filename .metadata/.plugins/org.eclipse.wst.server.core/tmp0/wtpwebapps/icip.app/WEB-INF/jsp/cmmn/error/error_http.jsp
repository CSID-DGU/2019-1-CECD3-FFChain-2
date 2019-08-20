<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Error</title>
</head>

<body>
	<h2 class="title">장애가 발생하였습니다.</h2>
	<div class="box">
		<div class="step2-2">
			<div class="message">
				<p>이용에 불편을 드려 죄송합니다. </p>
				
		        <table style="width: 1080px;" "border="0" cellspacing="0" cellpadding="0">
		            <caption>Exception</caption>
		            <colgroup>
		                <col style="width: 200px; text-align: left;">
		                <col style="width: *; text-align: left;">
		            </colgroup>
		            <tbody>
		                <tr>
		                    <td>exception :</td>
		                    <td>${exception}</td>
		                </tr>
		            </tbody>
		        </table>				
			</div>
		</div>
	</div>
</body>
</html>
