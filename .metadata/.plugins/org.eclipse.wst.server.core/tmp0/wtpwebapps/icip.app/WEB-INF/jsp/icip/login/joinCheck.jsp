<%@page import="java.sql.Timestamp"%>
<%@page import="kr.ac.dgu.icip.login.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script language="javascript">
if(${pageFlag} == 1) {
    alert("회원가입 성공");
    document.location.href="loginMain.do";
}
else {
    alert("회원가입 실패");
    document.location.href="joinMain.do"; 
}
</script>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>

</body>
</html>