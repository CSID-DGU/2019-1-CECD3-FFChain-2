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
<link href="<c:out value="${pageContext.request.contextPath}"/>/css/left2.css" rel="stylesheet" type="text/css" />

<script type="text/javaScript" language="javascript" defer="defer">

</script>
<!-- TOP -->
<html>
 <head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <style type = "css">
  @import url("css/left2.css")
  hr{display : block}
 </style>
 </head> 
 <body>
 <div style: "z-index :900;">
 <ul id = "left" style = "display:block">
		<li class="drop" style="height: 55px;line-height: 55px;"><a href="#0" class="" style = "padding : 15px ;">내 정보</a> 
			<ul class="sub_menu" style="top: 0px;"> 
				<li style="height: 30px;line-height:30px"><a href="/icip/studentinfo/viewStudentinfo.do">학적부 조회</a></li> 
				<li style="height: 30px;line-height:30px"><a href="/icip/notice/noticeList.do" class="">공지사항</a></li>
				<li></li>
			</ul> 
		</li>
		<li class="drop" style="height: 55px;line-height: 55px;"><a href="#0" class="">학습 관리</a> 
			<ul class="sub_menu" style="top: 0px;"> 
				<li style="height: 30px;line-height:30px"><a href="/Board.do?cmd=viewBoardContentsList&amp;boardInfoDTO.boardInfoId=BOAD_SYSTEM_NOTICE&amp;mainDTO.parentMenuId=menu_00029&amp;mainDTO.menuId=menu_00041" class="">교과 학습</a></li> 
				<li style="height: 30px;line-height:30px"><a href="/Board.do?cmd=viewBoardContentsList&amp;boardInfoDTO.boardInfoId=BOAD_SYSTEM_NOTICE&amp;mainDTO.parentMenuId=menu_00029&amp;mainDTO.menuId=menu_00041" class="">비교과 학습</a></li> 
				<li></li>
			</ul> 
		</li>
		<li class="drop" style="height: 55px;line-height: 55px;"><a href="#0" class="">평가</a> 
			<ul class="sub_menu" style="top: 0px;"> 
				<li style="height: 30px;line-height:30px"><a href="/Board.do?cmd=viewBoardContentsList&amp;boardInfoDTO.boardInfoId=BOAD_SYSTEM_NOTICE&amp;mainDTO.parentMenuId=menu_00029&amp;mainDTO.menuId=menu_00041" class="">공지사항</a></li> 
				<li style="height: 30px;line-height:30px"><a href="/Board.do?cmd=viewBoardContentsList&amp;boardInfoDTO.boardInfoId=BOAD_SYSTEM_NOTICE&amp;mainDTO.parentMenuId=menu_00029&amp;mainDTO.menuId=menu_00041" class="">공지사항</a></li> 
				<li style="height: 30px;line-height:30px"><a href="/Board.do?cmd=viewBoardContentsList&amp;boardInfoDTO.boardInfoId=BOAD_SYSTEM_NOTICE&amp;mainDTO.parentMenuId=menu_00029&amp;mainDTO.menuId=menu_00041" class="">공지사항</a></li> 
				<li></li>
			</ul> 
		</li>
		<li class="drop" style="height: 55px;line-height: 55px;"><a href="#0" class="">포트폴리오</a> 
			<ul class="sub_menu" style="top: 0px;"> 
				<li style="height: 30px;line-height:30px"><a href="/Board.do?cmd=viewBoardContentsList&amp;boardInfoDTO.boardInfoId=BOAD_SYSTEM_NOTICE&amp;mainDTO.parentMenuId=menu_00029&amp;mainDTO.menuId=menu_00041" class="">공지사항</a></li> 
				<li style="height: 30px;line-height:30px"><a href="/Board.do?cmd=viewBoardContentsList&amp;boardInfoDTO.boardInfoId=BOAD_SYSTEM_NOTICE&amp;mainDTO.parentMenuId=menu_00029&amp;mainDTO.menuId=menu_00041" class="">공지사항</a></li> 
				<li style="height: 30px;line-height:30px"><a href="/Board.do?cmd=viewBoardContentsList&amp;boardInfoDTO.boardInfoId=BOAD_SYSTEM_NOTICE&amp;mainDTO.parentMenuId=menu_00029&amp;mainDTO.menuId=menu_00041" class="">공지사항</a></li> 
				<li></li>
			</ul> 
		</li>
		<li class="drop" style="height: 55px;line-height: 55px;"><a href="#0" class="">조회</a> 
			<ul class="sub_menu" style="top: 0px;"> 
				<li style="height: 30px;line-height:30px"><a href="/Board.do?cmd=viewBoardContentsList&amp;boardInfoDTO.boardInfoId=BOAD_SYSTEM_NOTICE&amp;mainDTO.parentMenuId=menu_00029&amp;mainDTO.menuId=menu_00041" class="">공지사항</a></li> 
				<li style="height: 30px;line-height:30px"><a href="/Board.do?cmd=viewBoardContentsList&amp;boardInfoDTO.boardInfoId=BOAD_SYSTEM_NOTICE&amp;mainDTO.parentMenuId=menu_00029&amp;mainDTO.menuId=menu_00041" class="">공지사항</a></li> 
				<li style="height: 30px;line-height:30px"><a href="/Board.do?cmd=viewBoardContentsList&amp;boardInfoDTO.boardInfoId=BOAD_SYSTEM_NOTICE&amp;mainDTO.parentMenuId=menu_00029&amp;mainDTO.menuId=menu_00041" class="">공지사항</a></li> 
				<li></li>		
			</ul> 
		</li>
		<li class="drop" style="height: 55px;line-height: 55px; width:0px;"></li>

	</ul>
 </div>
 </body> 
 </html>
<!-- TOP -->