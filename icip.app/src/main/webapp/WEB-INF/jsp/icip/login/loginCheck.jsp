<%@page import="java.sql.Timestamp"%>
<%@page import="kr.ac.dgu.icip.login.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script language="javascript">
if(${loginFlag} == -1) {
    alert("암호를 다시 확인해주세요.");
    document.location.href="loginMain.do";
}
else if(${loginFlag} == 0){
    alert("아이디를 다시 확인해주세요.");
    document.location.href="loginMain.do";
}
else if(${loginFlag} == 1) {
	alert("로그인에 성공하셨습니다.");
	//여기서 세션값을 지정
	document.location.href="../main/main.do";
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