<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <style type = "css">
  @import url("css/notice.css")

  </style>
  <style>

  table.boardListBasic {
    border-collapse: separate;
    border-spacing: 0;
    text-align: left;
    line-height: 1.5;
    border-top: 1px solid #ccc;
    border-left: 1px solid #ccc;
}

table.boardListBasic th {
    font-weight: bold;
    vertical-align: top;
    border-right: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
    border-top: 1px solid #fff;
    border-left: 1px solid #fff;
    background: #eee;
}
table.boardListBasic td {
    padding: 10px;
    vertical-align: top;
    border-right: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
}

  div.boardOption > ul select {
    width: auto;
    max-width: 200px
}
  input {
    height: inherit;
    font-size: 13px;
    color: #666;
    box-sizing: border-box;
    /* -webkit-box-sizing: border-box; */
    -moz-box-sizing: border-box;
    /* vertical-align: middle; */
}

#searchbox {
    *width: 120px;
    border: 1px solid #afafaf;
    padding: 0;
    text-align: center;
    color: #e1e1e1;
    vertical-align: middle;
}
#searchbox #search {
    float: left;
    border: 0;
    padding: 0 3px;
}
  </style>
</head>
<body>
<div style = "padding-top: 10px; font-size:30px; padding-bottom:25px; color : #fde585; font-weight:bold " >
공지사항
</div>
<div id = "noticeBoard">
   <div class="boardOption">
					<ul style = "float: left; padding-bottom: 10px;">
						<li style = "float: left; padding-bottom: 10px; padding-right : 10px; display: inline;">
						 	<select name="searchKey" class="default" title="선택해주세요"  style="height:22px">
								<option value="TITLE" selected="">제목</option>
								<option value="BOARD_CONTENTS">내용</option>
								<option value="REG_NAME">작성자 명</option>
							</select>
						</li>
							<li id="searchbox" class="mr0" style = "float: left; padding-left : 20px; display: inline; ">
								<input type="text" name="searchTxt" maxlength="100" value="" onkeyup="inputCheckSpecial(this)" id="search" title="검색">
								<input type="image" name="" src="/images/search_btn.png" onclick="javascript:searchSubmit();" alt="search">
							</li>
					</ul>
	</div>
	<div id = "listBox" style = "display : inline-block">
	<table class="boardListBasic" summary="게시판 리스트입니다.">
						<caption>게시판 리스트</caption>
						<thead>
							<tr class="odd-row">
								<th class="num first">번 호</th>
								<th>제 목</th>
								<th>파 일</th>
								<th>작성자</th>
								<th>작성일</th>
								<th class="last">조회수</th>
							</tr>
						</thead>
					<tbody>
							<!---->
							
									<tr class="odd-row">
										<td style="font-weight:bold;" class="first">
													공지
										</td>
										<td class="textLeft" style="font-weight:bold;">
										<div style="margin-left: 0px;" align="absmiddle" alt="">
										
													<a class="clip boardTitleNcontent TITLE_ORIGIN" href="noticeView.do">
														[안내] 수강신청/정정 정보 이클래스 반영시간 안내
													</a>	 
											</div>
											<div style="margin-left: 0px;" align="absmiddle" alt="">
											</div>
										</td>
										<td style="font-weight:bold;">&nbsp;</td>
										<td style="font-weight:bold;" class="boardTitleNcontent REG_NAME">관리자</td>
										<td style="font-weight:bold;">2018-02-27</td>
										<td style="font-weight:bold;" class="last">4423</td>
									</tr>
								<!---->
							
									<tr>
										<td style="font-weight:bold;" class="first">
										공지
										</td>
										<td class="textLeft" style="font-weight:bold;">
										<div style="margin-left: 0px;" align="absmiddle" alt="">
													<a class="clip boardTitleNcontent TITLE_ORIGIN" href="javascript:moveBoardView('BOAD_170905182259ef57201b', '7', '2');">
														[안내] 학습자 업로드 자료실 관련
													</a>
											</div>
											<div style="margin-left: 0px;" align="absmiddle" alt="">
		 										
											</div>
										</td>
										<td style="font-weight:bold;">&nbsp;</td>
										<td style="font-weight:bold;" class="boardTitleNcontent REG_NAME">관리자</td>
										<td style="font-weight:bold;">2017-09-05</td>
										<td style="font-weight:bold;" class="last">9539</td>
									</tr>
								
							
									<tr class="odd-row">
										<td style="font-weight:bold;" class="first">
												
													공지
											
										</td>
										
										
										<td class="textLeft" style="font-weight:bold;">
										<div style="margin-left: 0px;" align="absmiddle" alt="">
												
													<a class="clip boardTitleNcontent TITLE_ORIGIN" href="javascript:moveBoardView('BOAD_170904164429ea1c03c0', '6', '3');">
														
														[안내] 인터넷 익스플로러 온라인강의 오류 관련
													</a>
												 
											</div>
											<div style="margin-left: 0px;" align="absmiddle" alt="">
		 										
											</div>
										</td>
										<td style="font-weight:bold;">&nbsp;</td>
										<td style="font-weight:bold;" class="boardTitleNcontent REG_NAME">관리자</td>
										<td style="font-weight:bold;">2017-09-04</td>
										<td style="font-weight:bold;" class="last">5623</td>
									</tr>
								
							
									<tr>
										<td style="font-weight:bold;" class="first">
										
												
													공지
												
												
											
										</td>
										
										
										<td class="textLeft" style="font-weight:bold;">
										<div style="margin-left: 0px;" align="absmiddle" alt="">
												
													<a class="clip boardTitleNcontent TITLE_ORIGIN" href="javascript:moveBoardView('BOAD_1709011805497761168b', '5', '4');">
														
														[안내] 인터넷 익스플로러 사용 관련 (로그인 오류/화면 깨짐)
													</a>
												 
											</div>
											<div style="margin-left: 0px;" align="absmiddle" alt="">
		 										
											</div>
										</td>
										<td style="font-weight:bold;">&nbsp;</td>
										<td style="font-weight:bold;" class="boardTitleNcontent REG_NAME">관리자</td>
										<td style="font-weight:bold;">2017-09-01</td>
										<td style="font-weight:bold;" class="last">3712</td>
									</tr>
								
							
									<tr class="odd-row">
										<td style="font-weight:bold;" class="first">
													공지
											
										</td>
										
										
										<td class="textLeft" style="font-weight:bold;">
										<div style="margin-left: 0px;" align="absmiddle" alt="">
												
													<a class="clip boardTitleNcontent TITLE_ORIGIN" href="javascript:moveBoardView('BOAD_170825182300af520076', '4', '5');">
														
														[안내] 스마트 출석 사용 가이드 안내
													</a>
												 
											</div>
											<div style="margin-left: 0px;" align="absmiddle" alt="">
		 										
											</div>
										</td>
										<td style="font-weight:bold;"> <i class="icon-data-color m0"></i> </td>
										<td style="font-weight:bold;" class="boardTitleNcontent REG_NAME">관리자</td>
										<td style="font-weight:bold;">2017-08-25</td>
										<td style="font-weight:bold;" class="last">6787</td>
									</tr>
								
							
									<tr>
										<td style="font-weight:bold;" class="first">
													공지
											
											
										</td>
										
										
										<td class="textLeft" style="font-weight:bold;">
										<div style="margin-left: 0px;" align="absmiddle" alt="">
												
													<a class="clip boardTitleNcontent TITLE_ORIGIN" href="javascript:moveBoardView('BOAD_1708231658556b730001', '2', '6');">
														
														[안내] 로그인 관련 및 질의응답 게시판 안내
													</a>
												 
											</div>
											<div style="margin-left: 0px;" align="absmiddle" alt="">
		 										
											</div>
										</td>
										<td style="font-weight:bold;">&nbsp;</td>
										<td style="font-weight:bold;" class="boardTitleNcontent REG_NAME">관리자</td>
										<td style="font-weight:bold;">2017-08-23</td>
										<td style="font-weight:bold;" class="last">2839</td>
									</tr>
							<!---->
							
									<tr class="odd-row">
										<td class="first">
										
													11
												
											
											
										</td>
										
										
										<td class="textLeft" style="max-width:400px;">
										<div style="margin-left: 0px;" align="absmiddle" alt="">
												 
											</div>
											<div style="margin-left: 0px;" align="absmiddle" alt="">
		 										
													
														<a class="clip boardTitleNcontent TITLE_ORIGIN" href="javascript:moveBoardView('BOAD_190211104424f8690aa6', '18', '7' );">
														
														 [공지] 2019-1학기 수강 관련 이클래스 반영 기간 안내
													</a>
												
											</div>
										</td>
										<td>&nbsp;</td>
										<td class="boardTitleNcontent REG_NAME">관리자</td>
										<td>2019-02-11</td>
										<td class="last">1886</td>
									</tr>
								
							
									<tr>
										<td class="first">
												
													10
										</td>
										
										
										<td class="textLeft" style="max-width:400px;">
										<div style="margin-left: 0px;" align="absmiddle" alt="">
												 
											</div>
											<div style="margin-left: 0px;" align="absmiddle" alt="">
		 										
													
														<a class="clip boardTitleNcontent TITLE_ORIGIN" href="javascript:moveBoardView('BOAD_1808081131198d7620ea', '17', '8' );">
														
														 [공지] 2018-2학기 수강 관련 이클래스 반영 기간 안내
													</a>
												
											</div>
										</td>
										<td>&nbsp;</td>
										<td class="boardTitleNcontent REG_NAME">관리자</td>
										<td>2018-08-08</td>
										<td class="last">3308</td>
									</tr>
							<!---->
							
									<tr class="odd-row">
										<td class="first">
												
													9
										</td>
										
										
										<td class="textLeft" style="max-width:400px;">
										<div style="margin-left: 0px;" align="absmiddle" alt="">
												 
											</div>
											<div style="margin-left: 0px;" align="absmiddle" alt="">
		 										
													
														<a class="clip boardTitleNcontent TITLE_ORIGIN" href="javascript:moveBoardView('BOAD_180801102431ab2a002f', '16', '9' );">
														
														 [공지]시스템 작업 안내 - 8.11(토) 13시 ~ 24시
													</a>
												
											</div>
										</td>
										<td>&nbsp;</td>
										<td class="boardTitleNcontent REG_NAME">관리자</td>
										<td>2018-08-01</td>
										<td class="last">228</td>
									</tr>
							
							<!---->
							
							
							
									<tr>
										<td class="first">
										
													8
											
										</td>
										
										
										<td class="textLeft" style="max-width:400px;">
										<div style="margin-left: 0px;" align="absmiddle" alt="">
												 
											</div>
											<div style="margin-left: 0px;" align="absmiddle" alt="">
		 										
													
														<a class="clip boardTitleNcontent TITLE_ORIGIN" href="javascript:moveBoardView('BOAD_1807231249079c34163c', '15', '10' );">
														
														 2018-2학기 학부 수강신청 시범운영 안내 (7.30~8.1)
													</a>
												
											</div>
										</td>
										<td>&nbsp;</td>
										<td class="boardTitleNcontent REG_NAME">관리자</td>
										<td>2018-07-23</td>
										<td class="last">563</td>
									</tr>
					</tbody>
					</table>
					</div>
<script>
//글쓰기
function fn_write(){
    
    var form = document.getElementById("boardForm");
    
    form.action = "<c:url value='noticeWrite.do'/>";
    form.submit();
    
}
 
//글조회
function fn_view(code){
    
    var form = document.getElementById("boardForm");
    var url = "<c:url value='noticeView.do'/>";
   // url = url + "?code=" + code;
    
    form.action = url;    
    form.submit(); 
}
</script>
</div>
</body>
</html>