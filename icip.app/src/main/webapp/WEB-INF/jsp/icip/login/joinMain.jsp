<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import = "java.sql.*" %>
<%
  /**
  * @Class Name : joinMain.jsp
  * @Description : 회원가입 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2019.07.17.	DGU		신규생성
  *
  * author DGU
  * since 2019.07.17
  *
  * @Copyright ⓒ 2019 Dongguk Univ., All Rights Reserved.
  */
%>
<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<META http-equiv="Expires" content="-1">
<META http-equiv="Pragma" content="no-cache">
<META http-equiv="Cache-Control" content="No-Cache">
<link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon" />
<script type="text/javascript" src="/js/jquery/jquery-1.11.1.min.js"></script>
<link id="style2012" rel="stylesheet" type="text/css" href="/css/style2012.css" />
<jsp:useBean id="toDay" class="java.util.Date" />
<title>동국대학교</title>
<script language="JavaScript">
function infoConfirm() {//필드 자체에서 바로 처리하는 alert(컨트롤러로 넘어가지 않는다)
    if(document.reg_frm.id.value.length == 0) {
        alert("아이디는 필수 사항입니다.");
        reg_frm.id.focus();
        return;
    }
    
    if(document.reg_frm.id.value.length < 4) {
        alert("아이디는 4글자 이상이어야 합니다.");
        reg_frm.id.focus();
        return;
    }
    
    if(document.reg_frm.pw.value.length == 0) {
        alert("비밀번호는 필수 사항입니다.");
        reg_frm.pw.focus();
        return;
    }
    
    if(document.reg_frm.pw.value != document.reg_frm.pw_check.value) {
        alert("비밀번호가 일치하지 않습니다.");
        reg_frm.pw.focus();
        return;
    }
    
    if(document.reg_frm.name.value.length == 0) {
        alert("이름은 필수 사항입니다.");
        reg_frm.name.focus();
        return;
    }
    
    if(document.reg_frm.id.value.length == 0) {
        alert("아아디는 필수 사항입니다.");
        reg_frm.id.focus();
        return;
    }
    
    if(document.reg_frm.eMail.value.length == 0) {
        alert("메일은 필수 사항입니다.");
        reg_frm.eMail.focus();
        return;
    }
    
    document.reg_frm.submit();//요 로직 빼는거
}</script>
<style type="text/css">
#menuL ul li.mlonB	{display:block;}
#contents { position:relative; z-index:9980; float:right;height:830px;width:100%;margin-left:0px; }
.board_list {width: 500px; margin: 0 auto;}
.board_list tfoot {text-align: center;}
.signUp_agree {text-align: center;}
.signUp_agree_textarea {text-align: center;}
.signUp_agree_textarea textarea {resize: none;}
</style>
</head>
<body>
<!--/Header Start/-->
<div id="header">
	<a href=""></a>
</div>

<div id="menu">
</div>

<div id="guide">
	<div id="guideL"><span>접속 일시 : </span><fmt:formatDate value="${toDay}" pattern="yyyy-MM-dd" /></div>
</div>
<div>
<form action="joinCheck.do" method="post" name="reg_frm">
아이디 : <input type="text" name="id" size"20"><br />
비밀번호 : <input type="password" name="pw" size"20"><br />
비밀번호 확인 : <input type="password" name="pw_check" size"20"><br />
이름 : <input type="text" name="name" size"20"><br />
메일 : <input type="text" name="eMail" size"20"><br />
주소 : <input type="text" name="address" size"50"><br />
<input type="button" value="회원가입" onclick="infoConfirm()">
<input type="button" value="취소" onclick="location.href='login.do'">
</form>
</div>
<!--/Header End/-->
</body>
</html>