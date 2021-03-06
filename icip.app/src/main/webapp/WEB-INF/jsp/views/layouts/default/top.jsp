<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
response.setHeader("cache-control","no-cache");
response.setHeader("expires","-1");
response.setHeader("pragma","no-cache");
%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<link href="<c:out value="${pageContext.request.contextPath}"/>/css/top4.css" rel="stylesheet" type="text/css" />

<script type="text/javaScript" language="javascript" defer="defer">

</script>
<!-- TOP -->
<html>
 <head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <style type = "css">
  @import url("css/top4.css")
  hr{display : block}
 </style>
 </head> 
 <body>
 <div id = "topCover" style = "display:block">
 <hr>
 
 <table>
  <tr style="vertical-align:middle">
    <td style="width:150px; border-right:1px solid gray; padding-right:12px; text-align:justify"><a href = "/icip/main/index.do"><img src="<c:url value="/images/logo.png" />"></a></td>
    <td>
    <div class = "login" style = "padding-left:12px; float:left">
    	<form id="loginForm-member" action="/User.do" method="post" onsubmit="return false;" style="display:block" class="show" style = "float:left">
			<input type="hidden" name="userDTO.paramUserId">
			<input type="hidden" name="userDTO.paramPassword">
			<input type="hidden" name="cmd" value="loginUser">
			<input type="hidden" name="userDTO.outsiderYn" value="N">
				<fieldset>
				<ul class="user_box" style = "float:left">
					<li style = "float:left"><input id="id" name="userDTO.userId" type="text" value="아이디" onfocus="this.value=''" onblur="if(this.value=='')this.value='아이디'" title="아이디를 입력하세요"></li>
					<li style = "float:left"><input type="password" name="userDTO.password" id="pw" onkeypress="if(event.keyCode == 13){fnLogin()}" placeholder="비밀번호" value="" title="비밀번호"></li>
				</ul>
				<p style = "float:left"><a href="#" onclick="fnLogin();" class="loginBtn" style="height: 35px;line-height: 20px;">로그인</a></p>
				</fieldset>
		</form>
	</div>
	</td>
    <td style="padding-left:450px; vertical-align: top; font-size : 14px; text-align: right;">HOME | ENG | JPN | CHN</td>
  </tr>
</table>
 <hr>
 </div>
 </body> 
 </html>
<!-- TOP -->